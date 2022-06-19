package Terraria.modele.Item;

import Terraria.modele.Environnement;

public abstract class ItemOutil extends Item {
    private int durabilte;
    private int niveau;
    public ItemOutil(int quantite, int durabilte, int niveau, Environnement environnement) {
        super(quantite,environnement,2);
        this.durabilte=durabilte;
        this.niveau=niveau;
    }
    public void removeItemFromInv(){
        if (this.getQuantite()<=0){
            this.getEnvironnement().getJoueur1().getInventaire().remove(this);
        }
    }
}
