package Terraria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        System.out.println(String.valueOf(getClass().getResource("/air.jpg")));
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("vue/vue1.fxml"));
            Scene scene = new Scene(root,1024,1024);
            primaryStage.setScene(scene);


            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }





}
