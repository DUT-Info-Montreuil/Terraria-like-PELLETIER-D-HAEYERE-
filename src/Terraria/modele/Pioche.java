package Terraria.modele;

public class Pioche extends ItemOutil{

    public Pioche(String id, int durabilte, int niveau,Environnement environnement) {
        super(1, id, durabilte, niveau,environnement);
    }

    public void action(int tileModif){
        System.out.println("suprimmable");
        this.getEnvironnement().changementTerrain(tileModif,3);
    }
}
