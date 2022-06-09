package Terraria.controleur;


import Terraria.modele.*;
import Terraria.vue.MonObservateurItem;
import Terraria.vue.MonObservateurListActeur;
import Terraria.vue.ViewItem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private KeyHandler keyHandler;
    private boolean affiche = false;
    private HashMap<Tile, Image> mapLienIdImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        e1 = new Environnement(InitialisationEnvironnement.loadMap("ress/terrain3.json"));

        mapLienIdImage = loadTile(e1.getMap());


        Pioche piocheDep = new Pioche(250, 10, e1);
        this.e1.getListActeur().addListener(new MonObservateurListActeur(e1, pane));
        this.e1.getOnGroundItem().addListener(new MonObservateurItem(e1, pane));
        Joueur hero = new Joueur(20, 5, 50, -40, e1, "hero", new HitBox(50, 30, 24, 14, true), piocheDep);
        Zombie z = new Zombie(20, 5, 30, 30, e1, "Zombie", new HitBox(50, 30, 28, 16, true));
        e1.addActeur(hero);
        e1.addActeur(z);
        e1.addEnnemi(z);


        Image imgInv = new Image(String.valueOf(getClass().getResource("/inventaire.png")));
        ImageView imgViewInv = new ImageView(imgInv);
        pane.getChildren().add(imgViewInv);
        imgViewInv.setVisible(false);
        imgViewInv.setX(600);
        imgViewInv.setY(10);
        imgViewInv.setId("inv");
//

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
                System.out.println(e1.getJoueur1().getInventaire());
//                e1.terrainToString();

                ImageView imageClicked = (ImageView) e.getSource();
                int idDansLeTerrain = Integer.parseInt(imageClicked.getId());

                int typeDeLaTile = e1.getTerrain().get(idDansLeTerrain);


                if (e1.getJoueur1().checkDistanceInReach((int) imageClicked.getX(), (int) imageClicked.getY())) {

                    e1.getJoueur1().getItemEquipe().action(idDansLeTerrain);
                    if (e1.getJoueur1().getItemEquipe().cielEstModifiable(typeDeLaTile)) {
                        System.out.println("est ce que c'est du ciel ?");
                        modifTerrain(mapLienIdImage, imageClicked, Integer.parseInt(imageClicked.getId()));
                        for (Item i : e1.getJoueur1().getInventaire()
                        ) {
                            if (i instanceof ItemBlock) {
                                ItemBlock block = (ItemBlock) i;
                                if (block.getCode() == typeDeLaTile) {
                                    i.quantiteEnPlus();
                                }
                            }
                        }


                    }

//                        e1.terrainToString();
                }


