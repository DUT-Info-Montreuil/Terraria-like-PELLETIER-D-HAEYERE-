package Terraria.vue;

import Terraria.modele.Acteur;
import Terraria.modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ViewActeur extends ImageView {
    private Acteur acteur;
    private Image img;
    private Pane pane;
    private Environnement environnement;

    public ViewActeur(Acteur acteur, Image img, Pane pane, Environnement environnement) {
        super(img);
        this.acteur = acteur;
        this.img = img;
        this.pane = pane;
        this.environnement = environnement;
        pane.getChildren().add(this);
        this.setId(acteur.getId());
        this.translateXProperty().bind(acteur.getXProprety());
        this.translateYProperty().bind(acteur.getYProprety());
    }
}
