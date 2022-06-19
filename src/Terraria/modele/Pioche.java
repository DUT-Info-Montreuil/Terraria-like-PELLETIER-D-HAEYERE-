package Terraria.modele;

public class Pioche extends ItemOutil {

    public Pioche( int durabilte, int niveau, Environnement environnement) {
        super(1, durabilte, niveau, environnement);
    }

    public void action(int idTileModif) {

        if (this.getEnvironnement().getTerrain().get(idTileModif)!=1){
            //System.out.println("suprimmable");
            this.getEnvironnement().changementTerrain(idTileModif, 1);
            for (Block b : this.getEnvironnement().getAllBlock()
            ) {
                if (b.getId() == idTileModif) {
                    //System.out.println("tile found");
                    b.getBox().setHitBoxFals();
                }
            }
        }
            //System.out.println("de l'air");

    }
    public boolean cielEstModifiable(int idCible){
        //System.out.println(idCible+"valeur a l'id dans le if");
        return (idCible!=1);
    }

}
