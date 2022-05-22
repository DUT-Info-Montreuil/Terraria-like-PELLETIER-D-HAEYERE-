package Terraria.modele;

public class Joueur extends Acteur{
    public Joueur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox h ) {
        super(pv, vitesse, posX, posY, environnement, id , h);
        h.getY().bind(this.getYProprety());
        h.getX().bind(this.getXProprety());
    }


    public int collideGaucheDroite(Block b ){
        Tile t = b.getTile() ;
        HitBox h = t.getBox() ;
        if (h.isSolide()) {
            if ((this.getBox().getY().intValue() > b.getBoxY().intValue() && this.getBox().getY().intValue() <= b.getBoxY().intValue() + b.getTile().getBox().getHeight()) || (this.getBox().getY().intValue() + this.getBox().getHeight() > b.getBoxY().intValue()) && (this.getBox().getY().intValue() + this.getBox().getHeight() <= b.getBoxY().intValue() + b.getTile().getBox().getHeight())) {
                if (this.getBox().getX().intValue() <= b.getBoxX().intValue() + b.getTile().getBox().getWidth() && this.getBox().getX().intValue() >= b.getBoxX().intValue() + b.getTile().getBox().getWidth() - b.getOffSet()) {
                    System.out.println("block on the left");
                } else if (this.getBox().getX().intValue() + this.getBox().getWidth() >= b.getBoxX().intValue() + b.getTile().getBox().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() <= b.getBoxX().intValue() + b.getOffSet()) {
                    System.out.println("block on the right");
                }
            }

        }


            return 0 ;
    }
}

