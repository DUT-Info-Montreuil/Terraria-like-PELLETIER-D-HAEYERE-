package Terraria.modele;

import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Joueur extends Acteur{
    private Item itemEquipe;
    public Joueur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox h ,Item itemEquipe) {
        super(pv, vitesse, posX, posY, environnement, id , h);

        h.getY().bind(this.getYProprety());
        h.getX().bind(this.getXProprety());
        this.itemEquipe=itemEquipe;
        initInv();
    }

    public void setItemEquipe(Item itemEquipe) {
        this.itemEquipe = itemEquipe;
        System.out.println("changement item");
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
                        //test collision gauche
                        this.setPosX(block.getBox().getX().intValue() + block.getBox().getWidth());
                        return - 1 ;
                    } else if (this.getBox().getX().intValue() <= block.getBoxX().intValue()  && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() && this.getBox().getX().intValue() + this.getBox().getWidth() <= block.getBoxX().intValue() + block.getOffSet()  ) {
                        // test collision droite
                        this.setPosX(block.getBox().getX().intValue()-this.getBox().getWidth());
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

                        int aPrime = block.getBoxX().intValue() ;
                        int cPrime = block.getBoxX().intValue() + block.getBox().getWidth();
                       // System.out.println("yes");
                        //
                            if ( ( b <= aPrime &&  d >= aPrime) || (b <= cPrime && d >= cPrime)) {
                                //System.out.println("test");
                                /*for (Block bl:blocks) {
                                    if (bl.getBox().getX().intValue() == block.getBox().getX().intValue() && bl.getBox().isSolide()){
                                        if (bl.getBox().getY().intValue() == block.getBox().getY().intValue() + bl.getBox().getHeight()){
                                            this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());
                                            return 0 ;
                                        }
                                    }
                                }*/
                                this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());

                                //System.out.println("gauche");

                                this.setFalling(false);
                                return 1;


                            }

                    }





                }




            }
            this.setFalling(true);
            return  0;
        }




    public int itemCollide(ObservableList<OnGroundItem> allItem){
        for (int is= 0; is < allItem.size(); is++) {
                int a = this.getBox().getY().intValue()-1;
                int b = a + this.getBox().getHeight()-1 ;
                int aPrime = i.getBox().getY().intValue()-1 ;
                int bPrime = i.getBox().getX().intValue() + i.getBox().getHeight() -1;

                if (( a >= aPrime && a <= bPrime) || (b >= aPrime && b <= bPrime)) {

                    if (this.getBox().getX().intValue() <= i.getBox().getX().intValue() + i.getBox().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() >= i.getBox().getX().intValue() + i.getBox().getWidth() ) {
                        //test collision gauche
                        System.out.println("in item");
                        return - 1 ;
                    } else if (this.getBox().getX().intValue() <= i.getBox().getX().intValue()  && this.getBox().getX().intValue() + this.getBox().getWidth() >= i.getBox().getX().intValue() && this.getBox().getX().intValue() + this.getBox().getWidth() <= i.getBox().getX().intValue()   ) {
                        System.out.println("in item");
                        return 1 ;
                    }
                }





        }
        return 0 ;
    }

        public boolean checkDistanceInReach(int posX, int posY){
            if ((valABS(this.getPosX()-posX)<this.getReach()*16)&&(valABS(this.getPosY()-posY)<this.getReach()*16)){
                return true;
            }
            return false;
        }

        public int valABS(int valeur){
        if (valeur<0)
            return -valeur;
        return valeur;
        }

    public Item getItemEquipe() {
        return this.itemEquipe;
    }

    public void initInv(){
        for (Tile t:environnement.getAllTiles()
             ) {
            if (t.getId()!=1){
                this.addItem(new ItemBlock(0,environnement,t.getId()));
            }

        }
    }
}

