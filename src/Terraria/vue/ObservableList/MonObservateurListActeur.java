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

//                e1.terrainToString();

                ViewActeur imageClicked = (ViewActeur) e.getSource();
                String idActeur = (imageClicked.getId());
                System.out.println("acteur clisk");
                System.out.println(e1.getJoueur1().checkDistanceInReach((int) imageClicked.getX(), (int) imageClicked.getY()));


                for (int i = 0  ;  i < e1.getListActeur().size() ; i++ ){
                    System.out.println("in the boucle :)");
                    if (idActeur.equals(e1.getListActeur().get(i).getId())){
                        System.out.println("id found");
                        if (e1.getListActeur().get(i) instanceof Ennemi){
                            e1.getListActeur().get(i).takeDomage(2);
                            System.out.println("domage take ;<");
                        }
                    }
                }

                if (e1.getJoueur1().checkDistanceInReach((int) imageClicked.getX(), (int) imageClicked.getY())) {
                    System.out.println("in range ");

                }
//                    System.out.println(e1.getTerrain());
            }
        };
    }



    @Override
    public void onChanged(Change<? extends Acteur> c) {
        while (c.next()) {
            for (Acteur a : c.getAddedSubList()
            ) {
                System.out.println("test viewActeur");
                ViewActeur viewActeur = new ViewActeur(a,pane,environnement);
                viewActeur.addEventFilter(MouseEvent.MOUSE_CLICKED, att);
            }
            for (Acteur a : c.getRemoved()) {
                pane.getChildren().remove(pane.lookup("#"+a.getId()));

            }

        }
    }
}
