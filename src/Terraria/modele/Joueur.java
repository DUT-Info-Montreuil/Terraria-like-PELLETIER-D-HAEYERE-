package Terraria.modele;

import java.util.ArrayList;

public class Joueur extends Acteur{
    public Joueur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox h ) {
        super(pv, vitesse, posX, posY, environnement, id , h);
        h.getY().bind(this.getYProprety());
        h.getX().bind(this.getXProprety());
    }


    public int collideGaucheDroite(ArrayList<Block> blocks ){
        System.out.println("test");
        for (Block block: blocks) {
            System.out.println("retest");
            System.out.println(blocks.size());
            System.out.println(block.getBox().isSolide());
            if (block.getBox().isSolide()) {
                System.out.println("in the if ");
                int a = this.getBox().getY().intValue();
                int b = a + this.getBox().getHeight() ;
                int aPrime = block.getBoxY().intValue() ;
                int bPrime = block.getBoxY().intValue() + block.getTile().getBox().getHeight() ;
                System.out.println("1 "+( a >= aPrime && a <= bPrime));
                System.out.println("2 "+(b >= aPrime && b <= bPrime));
                if (( a >= aPrime && a <= bPrime) || (b >= aPrime && b <= bPrime)) {
                    System.out.println("yep");
                    if (this.getBox().getX().intValue() <= block.getBoxX().intValue() + block.getTile().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() + block.getTile().getWidth() ) {
                        System.out.println("block on the left");
                        return - 1 ;
                    } else if (this.getBox().getX().intValue() <= block.getBoxX().intValue()  && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() ) {
                        System.out.println("block on the right");
                        return 1 ;
                    }
                }

            }



        }
        return 0 ;
        }


}

