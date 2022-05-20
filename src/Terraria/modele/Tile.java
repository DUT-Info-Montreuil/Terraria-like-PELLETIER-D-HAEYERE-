package Terraria.modele;

import javafx.scene.image.Image;


public class Tile {
    private HitBox box;
    private int id;
    private String imagePath;
    private int hauteur;
    private int width;

    public int getId() {return id;}


    public String getImagePath() {
        return imagePath;
    }

    public long getHauteur() {
        return hauteur;
    }

    public long getWidth() {
        return width;
    }

    public HitBox canCollide(){
        return box ;
    }


    public Tile(int id, String path, int hauteur, int width , HitBox hitBox ) {
        this.id = id+1;
        this.imagePath = path ;
        this.hauteur = hauteur;
        this.width = width;
        this.box = hitBox ;

    }
}


