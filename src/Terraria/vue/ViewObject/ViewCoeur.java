package Terraria.vue.ViewObject;

import Terraria.modele.UnCoeur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ViewCoeur {
    private ArrayList<ImageView> allCoeursImageView = new ArrayList<>();

    public  ViewCoeur(ArrayList<UnCoeur> allCoeurs, Pane pane){
        for(UnCoeur unCoeur : allCoeurs){
            ImageView imageCoeur = new ImageView(new Image(String.valueOf(getClass().getResource("/heinkeur.png"))));
            imageCoeur.setTranslateX(unCoeur.getX());
            imageCoeur.setTranslateY(unCoeur.getY());
            imageCoeur.setId("coeur"+unCoeur.getId());
            allCoeursImageView.add(imageCoeur);
        }
    }


    public void afficherCoeur(ArrayList<UnCoeur> allCoeurs , Pane pane){
        for (UnCoeur unCoeur: allCoeurs) {
            if (unCoeur.isAllume()) {
                if (pane.lookup("#coeur"+unCoeur.getId()) == null) {
                    for(ImageView imageCoeur : this.allCoeursImageView) {
                        if (imageCoeur.getId().equals("coeur"+unCoeur.getId())) {
                            imageCoeur.setTranslateX(unCoeur.getX());
                            imageCoeur.setTranslateY(unCoeur.getY());
                            pane.getChildren().add(imageCoeur);

                        }
                    }
                }

                }else{
                    if (pane.lookup("#coeur"+unCoeur.getId()) != null) {
                        pane.getChildren().remove(pane.lookup("#coeur"+unCoeur.getId()));
                    }
                }

            }
        }

    }


