package Terraria.vue.ViewObject;

import Terraria.modele.Environnement;
import Terraria.modele.Item.OnGroundItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class ViewItemOnGround extends ImageView {

    private OnGroundItem item;
    private Pane pane;
    private Environnement environnement;

    public ViewItemOnGround(OnGroundItem item, Pane pane, Environnement environnement , String img) {
        super();


        this.setImage(new Image(String.valueOf(getClass().getResource("/"+img))));
        this.item = item;
        this.pane = pane;
        this.environnement = environnement;
        pane.getChildren().add(this);
        this.setId(item.getId());
        this.translateXProperty().bind(item.getXProprety());
        this.translateYProperty().bind(item.getYProprety());





    }
}
