package Terraria.modele.Item;

import Terraria.modele.Environnement;

public class RottenFlesh extends Consomable {


    public RottenFlesh(int quantite, Environnement environnement) {
        super(quantite, environnement);
    }

    public void action(int cible) {
        System.out.println("Rotting flesh used");
    }



}
