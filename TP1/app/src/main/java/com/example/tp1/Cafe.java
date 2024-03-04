package com.example.tp1;

public class Cafe extends Produit {

    String nomscafe = "Café filtre";
    double prixCafe = 1.80;
    int calorieCafe = 5;

    Cafe(String n, String f, double p, int c) {
        super(n,f,p,c);
        this.setNoms(nomscafe);

        if (this.getFormat().equals("Moyen")){
        this.setPrix((prixCafe/3)*5);
        this.setCalories(7);
        }
        else if (this.getFormat().equals("Grand")){
            this.setPrix(prixCafe*2.2);
            this.setCalories(10);
        }
        else {
            this.setPrix(prixCafe);
            this.setCalories(calorieCafe);
        }
    }
}
