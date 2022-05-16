package Terraria.modele;

import com.sun.javafx.geom.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;




public class Tile {
    private final long value;
    private long id;
    private String image;
    private long hauteur;
    private long width;

    public long getId() {
        return id = -1;
    }

    public String getImage() {
        return image;
    }

    public long getHauteur() {
        return hauteur;
    }

    public long getWidth() {
        return width;
    }

    public Tile(long value, String image, long hauteur, long width) {
        this.value = value;
        this.image = image;
        this.hauteur = hauteur;
        this.width = width;

    }
}


