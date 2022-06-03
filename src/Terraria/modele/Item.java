package Terraria.modele;

public abstract class Item {
    private int quantite;
    private String id;
    private boolean consomable;
    private Environnement environnement;
    public Item(int quantite, String id,boolean consomable,Environnement environnement) {
        this.quantite = quantite;
        this.id = id;
        this.consomable = consomable;
        this.environnement=environnement;
    }
    public void action(int cible){
    }
    public boolean cielEstModifiable(int cible){
        return false;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }
}
