package Terraria.vue;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class MonObservateurTerrain implements ListChangeListener<Integer> {

    private Pane paneJeu;
    private HashMap mapImage;
    private EventHandler<MouseEvent> eventHandler;
    public MonObservateurTerrain(Pane paneJeu,HashMap mapImage,EventHandler<MouseEvent>eventHandler){
        super();
        this.paneJeu=paneJeu;
        this.mapImage=mapImage;
        this.eventHandler=eventHandler;
    }
    @Override
    public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
        while (c.next()){
//            System.out.println("test");
            for (Integer nouveau:c.getRemoved()
                 ) {
                Image test = new Image(String.valueOf(getClass().getResource("/air.jpg")));
                ImageView testIV = new ImageView(test);
                ImageView imageSupr = (ImageView) paneJeu.lookup("#"+c.getFrom());
                testIV.setX(imageSupr.getX());
                testIV.setY(imageSupr.getY());
                testIV.setId(imageSupr.getId());
                paneJeu.getChildren().remove(imageSupr);
                paneJeu.getChildren().add(testIV);
//              System.out.println("testremove");
//              System.out.println(c.getFrom());
                ImageView newIV = new ImageView((Image) mapImage.get(0));
                testIV.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

            }
//            for (Integer nouveau:c.getAddedSubList()
//                 ) {
//
//            }
        }
    }
}
