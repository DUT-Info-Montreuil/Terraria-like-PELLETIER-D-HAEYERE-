package Terraria.modele;


import javafx.beans.property.SimpleIntegerProperty;

public class HitBox {
    private boolean isSolide ;
    private int height ;
    private int width ;
    private SimpleIntegerProperty x ;
    private SimpleIntegerProperty y ;

    public boolean isSolide() {
        return isSolide;
    }

    public int getHeight() {
        return height;
    }



    public SimpleIntegerProperty getX() {
        return x;
    }

    public SimpleIntegerProperty getY() {
        return y;
    }

    public void setY(int y) {
        this.y = new SimpleIntegerProperty(y);
    }

    public int getWidth() {
        return width;
    }
    public void setX(int x) {
        this.x =  new SimpleIntegerProperty(x) ;

    }
    public void inversionHitBox(){
        this.isSolide=(!this.isSolide);
    }

    public  HitBox (int x ,int  y , int height , int width , boolean solide){
        this.isSolide = solide ;
        this.height = height ;
        this.width = width ;
        this.x = new SimpleIntegerProperty(x) ;
        this.y = new SimpleIntegerProperty(y) ;

    }
}
