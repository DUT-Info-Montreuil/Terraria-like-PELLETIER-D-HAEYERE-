package Terraria.vue.ObservableList;


import Terraria.modele.Acteur.Acteur;
import Terraria.modele.Acteur.Ennemi;
import Terraria.modele.Environnement;
import Terraria.vue.ViewObject.ViewActeur;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MonObservateurListActeur implements ListChangeListener<Acteur> {
    private Environnement environnement;
    private Pane pane;
    private EventHandler<MouseEvent> att;
    public MonObservateurListActeur(Environnement e1, Pane pane) {
        super();
        this.environnement=e1;
        this.pane=pane;

        att = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {



                ViewActeur imageClicked = (ViewActeur) e.getSource();
                String idActeur = (imageClicked.getId());




                for (int i = 0  ;  i < e1.getListActeur().size() ; i++ ){

                    if (idActeur.equals(e1.getListActeur().get(i).getId())){

                        if (e1.getListActeur().get(i) instanceof Ennemi){
                            e1.getListActeur().get(i).takeDomage(environnement.getJoueur1().getItemEquipe().getDegatSurMob());
                            System.out.println(environnement.getJoueur1().getItemEquipe().getDegatSurMob());
                        }
                    }
                }

                if (e1.getJoueur1().checkDistanceInReach((int) imageClicked.getX(), (int) imageClicked.getY())) {


                }
//
            }
        };
    }



    @Override
    public void onChanged(Change<? extends Acteur> c) {
        while (c.next()) {
            for (Acteur a : c.getAddedSubList()
            ) {

                ViewActeur viewActeur = new ViewActeur(a,pane,environnement);
                viewActeur.addEventFilter(MouseEvent.MOUSE_CLICKED, att);
            }
            for (Acteur a : c.getRemoved()) {
                pane.getChildren().remove(pane.lookup("#"+a.getId()));

            }

        }
    }
}