//                    System.out.println(e1.getTerrain());
            }
        };


        e1.setJoueur1(hero);
        e1.getJoueur1().addItem(piocheDep);
        Scene scene = new Scene(pane, e1.getLargeur() * sprit_largeur, e1.getHauteur() * sprit_hauteur);


        ParallelCamera camera = new ParallelCamera();
        pane.getScene().setCamera(camera);


        e1.loadLayers();


        afficheMap(e1, mapLienIdImage);
        afficherColision(e1.getAllBlock(), hero, e1.getListActeur(), e1.getOnGroundItem(), false);


        //ajoutSprite(hero);

        //System.out.println(pane.getScene().getHeight());

        // pane.getScene().getCamera().layoutXProperty().bind(hero.getXProprety().subtract(pane.getScene().getWidth() / 2));
        //pane.getScene().getCamera().layoutYProperty().bind(hero.getYProprety().subtract(pane.getScene().getHeight() / 2));


        keyHandler = new KeyHandler(pane);
        keyHandler.start();


        //Registering the event filter


        for (Acteur a : e1.getListActeur()) {
            pane.lookup("#" + a.getId()).toFront();
        }
        (pane.lookup("#" + e1.getJoueur1().getId())).toFront();
        imgViewInv.toFront();
        launchTimeLine();
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();

    }


    public void launchTimeLine() {

        ArrayList<Block> allBlock = e1.getAllBlock();

        timeline = new Timeline(new KeyFrame(Duration.millis(32.66), actionEvent -> {

            if (keyHandler.isInventoryTyped() && !affiche) {
                pane.lookup("#inv").setVisible(true);
                affiche = true;
                affichageInventaire();
            }
            if (!keyHandler.isInventoryTyped() && affiche) {
                pane.lookup("#inv").setVisible(false);
                affiche = false;
                closeInv();
            }
            if (keyHandler.isLeftPressed()) {
                e1.getJoueur1().setDirection(-1);
            }
            if (keyHandler.isRightPressed()) {
                e1.getJoueur1().setDirection(1);
            }
            if (keyHandler.isUpPressed()) {
                if (!e1.getJoueur1().isJumping()) {
                    e1.getJoueur1().saute();
                }
            }
            if (e1.getJoueur1().getDirection() == -1 && !keyHandler.isLeftPressed()) {
                e1.getJoueur1().setDirection(0);
            } else if (e1.getJoueur1().getDirection() == 1 && !keyHandler.isRightPressed()) {
                e1.getJoueur1().setDirection(0);
            }


            if (e1.getJoueur1().getDirection() == 1 && e1.getJoueur1().collideGaucheDroite(allBlock) != 1) {
                e1.getJoueur1().seDeplace();
            } else if (e1.getJoueur1().getDirection() == -1 && e1.getJoueur1().collideGaucheDroite(allBlock) != -1) {
                e1.getJoueur1().seDeplace();
            }

            for (Acteur a : e1.getListActeur()) {
                a.collideHautBas(allBlock);
                a.gravite();
            }

            for (int i = 0; i < e1.getListEnnemi().size(); i++) {
                e1.getListEnnemi().get(i).seDeplace(e1.getJoueur1(), e1.getAllBlock());
            }

            for (int i = 0; i < e1.getOnGroundItem().size(); i++) {
                e1.getOnGroundItem().get(i).collideHautBas(allBlock);
                e1.getOnGroundItem().get(i).gravite();
            }


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

                        imageView.toBack();


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

    /*
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
                break;
            case P:
                e1.getJoueur1().setItemEquipe(e1.getJoueur1().getInventaire().get(0));
                break;
            case L:
                e1.getJoueur1().setItemEquipe(e1.getJoueur1().getInventaire().get(1));
                break;
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
    }*/


    public void afficherColision(ArrayList<Block> blocks, Acteur a, ObservableList<Acteur> allActeur, ObservableList<OnGroundItem> allOngroundItem, boolean affiche) {
        if (affiche) {
//            System.out.println(blocks.size());

            for (Block b : blocks) {
                Rectangle r = new Rectangle(b.getBoxX().intValue(), b.getBoxY().intValue(), b.getTile().getWidth(), b.getTile().getHauteur());

                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.RED);
                r.setX(b.getBoxX().intValue());
                r.setY(b.getBoxY().intValue());
                pane.getChildren().add(r);

            }
            for (Acteur a1 : allActeur) {
                Rectangle r = new Rectangle(a1.getBox().getX().intValue(), a1.getBox().getY().intValue(), a1.getBox().getWidth(), a1.getBox().getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.YELLOW);
                r.xProperty().bind(a1.getBox().getX());
                r.yProperty().bind(a1.getBox().getY());
                pane.getChildren().add(r);

            }


            for (OnGroundItem a1 : allOngroundItem) {
                Rectangle r = new Rectangle(a1.getBox().getX().intValue(), a1.getBox().getY().intValue(), a1.getBox().getWidth(), a1.getBox().getHeight());
                r.setFill(Color.TRANSPARENT);
                r.setStroke(Color.BLUE);
                r.xProperty().bind(a1.getBox().getX());
                r.yProperty().bind(a1.getBox().getY());
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

    public void affichageInventaire() {
        ImageView inventaire = (ImageView) pane.lookup("#inv");
        int countCase = 1;
        int poseXDep = (int) inventaire.getX() + 30;
        int poseYDep = (int) inventaire.getY() + 17;
        int newPosX = poseXDep;
        int newPosY = poseYDep;
        EventHandler<MouseEvent> invHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView imageClicked = (ImageView) event.getSource();
                e1.getJoueur1().setItemEquipe(((ViewItem) imageClicked).getItem());

            }
        };
        for (Item i : e1.getJoueur1().getInventaire()
        ) {
            if (i.getQuantite() != 0) {
                try {
                    ViewItem itemInv = new ViewItem(i, pane, e1, mapLienIdImage);

                    itemInv.toFront();
                    itemInv.setX(newPosX);
                    itemInv.setY(newPosY);

                    itemInv.setId(i.getId());

                    newPosX = newPosX + 37;
                    countCase++;
                    System.out.println(countCase);
                    if (countCase % 5 == 0) {
                        newPosX = poseXDep;
                        newPosY = newPosY + 37;
                    }
                    itemInv.addEventFilter(MouseEvent.MOUSE_CLICKED, invHandler);
                    System.out.println("avant add");
                    pane.getChildren().add(itemInv);
                    System.out.println("apreès add");
                } catch (Exception e) {

                }

            }
        }

    }

    public void closeInv() {
        for (Item i : e1.getJoueur1().getInventaire()
        ) {

            ImageView itemDansInv = (ImageView) pane.lookup("#" + i.getId());
            pane.getChildren().remove(itemDansInv);

        }

    }


}