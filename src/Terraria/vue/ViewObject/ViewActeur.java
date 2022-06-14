package Terraria.vue.ViewObject;

import Terraria.modele.Acteur.Acteur;
import Terraria.modele.Environnement;
import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Acteur.Zombie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ViewActeur extends ImageView {

    private Acteur acteur;
    private Pane pane;
    private Environnement environnement;

    public ViewActeur(Acteur acteur, Pane pane, Environnement environnement) {
        super();

        this.acteur = acteur;
        this.pane = pane;
        this.environnement = environnement;
        pane.getChildren().add(this);
        this.setId(acteur.getId());
        this.translateXProperty().bind(acteur.getXProprety());
        this.translateYProperty().bind(acteur.getYProprety());

        if(acteur instanceof Joueur){



            Image imageSpriteHero = new Image(String.valueOf(getClass().getResource("/persoIdle.png")));

            this.setImage(imageSpriteHero);
        }else if (acteur instanceof Zombie){
            System.out.println("test");
            Image imageZombie = new Image(String.valueOf(getClass().getResource("/Sprite-0027.png"))) ;

            this.setImage(imageZombie);


        }
        this.toFront();
    }

    public Acteur getActeur() {
        return acteur;
    }

    public Pane getPane() {
        return pane;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }
}
