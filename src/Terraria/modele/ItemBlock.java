package Terraria.modele;

public abstract class ItemBlock extends Item {
    public ItemBlock(int quantite, String id,Environnement environnement) {
        super(quantite, id,false,environnement);
    }
}
