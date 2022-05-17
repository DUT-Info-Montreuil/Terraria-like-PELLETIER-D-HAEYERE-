package Terraria.controleur;

import Terraria.modele.Acteur;
import Terraria.modele.Environnement;
import Terraria.modele.Joueur;
import Terraria.modele.Tile;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private ArrayList<ImageView> listImageView = new ArrayList<>();
    @FXML
    private Pane pane;

    Environnement e1;
    public final int sprit_hauteur = 16;
    public final int sprit_largeur = 16 ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        e1 = new Environnement();
        Joueur hero = new Joueur(20, 5, 16, 16, e1, "hero");
        e1.setJoueur1(hero);
        Scene scene = new Scene(pane, e1.getLargeur() *sprit_largeur, e1.getHauteur() * sprit_hauteur);

        /*
        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);
*/
        e1.loadMap("ress/terrain.json");
        e1.loadTileInfo();
        e1.loadTile();
        e1.loadLayers();
        afficheMap(e1);


        ajoutSprite(hero);
        System.out.println(pane.getScene().getHeight());
        /*
        pane.getScene().getCamera().layoutXProperty().bind(hero.getXProprety().subtract(new SimpleIntegerProperty((e1.getLargeur()*sprit_largeur)/2)));
        pane.getScene().getCamera().layoutYProperty().bind(hero.getYProprety().subtract(new SimpleIntegerProperty(((e1.getLargeur()*sprit_hauteur)/2)-24)));
*/

    }

    private void afficheMap(Environnement e1) {
        JSONObject map = e1.getMap();
        JSONArray layers = (JSONArray) map.get("layers");
        JSONObject layer = (JSONObject) layers.get(0);
        JSONArray data = (JSONArray) layer.get("data");
        Map<Long, String> info = e1.getInfotiles();

        int posX = 0;
        int posY = 0;
        int nbr = 0;
        ArrayList<Tile> listeTiles = e1.getListeTiles();
        for (Tile t : listeTiles
        ) {
            System.out.println("je suis une tile " + t.getImage());
        }
        for (int i = 0; i < e1.getLargeur(); i++) {
            for (int j = 0; j < e1.getHauteur(); j++) {
                try {

                    ImageView imageView;

                    for (Tile t : listeTiles) {

                        if (((Long) data.get(nbr)).intValue() == t.getId()) {
                            imageView = new ImageView(t.getImage());
                            imageView.setX(posX);
                            imageView.setY(posY);
                            pane.getChildren().add(imageView);
                            listImageView.add(imageView);
                            System.out.println("adding " + info.get(data.get(nbr)) + " to the pane");
                        }
                    }


                } catch (Exception e) {
                    System.out.println("error :'(");
                }

                nbr++;
                posX += 16;


            }
            posX = 0;
            posY += 16;
        }
    }

    private void ajoutSprite(Acteur a) {
        Image imageSpriteHero =new Image(String.valueOf(getClass().getResource("/persoIdle.png"))); // a modifier quand ajout d'autre acteur
        ImageView imageViewSpriteHero = new ImageView(imageSpriteHero);
        imageViewSpriteHero.setId(a.getId());
        pane.getChildren().add(imageViewSpriteHero);
        imageViewSpriteHero.translateXProperty().bind(a.getXProprety());
        imageViewSpriteHero.translateYProperty().bind(a.getYProprety());
    }

    @FXML
    public void mouvements(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case D:
                e1.getJoueur1().setDirection(1);
                e1.getJoueur1().seDeplace();
                break;
            case Q:
                e1.getJoueur1().setDirection(-1);
                e1.getJoueur1().seDeplace();
                break;

        }
    }
}