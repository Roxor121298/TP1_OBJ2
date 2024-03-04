package com.example.tp1;

public class Cafe_Glace extends Produit {

    String nomsCafe_Glace = "Café glacé";
    double prixCafe_Glace = 2.50;
    int calorieCafe_Glace = 10;

    Cafe_Glace(String n, String f, double p, int c) {
            super(n,f,p,c);
            this.setNoms(nomsCafe_Glace);

            if (this.getFormat().equals("Moyen")){
                this.setPrix((prixCafe_Glace/3)*5);
                this.setCalories(12);
            }
            else if (this.getFormat().equals("Grand")){
                this.setPrix(prixCafe_Glace*2.2);
                this.setCalories(20);
            }
            else {
                this.setPrix(prixCafe_Glace);
                this.setCalories(calorieCafe_Glace);
            }
    }
}
