package Terraria.controleur;


import Terraria.modele.Acteur;
import Terraria.modele.Environnement;
import Terraria.modele.Joueur;
import Terraria.modele.*;
import Terraria.vue.MonObservateurListActeur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import Terraria.modele.Tile;
import Terraria.vue.MonObservateurTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private ArrayList<ImageView> listImageView = new ArrayList<>();
    @FXML
    private Pane pane;
    private Timeline timeline;
    private Environnement e1;
    public final int sprit_hauteur = 16;


    public final int sprit_largeur = 16 ;
    private ArrayList<Block> allBlock = new ArrayList<>() ;
    private EventHandler<MouseEvent> eventHandler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        e1 = new Environnement(InitialisationEnvironnement.loadMap("ress/terrain3.json"));

        HashMap<Tile, Image> mapLienIdImage = loadTile(e1.getMap());

        this.e1.getListActeur().addListener(new MonObservateurListActeur(e1,pane));
        Joueur hero = new Joueur(20, 5, 50, -40, e1, "hero"  , new HitBox( 50 ,  30 ,24 , 14 , true ));
        e1.addActeur(hero);
   /* public final int sprit_largeur = 16;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        e1 = new Environnement(InitialisationEnvironnement.loadMap("ress/terrain2.json"));
        HashMap<Integer, Image> mapLienIdImage = loadTile(e1.getMap());
        Joueur hero = new Joueur(20, 5, 40, 40, e1, "hero");
        Image test = new Image(String.valueOf(getClass().getResource("/persoIdle.png")));
        ImageView testIV = new ImageView(test);
        pane.getChildren().add(testIV);
        testIV.setX(250);
        testIV.setY(250);

       */
         eventHandler = new EventHandler<MouseEvent>() {     //Initialisation eventHandler
                @Override
                public void handle(MouseEvent e) {
//                    System.out.println("Hello World");                  //Action quand cliqué
                    ImageView imageClicked = (ImageView) e.getSource();
                    e1.getTerrain().remove(Integer.parseInt(imageClicked.getId()));
                    e1.getTerrain().add(Integer.parseInt(imageClicked.getId()),0);
//                    System.out.println(e1.getTerrain());
                }
            } ;


        e1.setJoueur1(hero);
        Scene scene = new Scene(pane, e1.getLargeur() * sprit_largeur, e1.getHauteur() * sprit_hauteur);


        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);



        e1.loadLayers();
        afficheMap(e1,mapLienIdImage);
        afficherColision(allBlock ,hero , false);




        //ajoutSprite(hero);

        //System.out.println(pane.getScene().getHeight());

        pane.getScene().getCamera().layoutXProperty().bind(hero.getXProprety().subtract(pane.getScene().getWidth() / 2));
        pane.getScene().getCamera().layoutYProperty().bind(hero.getYProprety().subtract(pane.getScene().getHeight() / 2));

        this.e1.getTerrain().addListener(new MonObservateurTerrain(pane,mapLienIdImage,eventHandler));


        //Registering the event filter


        (pane.lookup("#"+e1.getJoueur1().getId())).toFront();
        launchTimeLine();
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();

    }


   

    public void launchTimeLine() {
        timeline = new Timeline(new KeyFrame(Duration.millis(32.66), actionEvent -> {

            if(e1.getJoueur1().getDirection() == 1 && e1.getJoueur1().collideGaucheDroite(allBlock) != 1  ){
                e1.getJoueur1().seDeplace();
            }else if (e1.getJoueur1().getDirection() == -1 && e1.getJoueur1().collideGaucheDroite(allBlock) != -1 ){
                e1.getJoueur1().seDeplace();
            }


            switch ( e1.getJoueur1().collideHautBas(allBlock)){
                case 0 :
                    e1.getJoueur1().setFalling(true);

                    break ;
                case 1:
                    e1.getJoueur1().setFalling(false);
                    break;
                case -1 :
                    break;

            }   e1.getJoueur1().gravite();


        }));


    }


    public HashMap<Tile,Image> loadTile(JSONObject map) {

        ArrayList<Tile> tiles = e1.getAllTiles() ;
        HashMap<Tile, Image> mapLienIdImage = new HashMap<Tile, Image>();
        for ( Tile t :tiles ) {

            Image image = new Image((String.valueOf(getClass().getResource("/" + t.getImagePath() ))));
            mapLienIdImage.put(t, image);


        }
        return mapLienIdImage;
    }


    private void afficheMap(Environnement e1, HashMap<Tile, Image> hashMapData) {
        ObservableList<Integer> listeTiles = e1.getTerrain();

        int posX = 0;
        int posY = 0;
        int nbr = 0;
//        System.out.println("-----------------------------------------");
//        System.out.println(listeTiles);
//        System.out.println("-----------------------------------------");
        ImageView imageView;
//        System.out.println(hashMapData);

        ArrayList<Tile> tiles  = e1.getAllTiles() ;


        for (int i = 0; i < e1.getHauteur(); i++) {
            for (int j = 0; j < e1.getLargeur(); j++) {
                for (Tile t :tiles ) {
                    if (t.getId() == listeTiles.get(nbr)) {
                        Block b = new Block(posX , posY , t) ;
                        imageView = new ImageView(hashMapData.get(t));
                        imageView.setX(posX);
                        imageView.setY(posY);
                        imageView.setId(Integer.toString(b.getId()));
                        allBlock.add(b) ;
                        imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);



                        pane.getChildren().add(imageView);
                        listImageView.add(imageView);
                    }


                }


               


                nbr++;
                posX += 16;
               
            }
            posX = 0;
            posY += 16;
        }
    }

//    private void ajoutSprite(Acteur a) {
//        Image imageSpriteHero = new Image(String.valueOf(getClass().getResource("/persoIdle.png"))); // a modifier quand ajout d'autre acteur
//        ImageView imageViewSpriteHero = new ImageView(imageSpriteHero);
//        imageViewSpriteHero.setId(a.getId());
//        pane.getChildren().add(imageViewSpriteHero);
//        imageViewSpriteHero.translateXProperty().bind(a.getXProprety());
//        imageViewSpriteHero.translateYProperty().bind(a.getYProprety());
//
//
//    }

    @FXML
    public void mouvements(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case D:
                    e1.getJoueur1().setDirection(1);
                break;
            case Q:

                    e1.getJoueur1().setDirection(-1);


                break;
            case SPACE:
                if (!e1.getJoueur1().isJumping()) {
                    e1.getJoueur1().saute();
                }
        }
    }

    @FXML
    public void stopMouvement(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case D:
                e1.getJoueur1().setDirection(0);
                break;
            case Q:
                e1.getJoueur1().setDirection(0);
                break;

        }
    }

    public void afficherColision(ArrayList<Block> blocks , Acteur a , boolean affiche ){
        if (affiche){
//            System.out.println(blocks.size());
            Rectangle rec = new Rectangle(a.getBox().getX().intValue() , a.getBox().getY().intValue() , a.getBox().getWidth(), a.getBox().getHeight()) ;
            rec.setFill(Color.TRANSPARENT);
            rec.setStroke(Color.RED);
            rec.xProperty().bind(a.getBox().getX());
            rec.yProperty().bind(a.getBox().getY());
            pane.getChildren().add(rec) ;
            for (Block b: blocks) {
                Rectangle r = new Rectangle(b.getBoxX().intValue() , b.getBoxY().intValue() , b.getTile().getWidth(), b.getTile().getHauteur()) ;
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                r.setX(b.getBoxX().intValue());
                r.setY(b.getBoxY().intValue());
                pane.getChildren().add(r) ;




            }





        }

    }


}