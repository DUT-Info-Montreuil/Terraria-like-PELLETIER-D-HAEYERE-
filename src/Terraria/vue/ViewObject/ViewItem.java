package Terraria.vue.ViewObject;

import Terraria.modele.*;
import Terraria.modele.Item.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ViewItem extends ImageView {

    private Item item;
    private Pane pane;
    private Environnement environnement;
    private HashMap<Tile,Image>hashMap;

    public ViewItem(Item i, Pane pane, Environnement environnement,HashMap<Tile,Image>hashMap) {


        this.item = i;
        this.pane = pane;
        this.environnement = environnement;
        pane.getChildren().add(this);
        this.setId(item.getId());
        this.hashMap=hashMap;
        if (item instanceof Pioche){
            this.setImage(new Image(String.valueOf(getClass().getResource("/pioche.png"))));
        }
        if (item instanceof Hache){
            this.setImage(new Image(String.valueOf(getClass().getResource("/hache.png"))));
        }if (item instanceof ItemBlock){
            for (Tile t:environnement.getAllTiles()
                 ) {
                if (t.getId()==((ItemBlock) i).getCode()){
                    this.setImage(hashMap.get(t));
                }
            }

        }if (item instanceof RottenFlesh) {
            this.setImage(new Image(String.valueOf(getClass().getResource("/RottenFlesh.png"))));
        }


    }

    public Item getItem() {
        return item;
    }

    public Pane getPane() {
        return pane;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }
}
