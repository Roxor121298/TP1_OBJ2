package com.example.tp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListeProduits liste;

    String nom;

    Produit precommande;

    private Vector<Produit> commande =  new Vector<>();

    LinearLayout cafe_filtre, americano, cafe_glace, latte, MiniImage;

    ChipGroup choixTaille;

    Chip chipSmall, chipMedium, chipLarge;

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
        linearSetup();
        chipSetup();
        btnSetup();

        commadeFini = new AlertDialog.Builder(this);
        commadeFini.setTitle("Commande envoyée");
        commadeFini.setMessage("Paiement de : 0$");
        indicePrixCalorie = findViewById(R.id.indicePrixCalorie);
        totalEtTaxes = findViewById(R.id.totalEtTaxes);



        // je set le bouton clearChecked a false ici
        choixTaille.clearCheck();

    }

    private class Ecouteur implements View.OnClickListener {
        @Override // On selectionne selon le nom quelle méthode il faut appeler
        public void onClick(View event) {
            if (event == cafe_filtre || event == americano || event == cafe_glace || event == latte) {
                clickBoisson(event);
            }
            else if(event == chipSmall || event == chipMedium || event == chipLarge){
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

    // Si fianlement on ne veut pas commander les produti que on a ajouter
    // vide la commande et la précommande
    //remet la vue a sont état de départ
    public void Effacer(){
        commande.removeAllElements();
        precommande = new Produit("","",0,0);
        updateCommande();
        indicePrixCalorie.setText("Aucun Produit selectionné");
        resetImage();
        choixTaille.clearCheck();
    }


    // Quand on a finis de selectionner les produits et que l'on veut commander
    // une alerte est créer et apparait
    // vide la commande et la précommande
    //remet la vue a sont état de départ
    public void Commander(){
        AlertDialog alert = commadeFini.create();
        alert.show();
        commande.removeAllElements();
        precommande = new Produit("","",0,0);
        updateCommande();
        indicePrixCalorie.setText("Aucun Produit selectionné");
        resetImage();
        choixTaille.clearCheck();
    }

    // boutton pour ajouter a la commande qui teste dabbord si on bien une precommande valide puis l'ajoute a notre Vector de Produit commande
    public void ajoutCommande(){
        // pour ne psa créer de bug au début si on click sur ajouter sans selectionner de boisson
        if(precommande != null){
        commande.add(new Produit(precommande.getNoms(),precommande.getFormat(),precommande.getPrix(),precommande.getCalories()));
        updateCommande();
        }
    }

    //Après avoir clicker sur une taille on update la precommande selon la bonne taille
    public void clickTaille(View event){
        updatepreCommande(nom);
    }

    //Après avoir clicker sur une boisson on update la precommande selon la bonne boisson
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


    // Pour retrouver la taille que on a selectionner
    public String getTaille(){
        String taille = "";
        ChipGroup choixTaille = findViewById(R.id.choixTaille);
        int selectedId = choixTaille.getCheckedChipId();
        if (selectedId == R.id.chipSmall) {
            taille = " Petit";
        }
        else if (selectedId == R.id.chipMedium){
            taille = " Moyen";
        }
        else if (selectedId == R.id.chipLarge){
            taille = " Grand";
        }
        return taille;
    }

    // pour updater la commande quand on veut choisir un produit avant de commander
    public void updatepreCommande(String nouveaunom){
        nom = nouveaunom;
        String nomComplet = nom + getTaille();
        // A partir du nom complet on peut trouver de quelle produit il s,agit dans la notre Hashtable
        precommande = liste.recupererProduit(nomComplet);
        //String cout = String.valueOf(precommande.prix);
        String cout = String.format("%.2f", precommande.getPrix());
        String calorie = String.valueOf(precommande.getCalories());
        indicePrixCalorie.setText( nomComplet + " : " + calorie + " calorie et coûte : " + cout + " $");


    }

    // pour update le Total de la commande une fosi que a pesée sur ajouter
    public void updateCommande(){
        double coutTotal = 0;
        for(int i=0; i<commande.size();i++){
            coutTotal += commande.get(i).getPrix();
        }
        coutTotal = coutTotal*1.15;
        String formatted = String.format("%.2f", coutTotal);
        totalEtTaxes.setText("Total: " + formatted);

        //on setup le message final de la commande aussi
        commadeFini.setMessage("Paiement de : " + formatted + " $" );

        nouvelleImage();
    }

    // Méthode pour ajouté des petite imageView de café

    public void nouvelleImage(){

        boolean valid = true;
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()));
        //layoutParams.weight = 1;
        LinearLayout linearLayout = findViewById(R.id.MiniImage);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        layoutParams.gravity= Gravity.CENTER;

        imageView.setLayoutParams(layoutParams);
        // On chosi quelle est la bonne image selon le nom du dernier article (précommande pourrait aussi être lastIndexOf)
        if(precommande.getNoms() == "Café"){
            imageView.setImageResource(R.drawable.cafe_filtre);
        }
        else if(precommande.getNoms() == "Americano"){
            imageView.setImageResource(R.drawable.americano);
        }
        else if(precommande.getNoms() == "Café glacé") {
            imageView.setImageResource(R.drawable.cafe_glace);
        }
        else if(precommande.getNoms() == "Latté") {
            imageView.setImageResource(R.drawable.latte);
        }
        else{
            //Pour que on ne créer pas d'image lorsque la selection est invalide
            valid = false;
        }
        if(valid) {
            imageView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            MiniImage.addView(imageView);
        }

    }

    // Méthode pour enlever les ImageView quand on clicque sur Effacer ou Commander
    public void resetImage(){
        MiniImage.removeAllViews();
    }


    //Méthode de binding des LinearLayout au bon élément de la vue et binding des méthode Onlick
    public void linearSetup() {
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

    //Méthode de binding des chip de la vue et binding des méthode Onlick

    public void chipSetup() {
        choixTaille = findViewById(R.id.choixTaille);

        chipSmall = findViewById(R.id.chipSmall);
        chipMedium = findViewById(R.id.chipMedium);
        chipLarge = findViewById(R.id.chipLarge);

        chipSmall.setOnClickListener(ec);
        chipMedium.setOnClickListener(ec);
        chipLarge.setOnClickListener(ec);
    }

    public void btnSetup(){
        btnAjouter = findViewById(R.id.btnAjouter);
        btnCommander = findViewById(R.id.btnCommander);
        btnEffacer = findViewById(R.id.btnEffacer);

        btnAjouter.setOnClickListener(ec);
        btnCommander.setOnClickListener(ec);
        btnEffacer.setOnClickListener(ec);

    }


}