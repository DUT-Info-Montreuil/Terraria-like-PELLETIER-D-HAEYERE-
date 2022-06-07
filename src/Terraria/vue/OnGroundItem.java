package Terraria.vue;

import Terraria.modele.Environnement;
import Terraria.modele.Item;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class OnGroundItem extends Item {
    private SimpleIntegerProperty x;
    private SimpleIntegerProperty y;

    public OnGroundItem(Item item, Environnement environnement , int x , int y) {
        super(item.getQuantite(), item.getId(), item.isConsomable(), environnement);

        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public SimpleIntegerProperty getXProprety() {
        return x   ;
    }

    public SimpleIntegerProperty getYProprety() {
        return y   ;
    }
}
