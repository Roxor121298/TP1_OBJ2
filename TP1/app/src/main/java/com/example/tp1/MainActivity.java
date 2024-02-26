package com.example.tp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListeProduits liste;

    String nom, nomcomplet;

    Produit precommande;

    private Vector<Produit> commande =  new Vector<>();

    LinearLayout cafe_filtre, americano, cafe_glace, latte, MiniImage;

    RadioGroup choixTaille;

    RadioButton radioButtonSmall,radioButtonMedium,radioButtonLarge;

    TextView indicePrixCalorie, totalEtTaxes;

    Button btnAjouter, btnEffacer, btnCommander;

    Ecouteur ec;

    AlertDialog.Builder commadeFini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liste = new ListeProduits();
        ec = new Ecouteur();
        LinearSetup();
        RadioSetup();

        commadeFini = new AlertDialog.Builder(this);

        commadeFini.setTitle("Commande envoyée");
        commadeFini.setMessage("Paiement de : 0$");

        indicePrixCalorie = findViewById(R.id.indicePrixCalorie);
        totalEtTaxes = findViewById(R.id.totalEtTaxes);

        btnAjouter = findViewById(R.id.btnAjouter);
        btnCommander = findViewById(R.id.btnCommander);
        btnEffacer = findViewById(R.id.btnEffacer);

        btnAjouter.setOnClickListener(ec);
        btnCommander.setOnClickListener(ec);
        btnEffacer.setOnClickListener(ec);



    }

    // "Ce produit contient : 5 calorie et coûte : 1,00$"
    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View event) {
            if (event == cafe_filtre || event == americano || event == cafe_glace || event == latte) {
                clickBoisson(event);
            }
            else if(event == radioButtonSmall || event == radioButtonMedium || event == radioButtonLarge ){
                clickTaille(event);
            }
            else if(event == btnAjouter){
                ajoutCommande();
            }
            else if(event == btnCommander){
                Commander();
            }
            else if(event == btnEffacer){
                Effacer();
            }
        }
    }

    public void Effacer(){
        commande.removeAllElements();
        precommande = new Produit("","",0,0);
        updateCommande();
    }



    public void Commander(){
        AlertDialog alert = commadeFini.create();
        alert.show();
        commande.removeAllElements();
        precommande = new Produit("","",0,0);
        updateCommande();
    }

    public void ajoutCommande(){
        commande.add(new Produit(precommande.noms,precommande.format,precommande.prix,precommande.calories));
        updateCommande();
    }
    public void clickTaille(View event){
        updatepreCommande(nom);
    }

    public void clickBoisson(View event){
        if(event == cafe_filtre){
            updatepreCommande("Café filtre");
        }
        else if (event == americano){
            updatepreCommande("Americano");
        }
        else if (event == cafe_glace){
            updatepreCommande("Café glacé");
        }
        else if (event == latte){
            updatepreCommande("Latté");
        }
    }


    public String getTaille(){
        String taille = "";
        RadioGroup choixTaille = findViewById(R.id.choixTaille);
        int selectedId = choixTaille.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButtonSmall) {
            taille = " Petit";
        }
        else if (selectedId == R.id.radioButtonMedium){
            taille = " Moyen";
        }
        else if (selectedId == R.id.radioButtonLarge){
            taille = " Grand";
        }
        return taille;
    }

    public void updatepreCommande(String nouveaunom){
        nom = nouveaunom;
        String nomComplet = nom + getTaille();
        precommande = liste.recupererProduit(nomComplet);
        //String cout = String.valueOf(precommande.prix);
        String cout = String.format("%.2f", precommande.prix);
        String calorie = String.valueOf(precommande.calories);
        indicePrixCalorie.setText( nomComplet + " : " + calorie + " calorie et coûte : " + cout + " $");


    }

    public void updateCommande(){
        double coutTotal = 0;
        for(int i=0; i<commande.size();i++){
            coutTotal += commande.get(i).prix;
        }
        coutTotal = coutTotal*1.15;
        String formatted = String.format("%.2f", coutTotal);
        totalEtTaxes.setText("Total: " + formatted);

        //on setup le message final de la commande aussi
        commadeFini.setMessage("Paiement de : " + formatted + " $" );

        nouvelleImage();
    }

    public void nouvelleImage(){

        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()));
        //layoutParams.weight = 1;
        LinearLayout linearLayout = findViewById(R.id.MiniImage);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        layoutParams.gravity= Gravity.CENTER;

        imageView.setLayoutParams(layoutParams);
        //imageView.setImageResource(R.drawable.latte);
        // Facon paresseuse de choisisr la bonne image ici
        if(precommande.noms == "Café"){
            imageView.setImageResource(R.drawable.cafe_filtre);
        }
        else if(precommande.noms == "Americano"){
            imageView.setImageResource(R.drawable.americano);
        }
        else if(precommande.noms == "Café glacé") {
            imageView.setImageResource(R.drawable.cafe_glace);
        }
        else if(precommande.noms == "Latté") {
            imageView.setImageResource(R.drawable.latte);
        }
        else{
            resetImage();
        }

        imageView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        MiniImage.addView(imageView);

    }

    public void resetImage(){
        MiniImage.removeAllViews();
    }




    //Méthode binding des LinearLayout au bon élément de la vue et ajout des méthode Onlick
    public void LinearSetup() {
        cafe_filtre = findViewById(R.id.cafe_filtre);
        americano = findViewById(R.id.americano);
        cafe_glace = findViewById(R.id.cafe_glace);
        latte = findViewById(R.id.latte);
        MiniImage = findViewById(R.id.MiniImage);

        cafe_filtre.setOnClickListener(ec);
        americano.setOnClickListener(ec);
        cafe_glace.setOnClickListener(ec);
        latte.setOnClickListener(ec);
    }

    public void RadioSetup() {
        choixTaille = findViewById(R.id.choixTaille);

        radioButtonSmall = findViewById(R.id.radioButtonSmall);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        radioButtonLarge = findViewById(R.id.radioButtonLarge);

        radioButtonSmall.setOnClickListener(ec);
        radioButtonMedium.setOnClickListener(ec);
        radioButtonLarge.setOnClickListener(ec);
    }


}