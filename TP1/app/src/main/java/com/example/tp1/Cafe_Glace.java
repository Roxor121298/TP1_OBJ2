package com.example.tp1;

public class Cafe_Glace extends Produit {

    String nomscafe = "Café glacé";
    double prixCafe = 2.50;
    int calorieCafe = 10;

    Cafe_Glace(String n, String f, double p, int c) {
            super(n,f,p,c);
            this.noms = nomscafe;
        this.calories = calorieCafe;

            if (this.format.equals("Moyen")){
                this.prix = (prixCafe/3)*5;
                this.calories = 12;
            }
            else if (this.format.equals("Grand")){
                this.prix = prixCafe*2.2;
                this.calories = 20;
            }
    }
}
