package Terraria.modele.Item;

import Terraria.modele.Block;
import Terraria.modele.Environnement;

public class ItemBlock extends Item {
    private int code;

    public ItemBlock(int quantite, Environnement environnement, int code) {
        super(quantite,  environnement,1);
        this.code = code;
    }

    public void action(int idTileModif) {

        if (this.getQuantite() != 0) {
            if (this.getEnvironnement().getTerrain().get(idTileModif) == 1) {

                this.getEnvironnement().changementTerrain(idTileModif, this.code);
                for (Block b : this.getEnvironnement().getAllBlock()
                ) {
                    if (b.getId() == idTileModif) {

                        b.getBox().setHitBoxTrue();
                    }
                }
            }
            this.quantiteEnMoins();
        }


    }

    public boolean cielEstModifiable(int idCible) {

        return (idCible == 1);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ItemBlock{" +
                "code=" + code +
                '}';
    }

}
