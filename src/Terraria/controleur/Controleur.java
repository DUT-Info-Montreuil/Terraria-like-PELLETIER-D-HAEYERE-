package Terraria.controleur;


import Terraria.modele.*;
import Terraria.vue.MonObservateurListActeur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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


    public final int sprit_largeur = 16;

    private EventHandler<MouseEvent> eventHandler;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        e1 = new Environnement(InitialisationEnvironnement.loadMap("ress/terrain3.json"));

        HashMap<Tile, Image> mapLienIdImage = loadTile(e1.getMap());

        this.e1.getListActeur().addListener(new MonObservateurListActeur(e1, pane));
        Pioche piocheDep = new Pioche("piocheDep", 250, 10, e1);
        Joueur hero = new Joueur(20, 5, 50, -40, e1, "hero", new HitBox(50, 30, 24, 14, true), piocheDep);
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


        /*MOUSE EVENT*/

        eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

//                e1.terrainToString();

                ImageView imageClicked = (ImageView) e.getSource();
                int idDansLeTerrain = Integer.parseInt(imageClicked.getId());
                int typeDeLaTile = e1.getTerrain().get(idDansLeTerrain);

                if (e1.getJoueur1().checkDistanceInReach((int) imageClicked.getX(), (int) imageClicked.getY())) {

                    e1.getJoueur1().getItemEquipe().action(idDansLeTerrain);
                    //System.out.println(idDansLeTerrain+"id avant le if");
                    //System.out.println(typeDeLaTile+"valeur à lid avant le if");
                    //System.out.println(e1.getJoueur1().getItemEquipe().cielEstModifiable(typeDeLaTile));
                    if (e1.getJoueur1().getItemEquipe().cielEstModifiable(typeDeLaTile)){
                        System.out.println("est ce que c'est du ciel ?");
                        modifTerrain(mapLienIdImage, imageClicked, Integer.parseInt(imageClicked.getId()));
                    }

//                        e1.terrainToString();
                }


//                    System.out.println(e1.getTerrain());
            }
        };


        e1.setJoueur1(hero);
        Scene scene = new Scene(pane, e1.getLargeur() * sprit_largeur, e1.getHauteur() * sprit_hauteur);


        ParallelCamera camera = new ParallelCamera();
        scene.setCamera(camera);


        e1.loadLayers();
        afficheMap(e1, mapLienIdImage);
        afficherColision(e1.getAllBlock(), hero, false);


        //ajoutSprite(hero);

        //System.out.println(pane.getScene().getHeight());

        pane.getScene().getCamera().layoutXProperty().bind(hero.getXProprety().subtract(pane.getScene().getWidth() / 2));
        pane.getScene().getCamera().layoutYProperty().bind(hero.getYProprety().subtract(pane.getScene().getHeight() / 2));


        //Registering the event filter


        (pane.lookup("#" + e1.getJoueur1().getId())).toFront();
        launchTimeLine();
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();

    }


    public void launchTimeLine() {
        ArrayList<Block> allBlock = e1.getAllBlock();
        timeline = new Timeline(new KeyFrame(Duration.millis(32.66), actionEvent -> {

            if (e1.getJoueur1().getDirection() == 1 && e1.getJoueur1().collideGaucheDroite(allBlock) != 1) {
                e1.getJoueur1().seDeplace();
            } else if (e1.getJoueur1().getDirection() == -1 && e1.getJoueur1().collideGaucheDroite(allBlock) != -1) {
                e1.getJoueur1().seDeplace();
            }


            switch (e1.getJoueur1().collideHautBas(allBlock)) {
                case 0:
                    e1.getJoueur1().setFalling(true);

                    break;
                case 1:
                    e1.getJoueur1().setFalling(false);
                    break;
                case -1:
                    break;

            }
            e1.getJoueur1().gravite();


        }));


    }


    public HashMap<Tile, Image> loadTile(JSONObject map) {

        ArrayList<Tile> tiles = e1.getAllTiles();
        HashMap<Tile, Image> mapLienIdImage = new HashMap<Tile, Image>();
        for (Tile t : tiles) {

            Image image = new Image((String.valueOf(getClass().getResource("/" + t.getImagePath()))));
            mapLienIdImage.put(t, image);


        }
        return mapLienIdImage;
    }


    private void afficheMap(Environnement e1, HashMap<Tile, Image> hashMapData) {
        ArrayList<Integer> listeTiles = e1.getTerrain();

        int posX = 0;
        int posY = 0;
        int nbr = 0;
//        System.out.println("-----------------------------------------");
//        System.out.println(listeTiles);
//        System.out.println("-----------------------------------------");
        ImageView imageView;
//        System.out.println(hashMapData);

        ArrayList<Tile> tiles = e1.getAllTiles();


        for (int i = 0; i < e1.getHauteur(); i++) {
            for (int j = 0; j < e1.getLargeur(); j++) {
                for (Tile t : tiles) {
                    if (t.getId() == listeTiles.get(nbr)) {
                        Block b = new Block(posX, posY, t);

                        imageView = new ImageView(hashMapData.get(t));
                        imageView.setX(posX);
                        imageView.setY(posY);
                        imageView.setId(Integer.toString(b.getId()));
                        e1.addBlock(b);
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

    public void afficherColision(ArrayList<Block> blocks, Acteur a, boolean affiche) {
        if (affiche) {
//            System.out.println(blocks.size());
            Rectangle rec = new Rectangle(a.getBox().getX().intValue(), a.getBox().getY().intValue(), a.getBox().getWidth(), a.getBox().getHeight());
            rec.setFill(Color.TRANSPARENT);
            rec.setStroke(Color.RED);
            rec.xProperty().bind(a.getBox().getX());
            rec.yProperty().bind(a.getBox().getY());
            pane.getChildren().add(rec);
            for (Block b : blocks) {
                Rectangle r = new Rectangle(b.getBoxX().intValue(), b.getBoxY().intValue(), b.getTile().getWidth(), b.getTile().getHauteur());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                r.setX(b.getBoxX().intValue());
                r.setY(b.getBoxY().intValue());
                pane.getChildren().add(r);


            }


        }

    }

    public void modifTerrain(HashMap<Tile, Image> mapLienIdImage, ImageView imageClicked, int id) {
        System.out.println("testDestruction");
        imageClicked.removeEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        pane.getChildren().remove(imageClicked);
        for (Tile t : e1.getAllTiles()
        ) {

            if (t.getId() == e1.getTerrain().get(id)) {
                ImageView newTile = new ImageView(mapLienIdImage.get(t));
                newTile.setId(imageClicked.getId());
                newTile.setX(imageClicked.getX());
                newTile.setY(imageClicked.getY());
                pane.getChildren().add(newTile);
                newTile.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                newTile.toBack();
            }
        }

    }


}