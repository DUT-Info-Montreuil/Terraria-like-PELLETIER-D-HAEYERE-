package Terraria.modele;

import com.sun.javafx.geom.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;




public class Tile {
    private int id;
    private Image image;
    private int hauteur;
    private int width;

    public int getId() {return id;}


    public Image getImage() {
        return image;
    }

    public long getHauteur() {
        return hauteur;
    }

    public long getWidth() {
        return width;
    }



    public Tile(int id, String path, int hauteur, int width ) {
        this.id = id+1;
        this.image = new Image(String.valueOf(getClass().getResource("/"+path)));
        this.hauteur = hauteur;
        this.width = width;

    }
}


