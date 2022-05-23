package Terraria.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.omg.CORBA.PUBLIC_MEMBER;

public abstract class Acteur {
    private int pv;
    private int vitesse;
    private IntegerProperty posX,posY;
    private  int direction;

    private int multiplicateurSaut;
    private boolean isFalling;
    private boolean isJumping;

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

    public Acteur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox b ) {
        this.pv = pv;
        this.vitesse = vitesse;
        this.posX = new SimpleIntegerProperty(posX);
        this.posY = new SimpleIntegerProperty(posY);
        this.direction=0;
        this.statut = true;
        this.environnement = environnement;
        this.id=id;

        this.isFalling=false;
        this.multiplicateurSaut=10;
        this.isJumping=false;

        this.box = b ;


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
    public void seDeplace(){
        this.posX.setValue(posX.getValue()+(direction*vitesse*2));
        if (isFalling){
            this.posY.setValue(posY.getValue()+(1.5*vitesse));
        }

    }
    public void saute(){
        if (!isFalling){
            this.posY.setValue(posY.getValue()-1);
        }



    }
    public IntegerProperty getXProprety(){return posX;}

    public IntegerProperty getYProprety(){return posY;}


}
