package Terraria.vue;

import Terraria.modele.Acteur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ViewActor extends ImageView {
    private Acteur a;
    private Image img;
    private Pane pane;

    public ViewActor(Acteur a, Image img, Pane pane) {
        super(img);
        this.a = a;
        this.img = img;
        this.pane = pane;
        pane.getChildren().add(this);
    }

    public Acteur getA() {
        return a;
    }

    public Image getImg() {
        return img;
    }

    public Pane getPane() {
        return pane;
    }
}
