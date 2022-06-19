package Terraria.vue.ViewObject;

import Terraria.modele.Environnement;
import Terraria.modele.Item.Item;
import Terraria.modele.Tile;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.HashMap;


public class ViewFenetreInv extends ImageView {

    private Pane pane;
    private Environnement environnement;
    private HashMap<Tile, Image> mapLienIdImage;
    private boolean afficheInv;

    public ViewFenetreInv(Pane pane, Environnement environnement, HashMap<Tile, Image> mapLienIdImage) {
        this.environnement = environnement;
        this.pane = pane;
        this.mapLienIdImage = mapLienIdImage;
        this.afficheInv = false;
        Image imgInv = new Image(String.valueOf(getClass().getResource("/inventaire.png")));
        this.setImage(imgInv);
        pane.getChildren().add(this);
        this.setVisible(false);
        this.setX(600);
        this.setY(10);
        this.setId("inv");
    }

    public void closeInv() {
        if (afficheInv) {
            this.afficheInv=false;
            for (Item i : environnement.getJoueur1().getInventaire()
            ) {

                ImageView itemDansInv = (ImageView) pane.lookup("#" + i.getId());
                pane.getChildren().remove(itemDansInv);
                i.removeItemFromInv();
            }
        }


    }

    public void affichageInventaire() {
        if (!afficheInv) {
            this.afficheInv=true;
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
                    environnement.getJoueur1().setItemEquipe(((ViewItem) imageClicked).getItem());

                }
            };
            for (Item i : environnement.getJoueur1().getInventaire()
            ) {
                System.out.println(i);
                System.out.println(i.getQuantite());
                if (i.getQuantite() != 0) {
                    try {
                        ViewItem itemInv = new ViewItem(i, pane, environnement, mapLienIdImage);

                        itemInv.toFront();
                        itemInv.setX(newPosX);
                        itemInv.setY(newPosY);

                        itemInv.setId(i.getId());

                        newPosX = newPosX + 37;
                        countCase++;

                        if (countCase % 5 == 0) {
                            newPosX = poseXDep;
                            newPosY = newPosY + 37;
                        }
                        itemInv.addEventFilter(MouseEvent.MOUSE_CLICKED, invHandler);
                        pane.getChildren().add(itemInv);
                    } catch (Exception e) {

                    }

                }
            }
        }


    }
    public void refreshInv(){
        System.out.println("test");

        if (afficheInv){
            System.out.println("dans affiche inv");
            this.closeInv();
            this.affichageInventaire();
        }
        pane.requestFocus();
    }
}
