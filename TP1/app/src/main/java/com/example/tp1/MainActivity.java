package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Vector<Produit> commande =  new Vector<>();

    LinearLayout cafe_filtre, americano, cafe_glace, latte;

    RadioGroup choixTaille;

    TextView indicePrixCalorie, totalEtTaxes;

    Button btnAjouter, btnEffacer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}