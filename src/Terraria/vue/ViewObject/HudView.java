package Terraria.vue.ViewObject;

import Terraria.modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HudView extends ImageView {
    private Environnement environnement;
    private int x;
    private int y;


    public HudView(Environnement environnement , int x  , int y , Pane pane  , Environnement e1){
        this.x = x ;
        this.y = y ;
        this.environnement = environnement ;
        this.setImage(new Image(String.valueOf(getClass().getResource("/hud.png"))));
        pane.getChildren().add(this);
        this.toFront();
        this.setX(x);
        this.setY(y);


    }





}
