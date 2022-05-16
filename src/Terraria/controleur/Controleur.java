package Terraria.controleur;

import Terraria.modele.Acteur;
import Terraria.modele.Environnement;
import Terraria.modele.Joueur;
import Terraria.modele.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.plaf.TableUI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private ArrayList<ImageView> listImageView = new ArrayList<>() ;
    @FXML
    private Pane pane;

    @FXML
    private BorderPane borderPane;
    public Environnement e1 ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         e1 = new Environnement();

        e1.loadMap("ress/terrain2.json");
        e1.loadTileInfo();
        e1.loadTile() ;
        e1.loadLayers() ;
        afficheMap(e1);
        Joueur hero = new Joueur(20,5,50,50,e1,"hero","persoIdle.png");
        ajoutSprite(hero);
    }

    private void afficheMap(Environnement e1) {
        JSONObject map = e1.getMap() ;
        JSONArray layers = (JSONArray) map.get("layers");
        JSONObject layer = (JSONObject) layers.get(0) ;
        JSONArray data = (JSONArray) layer.get("data");
        Map<Long ,String> info = e1.getInfotiles() ;

        int posX = 0 ;
        int posY = 0 ;
        int nbr = 0 ;
        ArrayList<Tile> listeTiles = e1.getListeTiles() ;
        for (Tile t :listeTiles
             ) {
            System.out.println("je suis une tile "+ t.getImage());
        }
        for (int i = 0; i <e1.getLargeur() ; i++) {
            for (int j = 0; j < e1.getHauteur(); j++) {
                try {

                    ImageView imageView ;

                    for (Tile t :listeTiles) {

                        if (((Long) data.get(nbr)).intValue() == t.getId()){
                            imageView = new ImageView(t.getImage()) ;
                            imageView.setX(posX);
                            imageView.setY(posY);
                            pane.getChildren().add(imageView);
                            listImageView.add(imageView) ;
                            System.out.println("adding "+info.get(data.get(nbr))+" to the pane");
                        }
                    }


                }
                catch (Exception e){
                    System.out.println("error :'(");
                }

                nbr++ ;
                posX += 16 ;


            }
            posX = 0 ;
            posY += 16 ;
        }
    }
    private void ajoutSprite(Acteur a){
        ImageView imageView = new ImageView(a.getSprite());
        pane.getChildren().add(imageView);
        imageView.translateXProperty().bind(a.getXProprety());
        imageView.translateYProperty().bind(a.getYProprety());
    }
}