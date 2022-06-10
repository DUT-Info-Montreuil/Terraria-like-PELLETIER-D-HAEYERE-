package Terraria.vue;

import Terraria.modele.Acteur;
import Terraria.modele.Environnement;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;


public class MonObservateurListActeur implements ListChangeListener<Acteur> {
    private Environnement environnement;
    private Pane pane;
    public MonObservateurListActeur(Environnement e1, Pane pane) {
        super();
        this.environnement=e1;
        this.pane=pane;
    }

    @Override
    public void onChanged(Change<? extends Acteur> c) {
        while (c.next()) {
            for (Acteur a : c.getAddedSubList()
            ) {
                System.out.println("test viewActeur");
                ViewActeur viewActeur = new ViewActeur(a,pane,environnement);

            }
            for (Acteur a : c.getRemoved()) {
                pane.getChildren().remove(pane.lookup("#"+a.getId()));

            }

        }
    }
}
