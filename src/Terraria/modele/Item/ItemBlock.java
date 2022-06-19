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
                //System.out.println("suprimmable");
                this.getEnvironnement().changementTerrain(idTileModif, this.code);
                for (Block b : this.getEnvironnement().getAllBlock()
                ) {
                    if (b.getId() == idTileModif) {
                        //System.out.println("tile found");
                        b.getBox().setHitBoxTrue();
                    }
                }
            }
            this.quantiteEnMoins();
        }
        //System.out.println("de l'air");

    }

    public boolean cielEstModifiable(int idCible) {
        //System.out.println(idCible+"valeur a l'id dans le if");
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
