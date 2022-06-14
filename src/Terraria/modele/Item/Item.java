package Terraria.modele.Item;

import Terraria.modele.Environnement;

public abstract class Item {
    private int quantite;
    private String id;
    private boolean consomable;
    private Environnement environnement;
    private static int count = 0;

    public Item(int quantite, boolean consomable, Environnement environnement) {
        this.quantite = quantite;
        this.id = "item" + count++;
        this.consomable = consomable;
        this.environnement = environnement;
    }

    public void action(int cible) {
    }


    public boolean cielEstModifiable(int cible) {
        return false;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void quantiteEnMoins() {
        this.quantite--;
    }


    public boolean isConsomable() {
        return consomable;
    }

    public String getId() {
        return id;
    }


    public void quantiteEnPlus() {
        this.quantite++;
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantite=" + quantite +
                ", id='" + id + '\'' +
                ", consomable=" + consomable +
                ", environnement=" + environnement +
                '}';
    }
}

