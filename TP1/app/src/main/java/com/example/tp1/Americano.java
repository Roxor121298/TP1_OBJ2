package com.example.tp1;

public class Americano extends Produit {
    String nomsAmericano = "Américano";
    double prixAmericano = 2.40;
    int calorieAmericano = 9;

    Americano(String n, String f, double p, int c) {
        super(n,f,p,c);
        this.setNoms(nomsAmericano);

        if (this.getFormat().equals("Moyen")){
            this.setPrix((prixAmericano/3)*5);
            this.setCalories(calorieAmericano + 2);
        }
        else if (this.getFormat().equals("Grand")){
            this.setPrix(prixAmericano*2.2);
            this.setCalories(calorieAmericano * 2);
        }
        else {
            this.setPrix(prixAmericano);
            this.setCalories(calorieAmericano);
        }
    }
}
