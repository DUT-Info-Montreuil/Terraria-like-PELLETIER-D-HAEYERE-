package Terraria.modele.Acteur;

import Terraria.modele.*;
import Terraria.modele.Item.Item;
import Terraria.modele.Item.ItemBlock;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;


public abstract class Acteur {
    int pv;
    private int vitesse;
    private IntegerProperty posX,posY;
    private  int direction;
    private ArrayList<Item>inventaire;
    private int multiplicateurSaut;
    private boolean isFalling;
    private boolean isJumping;
    protected int reach;
    private HitBox box ;
    private static int count = 0 ;
    private String id;



    public void setDirection(int direction) {
        this.direction = direction;
    }


    protected boolean isAlive;

    public boolean isJumping() {
        return isJumping;
    }

    public String getId() {
        return id;
    }

    protected Environnement environnement;

    public HitBox getBox() {
        return box;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public Acteur(int pv, int vitesse, int posX, int posY, Environnement environnement, HitBox b ) {
        this.pv = pv;
        this.vitesse = vitesse;
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
        this.direction=0;
        this.isAlive = true;
        this.environnement = environnement;
        this.id="acteur"+count++;
        this.isFalling=true;
        this.multiplicateurSaut=70;
        this.isJumping=false;
        this.box = b ;
        this.inventaire = new ArrayList<Item>();
        this.reach=4;
        b.getY().bind(this.getYProprety());
        b.getX().bind(this.getXProprety());




    }

    public int getPv() {
        return pv;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getDirection() {
        return direction;
    }

    public int getPosX() {
        return posX.getValue();
    }

    public int getPosY() {
        return posY.getValue();
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public boolean getisAlive() {
        return isAlive;
    }

    public void setPosX(int posX) {
        this.posX.set(posX);
    }

    public void setPosY(int posY) {
        this.posY.set(posY);
    }

    public void seDeplace(){
        this.posX.setValue(posX.getValue()+(direction*vitesse*2));




    }
    public void addItem(Item item){
        System.out.println("add item");
        for (int i = 0 ; i < inventaire.size() ; i++){
            if (!(inventaire.get(i) instanceof ItemBlock)){
                Class c = inventaire.get(i).getClass();
                if(c.equals(item.getClass())){
                    inventaire.get(i).quantiteEnPlus();
                    return;
                }
            }


            }
        inventaire.add(item);
        }



    public ArrayList<Item> getInventaire() {
        return inventaire;
    }

    public void gravite(){
        if (isFalling){
            this.posY.setValue(posY.getValue()+(vitesse*2));
        }
    }
    public void saute(){
        if (!isFalling){
            this.posY.setValue(posY.getValue()-multiplicateurSaut);
        }



    }
    public IntegerProperty getXProprety(){return posX;}

    public IntegerProperty getYProprety(){return posY;}


    public int getReach() {
        return reach;
    }
  
    public int collideGaucheDroite(ArrayList<Block> blocks ) {
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
                                /*for (Block bl:blocks) {
                                    if (bl.getBox().getX().intValue() == block.getBox().getX().intValue() && bl.getBox().isSolide()){
                                        if (bl.getBox().getY().intValue() == block.getBox().getY().intValue() + bl.getBox().getHeight()){
                                            this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());
                                            return 0 ;
                                        }
                                    }
                                }*/
                        this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());

                        this.setFalling(false);
                        return 1;

                    }
                }
            }
        }
        this.setFalling(true);
        return 0;
    }

    public void takeDomage(int damage){
        this.pv-- ;
        if (this.pv<=0){
            die();

        }
    }

    public abstract void die();


}
