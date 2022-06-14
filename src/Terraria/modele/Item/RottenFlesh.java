package Terraria.modele.Item;

import Terraria.modele.Environnement;

public class RottenFlesh extends Item {
    public RottenFlesh(int quantite , Environnement environnement) {
        super(quantite, true, environnement);
    }


    public void action(int cible) {
        System.out.println("Rotting flesh used");
    }



}
