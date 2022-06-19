package Terraria.modele;

public abstract class ItemOutil extends Item {
    private int durabilte;
    private int niveau;
    public ItemOutil(int quantite, int durabilte, int niveau, Environnement environnement) {
        super(quantite,environnement);
        this.durabilte=durabilte;
        this.niveau=niveau;
    }
    public void removeItemFromInv(){
        if (this.getQuantite()<=0){
            this.getEnvironnement().getJoueur1().getInventaire().remove(this);
        }
    }
}
