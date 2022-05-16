package Terraria.controleur;

import Terraria.modele.Environnement;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Environnement e1 = new Environnement();

        e1.loadMap("ress/terrain2.json");
        e1.loadTileInfo();
        e1.loadTile() ;
        e1.loadLayers() ;
        afficheMap(e1);
    }

    private void afficheMap(Environnement e1) {
        JSONObject map = e1.getMap() ;
        JSONArray layers = (JSONArray) map.get("layers");
        JSONObject data = (JSONObject) layers.get(0) ;
        JSONArray miniData = (JSONArray) data.get("data");
        Map<Long ,String> info = e1.getInfotiles() ;

        int posX = 0 ;
        int posY = 0 ;
        int nbr = 0 ;
        ArrayList<Tile> listeTiles = e1.getListeTiles() ;
        for (Tile a :listeTiles
             ) {
            System.out.println("je suis une tile "+ a.getImage());
        }
        for (int i = 0; i <16 ; i++) {
            for (int j = 0; j < 16; j++) {
                try {
                    //"/"+info.get(miniData.get(nbr)))
                    ImageView imageView ;

                    for (Tile t :listeTiles) {

                        if (((Long) miniData.get(nbr)).intValue() == t.getId()){
                            imageView = new ImageView(t.getImage()) ;
                            imageView.setX(posX);
                            imageView.setY(posY);
                            pane.getChildren().add(imageView);
                            listImageView.add(imageView) ;
                            System.out.println("adding "+info.get(miniData.get(nbr))+" to the pane");
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
}