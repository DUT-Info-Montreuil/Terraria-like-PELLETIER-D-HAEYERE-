package Terraria.modele;

import javafx.beans.property.SimpleIntegerProperty;

public class Block {


    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getTile() {
        return tile;
    }

    private Tile tile;
    private  int  id  ;
    private static int idCount = 0 ;
    private final int offSet = 10 ;
    private HitBox box ;

    public int getOffSet() {
        return offSet;
    }

    public Block (int x , int y , Tile t){
        this.x = x ;
        this.y = y ;
        this.tile = t ;
        this.id = idCount++ ;
        this.box = new HitBox (x + this.getTile().getBox().getX().intValue() , y + this.getTile().getBox().getY().intValue() , this.getTile().getBox().getHeight() ,  this.getTile().getBox().getWidth() , this.getTile().getBox().isSolide()) ;
    }


    public int getId() {
        return id;
    }

    public HitBox getBox(){
        return box ;
    }

    public SimpleIntegerProperty getBoxX() {return this.box.getX() ;}
    public SimpleIntegerProperty getBoxY() {
        return this.box.getY() ;
    }

}
