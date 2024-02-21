package com.example.tp1;

import java.util.Hashtable;

public class ListeProduits {

    private Hashtable<String, Produit> liste;

    public ListeProduits ()
    {
        liste = new Hashtable();
        liste.put("Café filtre Petit",new Produit("Café","Petit",1.80 ,5 ) );
        liste.put("Café filtre Moyen",new Produit("Café","Moyen",3.00 ,7 ));
        liste.put("Café filtre Grand", new Produit("Café","Grand",3.96 ,10 ));
        liste.put("Americano Petit",new Produit("Americano","Petit",2.40 ,9 ));
        liste.put("Americano Moyen",new Produit("Americano","Moyen",4.00 ,11 ) );
        liste.put("Americano Grand",new Produit("Americano","Grand",5.28 ,18 ) );
        liste.put("Café glacé Petit",new Produit("Café glacé","Petit",2.50 ,10 ) );
        liste.put("Café glacé Moyen",new Produit("Café glacé","Moyen",4.17 ,12 ) );
        liste.put("Café glacé Grand",new Produit("Café glacé","Grand",5.50 ,20 ) );
        liste.put("Latté Petit",new Produit("Latté","Petit",4.00 ,125 ) );
        liste.put("Latté Moyen",new Produit("Latté","Moyen",6.67 ,208 ) );
        liste.put("Latté Grand",new Produit("Latté","Grand",10.00 ,250 ) );
    }


    public Produit recupererProduit(String cle) {
        Produit pvide = new Produit("", "", 0, 0);
        Produit produit = liste.get(cle);

        if (produit != null) {
            return produit;
        } else {
            return pvide;
        }
    }



}
