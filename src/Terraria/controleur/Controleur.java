package Terraria.controleur;

import Terraria.modele.Environnement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private TilePane tilepane;

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Environnement e1 = new Environnement();

        e1.afficheMap(tilepane);
    }
}