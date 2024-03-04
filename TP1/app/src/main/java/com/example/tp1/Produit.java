package com.example.tp1;

import java.util.Vector;

public class Produit {
    String noms;
    String format;
    double prix;
    int calories;

    Produit(String n, String f, double p, int c){
        this.noms = n;
        this.format = f;
        this.prix = p;
        this.calories = c;
    }


    public String getNoms() {
        return noms;
    }

    public String getFormat() {
        return format;
    }

    public double getPrix() {
        return prix;
    }

    public int getCalories() {
        return calories;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


    public String obtenirTaxe(Vector<Produit> commande ){
        double coutTotal = 0;
        for(int i=0; i<commande.size();i++){
            coutTotal += commande.get(i).getPrix();
        }
        coutTotal = coutTotal*1.15;
        String formatted = String.format("%.2f", coutTotal);
        return formatted;
    }
}

