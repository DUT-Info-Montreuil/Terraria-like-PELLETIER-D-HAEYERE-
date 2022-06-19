package Terraria.modele.Item;

import Terraria.modele.Block;
import Terraria.modele.Environnement;

public class Pioche extends ItemOutil {

    public Pioche( int durabilte, int niveau, Environnement environnement) {
        super(1, durabilte, niveau, environnement);
    }

    public void action(int idTileModif) {
        if (idTileModif>0){
            if (this.getEnvironnement().getTerrain().get(idTileModif)!=1){

                this.getEnvironnement().changementTerrain(idTileModif, 1);
                for (Block b : this.getEnvironnement().getAllBlock()
                ) {
                    if (b.getId() == idTileModif) {

                        b.getBox().setHitBoxFals();
                    }
                }
            }
        }



    }
    public boolean cielEstModifiable(int idCible){

        return (idCible!=1);
    }

}
