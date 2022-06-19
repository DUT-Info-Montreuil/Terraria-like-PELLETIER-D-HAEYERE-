package Terraria.modele;

public class Consomable extends Item{

    public Consomable(int quantite, Environnement environnement) {
        super(quantite, environnement);
    }
    public boolean cielEstModifiable(int idCible){
        //System.out.println(idCible+"valeur a l'id dans le if");
        return (idCible!=1);
    }
}
