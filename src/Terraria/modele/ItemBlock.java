package Terraria.modele;

public class ItemBlock extends Item {
    private int code;

    public ItemBlock(int quantite, String id, Environnement environnement, int code) {
        super(quantite, id, false, environnement);
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
                        b.getBox().inversionHitBox();
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
