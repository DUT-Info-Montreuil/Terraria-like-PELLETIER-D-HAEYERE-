package Terraria.modele.Item;

import Terraria.modele.Environnement;

public abstract class ItemOutil extends Item {
    private int durabilte;
    private int niveau;
    public ItemOutil(int quantite, int durabilte, int niveau, Environnement environnement) {
        super(quantite,false,environnement);
        this.durabilte=durabilte;
        this.niveau=niveau;
    }
}
