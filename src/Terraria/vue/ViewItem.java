package Terraria.vue;

import Terraria.modele.Environnement;
import Terraria.modele.Item;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ViewItem extends ImageView {

    private Item item;
    private Pane pane;
    private Environnement environnement;

    public ViewItem(Item i, Pane pane, Environnement environnement) {
        super();

        this.item = i;
        this.pane = pane;
        this.environnement = environnement;
        pane.getChildren().add(this);
        this.setId(item.getId());



    }
}
