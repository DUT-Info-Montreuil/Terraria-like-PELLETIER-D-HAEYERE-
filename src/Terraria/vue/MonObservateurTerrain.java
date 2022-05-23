package Terraria.vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class MonObservateurTerrain implements ListChangeListener<Integer> {

    private Pane paneJeu;
    private HashMap mapImage;
    public MonObservateurTerrain(Pane paneJeu,HashMap mapImage){
        super();
        this.paneJeu=paneJeu;
        this.mapImage=mapImage;
    }
    @Override
    public void onChanged(javafx.collections.ListChangeListener.Change<? extends Integer> c) {
        while (c.next()){
            System.out.println("test");
            for (Integer nouveau:c.getRemoved()
                 ) {
                Image test = new Image(String.valueOf(getClass().getResource("/air.jpg")));
                ImageView testIV = new ImageView(test);
                ImageView imageSupr = (ImageView) paneJeu.lookup("#"+c.getFrom());
                testIV.setX(imageSupr.getX());
                testIV.setY(imageSupr.getY());
                paneJeu.getChildren().remove(imageSupr);
                paneJeu.getChildren().add(testIV);
                System.out.println("testremove");
                System.out.println(c.getFrom());


                ImageView newIV = new ImageView((Image) mapImage.get(0));

            }
//            for (Integer nouveau:c.getAddedSubList()
//                 ) {
//
//            }
        }
    }
}
