package Terraria.modele.Item;

import Terraria.modele.Environnement;

public class Consomable extends Item{

    public Consomable(int quantite, Environnement environnement) {
        super(quantite, environnement,1);
    }
    public boolean cielEstModifiable(int idCible){
        //System.out.println(idCible+"valeur a l'id dans le if");
        return (idCible!=1);
    }
}
