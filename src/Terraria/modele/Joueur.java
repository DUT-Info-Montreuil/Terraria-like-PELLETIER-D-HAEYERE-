package Terraria.modele;

import java.util.ArrayList;


public class Joueur extends Acteur {
    private Item itemEquipe;

    public Joueur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id, HitBox h, Item itemEquipe) {
        super(pv, vitesse, posX, posY, environnement, id, h);
        h.getY().bind(this.getYProprety());
        h.getX().bind(this.getXProprety());
        this.itemEquipe = itemEquipe;

    }

    public void setItemEquipe(Item itemEquipe) {
        this.itemEquipe = itemEquipe;
    }


    public int collideGaucheDroite(ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (block.getBox().isSolide()) {
                int a = this.getBox().getY().intValue() - 1;
                int b = a + this.getBox().getHeight() - 1;
                int aPrime = block.getBoxY().intValue() - 1;
                int bPrime = block.getBoxY().intValue() + block.getTile().getBox().getHeight() - 1;

                if ((a >= aPrime && a <= bPrime) || (b >= aPrime && b <= bPrime)) {

                    if (this.getBox().getX().intValue() <= block.getBoxX().intValue() + block.getTile().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() + block.getTile().getWidth()) {
                        //test collision gauche
                        this.setPosX(block.getBox().getX().intValue() + block.getBox().getWidth());
                        return -1;
                    } else if (this.getBox().getX().intValue() <= block.getBoxX().intValue() && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() && this.getBox().getX().intValue() + this.getBox().getWidth() <= block.getBoxX().intValue() + block.getOffSet()) {
                        // test collision droite
                        this.setPosX(block.getBox().getX().intValue() - this.getBox().getWidth());
                        return 1;
                    }
                }

            }


        }
        return 0;
    }

    public void initInv() {
        for (Tile t : environnement.getAllTiles()
        ) {
            if (t.getId() != 1) {
                this.addItem(new ItemBlock(0, String.valueOf(t.getId()), environnement, t.getId()));
            }

        }
    }


    public int collideHautBas(ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (block.getBox().isSolide()) {
                if (this.getBox().getY().intValue() + this.getBox().getHeight() >= block.getBoxY().intValue() && this.getBox().getY().intValue() + this.getBox().getHeight() <= block.getBox().getY().intValue() + block.getOffSet()) {
                    int b = this.getBox().getX().intValue();
                    int d = this.getBox().getX().intValue() + this.getBox().getWidth();

                    int aPrime = block.getBoxX().intValue();
                    int cPrime = block.getBoxX().intValue() + block.getBox().getWidth();
                    // System.out.println("yes");
                    //
                    if ((b <= aPrime && d >= aPrime) || (b <= cPrime && d >= cPrime)) {
                        //System.out.println("test");
                        this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());
                        //System.out.println("gauche");

                        return 1;

                    } else {
                        //System.out.println("rien");
                    }

                }


            }


        }
        return 0;
    }

    public boolean checkDistanceInReach(int posX, int posY) {
        if ((valABS(this.getPosX() - posX) < this.getReach() * 16) && (valABS(this.getPosY() - posY) < this.getReach() * 16)) {
            return true;
        }
        return false;
    }

    public int valABS(int valeur) {
        if (valeur < 0)
            return -valeur;
        return valeur;
    }

    public Item getItemEquipe() {
        return this.itemEquipe;
    }
}

