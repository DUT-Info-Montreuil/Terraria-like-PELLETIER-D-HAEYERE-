package Terraria.vue;

import Terraria.modele.Acteur;
import Terraria.modele.Environnement;
import Terraria.modele.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ViewActeur extends ImageView {

    private Acteur acteur;
    private Pane pane;
    private Environnement environnement;

    public ViewActeur(Acteur acteur, Pane pane, Environnement environnement) {
        super();
        System.out.println("truc");
        this.acteur = acteur;
        this.pane = pane;
        this.environnement = environnement;
        pane.getChildren().add(this);
        this.setId(acteur.getId());
        this.translateXProperty().bind(acteur.getXProprety());
        this.translateYProperty().bind(acteur.getYProprety());
        System.out.println("wallo");
        if(acteur instanceof Joueur){
            System.out.println("test");


            Image imageSpriteHero = new Image(String.valueOf(getClass().getResource("/persoIdle.png")));

            this.setImage(imageSpriteHero);
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
