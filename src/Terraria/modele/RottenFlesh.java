package Terraria.modele;

public class RottenFlesh extends Consomable {


    public RottenFlesh(int quantite, Environnement environnement) {
        super(quantite, environnement);
    }

    public void action(int cible) {
        System.out.println("Rotting flesh used");
    }



}
