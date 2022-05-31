package Terraria.modele;

public abstract class ItemOutil extends Item {
    private int durabilte;
    private int niveau;
    public ItemOutil(int quantite, String id, int durabilte, int niveau) {
        super(quantite, id,false);
        this.durabilte=durabilte;
        this.niveau=niveau;
    }
}
