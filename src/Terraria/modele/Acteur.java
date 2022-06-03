package Terraria.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;


public abstract class Acteur {
    private int pv;
    private int vitesse;
    private IntegerProperty posX,posY;
    private  int direction;
    private ArrayList<Item>inventaire;
    private int multiplicateurSaut;
    private boolean isFalling;
    private boolean isJumping;
    private int reach;
    private HitBox box ;


    public void setDirection(int direction) {
        this.direction = direction;
    }

    private String id;
    private boolean statut;

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

    public Acteur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox b ) {
        this.pv = pv;
        this.vitesse = vitesse;
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
        this.direction=0;
        this.statut = true;
        this.environnement = environnement;
        this.id=id;
        this.isFalling=true;
        this.multiplicateurSaut=10;
        this.isJumping=false;
        this.box = b ;
        this.inventaire = new ArrayList<Item>();
        this.reach=4;
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

    public boolean getStatut() {
        return statut;
    }

    public void setPosX(int posX) {
        this.posX.set(posX);
    }

    public void setPosY(int posY) {
        this.posY.set(posY);
    }

    public void seDeplace(){
        this.posX.setValue(posX.getValue()+(direction*vitesse*2));
        if (isFalling){
            this.posY.setValue(posY.getValue()+(1.5*vitesse));
        }


    }
    public void addItem(Item item){
        inventaire.add(item);
    }
    public ArrayList<Item> getInventaire() {
        return inventaire;
    }

    public void gravite(){
        if (isFalling){
            this.posY.setValue(posY.getValue()+(vitesse));
        }
    }
    public void saute(){
        if (!isFalling){
            this.posY.setValue(posY.getValue()-90);
        }



    }
    public IntegerProperty getXProprety(){return posX;}

    public IntegerProperty getYProprety(){return posY;}

    public int getReach() {
        return reach;
    }
}
