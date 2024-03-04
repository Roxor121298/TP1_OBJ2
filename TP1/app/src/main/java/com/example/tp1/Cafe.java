package com.example.tp1;

public class Cafe extends Produit {

    String nomscafe = "Café filtre";
    double prixCafe = 1.80;
    int calorieCafe = 5;

    Cafe(String n, String f, double p, int c) {
        super(n,f,p,c);
        this.noms = nomscafe;
        this.calories = calorieCafe;

        if (this.format.equals("Moyen")){
        this.prix = (prixCafe/3)*5;
        this.calories = 7;
        }
        else if (this.format.equals("Grand")){
            this.prix = prixCafe*2.2;
            this.calories = 10;
        }
    }
}
