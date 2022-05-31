package Terraria.modele;

public abstract class Item {
    private int quantite;
    private String id;
    private boolean consomable;

    public Item(int quantite, String id,boolean consomable) {
        this.quantite = quantite;
        this.id = id;
        this.consomable = consomable;
    }
}
