package Terraria.modele.Item;

import Terraria.modele.Environnement;

public class Hache extends ItemOutil{
    public Hache(int quantite, int durabilte, int niveau, Environnement environnement) {
        super(quantite, durabilte, niveau, environnement);
        this.setDegatSurMob(15);

    }

}
