package Terraria.vue;

import Terraria.modele.Environnement;
import Terraria.modele.Item;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class monObservateurItem implements ListChangeListener<Item> {
    private Environnement environnement;
    private Pane pane;


    @Override
    public void onChanged(Change<? extends Item> c) {
        while (c.next()) {
            for (Item i : c.getAddedSubList()
            ) {
                System.out.println("test viewActeur");
                ViewItem viewItem = new ViewItem(i,pane,environnement);

            }
            for (Item a : c.getRemoved()

            ) {

            }

        }
    }
}
