package ExoSup;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {


    @FXML
    private Rectangle afficheRectangle ;

    @FXML
    private StackPane pane ;
    @FXML
    void reactionBouton(ActionEvent event){



        ObservableList<Node> childs = pane.getChildren();

        if (childs.size() > 1) {
            // Top Component
            Node topNode = childs.get(childs.size()-1);
            topNode.toBack();
        }

        System.out.println("je suis clic");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    // aaa
    }
}
