package Terraria.modele;

public abstract class ItemOutil extends Item {
    private int durabilte;
    private int niveau;
    public ItemOutil(int quantite, String id, int durabilte, int niveau,Environnement environnement) {
        super(quantite, id,false,environnement);
        this.durabilte=durabilte;
        this.niveau=niveau;
    }
}
