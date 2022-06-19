package Terraria.modele.Item;

import Terraria.modele.Environnement;

public abstract class Item {
    private int quantite;
    private String id;
    private int degatSurMob;
    private Environnement environnement;
    private static int count = 0;

    public Item(int quantite, Environnement environnement,int degatSurMob) {
        this.quantite = quantite;
        this.id = "item" + count++;
        this.degatSurMob = degatSurMob;
        this.environnement = environnement;
    }

    public void action(int cible) {
    }

    public void setDegatSurMob(int degatSurMob) {
        this.degatSurMob = degatSurMob;
    }

    public int getDegatSurMob() {
        return degatSurMob;
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
        if (this.quantite<0){
            this.quantite=0;
        }
    }




    public String getId() {
        return id;
    }


    public void quantiteEnPlus() {
        this.quantite++;
    }

    public void removeQuantite(int quant){
        this.quantite=quantite-quant;
        if (this.quantite<0){
            this.quantite=0;
        }
    }
    public void removeItemFromInv(){}
    @Override
    public String toString() {
        return "Item{" +
                "quantite=" + quantite +
                ", id='" + id + '\'' +

                ", environnement=" + environnement +
                '}';
    }
}

