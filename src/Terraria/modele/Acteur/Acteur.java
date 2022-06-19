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
        for (Item value : inventaire) {
            if (!(value instanceof ItemBlock)) {
                Class c = value.getClass();
                if (c.equals(item.getClass())) {
                    value.quantiteEnPlus();
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
        return -1;
    }


    public int collideHautBas(ArrayList<Block> blocks){
        return -1;
    }

    public void takeDomage(int damage){
        this.pv-- ;
        if (this.pv<=0){
            die();

        }
    }

    public abstract void die();
}
