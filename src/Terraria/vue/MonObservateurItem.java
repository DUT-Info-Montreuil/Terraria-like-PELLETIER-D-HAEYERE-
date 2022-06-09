package Terraria.vue;

import Terraria.modele.Environnement;
import Terraria.modele.Item;
import Terraria.modele.OnGroundItem;
import Terraria.modele.RottenFlesh;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class MonObservateurItem implements ListChangeListener<OnGroundItem> {
    private Environnement environnement;
    private Pane pane;


    public MonObservateurItem(Environnement e1, Pane pane) {
        super();
        this.environnement=e1;
        this.pane=pane;
    }




    @Override
    public void onChanged(Change<? extends OnGroundItem> c) {
        while (c.next()) {
            for (OnGroundItem i : c.getAddedSubList()
            ) {

                if (i.getItem() instanceof RottenFlesh){
                    ViewItemOnGround viewItemOnGround = new ViewItemOnGround(i,pane,environnement,"RottenFlesh.png");
                }



            }
            for (Item i : c.getRemoved()){
                pane.getChildren().remove(pane.lookup("#"+i.getId()));
            }

        }
    }
}
