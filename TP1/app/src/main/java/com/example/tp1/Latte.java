package com.example.tp1;

public class Latte extends Produit {

    String nomscafe = "Café filtre";
    double prixCafe = 4.00;
    int calorieCafe = 125;

    Latte(String n, String f, double p, int c) {
        super(n,f,p,c);
        this.setNoms(nomscafe);

        if (this.getFormat().equals("Moyen")){
            this.setPrix((prixCafe/3)*5);
            this.setCalories((calorieCafe/3)*5);
        }
        else if (this.getFormat().equals("Grand")){
            this.setPrix(prixCafe*2.5);
            this.setCalories((calorieCafe)*2);
        }
        else {
            this.setPrix(prixCafe);
            this.setCalories(calorieCafe);
        }
    }
}
