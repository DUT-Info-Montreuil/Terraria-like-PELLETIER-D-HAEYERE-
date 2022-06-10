package Terraria.modele;

public class RottenFlesh extends Item {
    public RottenFlesh(int quantite ,Environnement environnement) {
        super(quantite, true, environnement);
    }


    public void action(int cible) {
        System.out.println("Rotting flesh used");
    }



}
