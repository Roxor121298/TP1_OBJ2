package com.example.tp1;

import java.util.Vector;

public class Commande {

    Vector<Produit> listeProduit;

    double prixTotal;

    public Commande(){
        listeProduit = new Vector<Produit>();
        prixTotal = 0;
    }

    public double getPrixTotal() {return prixTotal;}
    public Vector<Produit> getListeProduit() {return listeProduit;}
    public void setPrixTotal(double prixTotal) {this.prixTotal = prixTotal;}
    public void setListeProduit(Vector<Produit> listeProduit) {this.listeProduit = listeProduit;}

    public double taxeAJour(){
        double coutTotal = 0;
        for(int i=0; i<listeProduit.size();i++){
            coutTotal += listeProduit.get(i).getPrix();
        }
        coutTotal = coutTotal*1.15;
        prixTotal = coutTotal;
        return prixTotal;
    }
}
