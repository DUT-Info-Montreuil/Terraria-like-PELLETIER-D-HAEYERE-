package Terraria.modele;

import java.util.ArrayList;

public class Joueur extends Acteur{
    public Joueur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox h ) {
        super(pv, vitesse, posX, posY, environnement, id , h);
        h.getY().bind(this.getYProprety());
        h.getX().bind(this.getXProprety());
    }


    public int collideGaucheDroite(ArrayList<Block> blocks ){
        for (Block block: blocks) {
            if (block.getBox().isSolide()) {
                int a = this.getBox().getY().intValue()-1;
                int b = a + this.getBox().getHeight()-1 ;
                int aPrime = block.getBoxY().intValue()-1 ;
                int bPrime = block.getBoxY().intValue() + block.getTile().getBox().getHeight() -1;

                if (( a >= aPrime && a <= bPrime) || (b >= aPrime && b <= bPrime)) {

                    if (this.getBox().getX().intValue() <= block.getBoxX().intValue() + block.getTile().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() + block.getTile().getWidth() ) {

                        return - 1 ;
                    } else if (this.getBox().getX().intValue() <= block.getBoxX().intValue()  && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() && this.getBox().getX().intValue() + this.getBox().getWidth() <= block.getBoxX().intValue() + block.getOffSet()  ) {

                        return 1 ;
                    }
                }

            }



        }
        return 0 ;
        }







        public int collideHautBas(ArrayList<Block> blocks){
            for (Block block:blocks) {
                if (block.getBox().isSolide()){
                    if (this.getBox().getY().intValue() + this.getBox().getHeight() >= block.getBoxY().intValue() && this.getBox().getY().intValue() + this.getBox().getHeight() <= block.getBox().getY().intValue() + block.getOffSet() ){
                        int b = this.getBox().getX().intValue() ;
                        int d = this.getBox().getX().intValue() + this.getBox().getWidth();
                        int  aPrime = block.getBoxX().intValue() ;
                        int cPrime = block.getBoxX().intValue() ;
                       // System.out.println("yes");
                            if (( b <= aPrime &&  d >= aPrime) || (b <= cPrime && d >= cPrime)){
                            //System.out.println("test");
                            this.setPosY(block.getBoxY().intValue()- this.getBox().getHeight());
                            return 1 ;
                        }
                    }





                }




            }
            return  0;
        }


}

