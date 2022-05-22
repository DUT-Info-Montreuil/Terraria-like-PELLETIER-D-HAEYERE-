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

    public int getOffSet() {
        return offSet;
    }

    public Block (int x , int y , Tile t){
        this.x = x ;
        this.y = y ;
        this.tile = t ;
        this.id = idCount++ ;
    }


    public int getId() {
        return id;
    }

    public SimpleIntegerProperty getBoxX() {
        return this.getTile().getBox().getX() ;
    }
    public SimpleIntegerProperty getBoxY() {
        return this.getTile().getBox().getY() ;
    }

}
