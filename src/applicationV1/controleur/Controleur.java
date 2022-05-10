package applicationV1.controleur;

import applicationV1.modele.Acteur;
import applicationV1.modele.Environnement;
import applicationV1.modele.Loup;
import applicationV1.modele.Mouton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    ArrayList<Circle> listeShape ;

    Environnement terre ;

    @FXML
    private RadioButton loup;

    @FXML
    private ToggleGroup LoupAndMouton;

    @FXML
    private RadioButton mouton;

    @FXML
    private TextField nbrDActeur;

    @FXML
    private Slider prodLoup;

    @FXML
    private Slider prodMouton;

    @FXML
    private Slider idMaxMangeMouton;

    @FXML
    private TextField nbrDeTour;

    @FXML
    private Pane root;

    @FXML
    private Label nbrTour;

    @FXML
    private Label nbrVivant;

    @FXML
    private Label nbrLoup;

    @FXML
    private Label nbrMouton;


    @FXML
    private Label TourNaissance;

    @FXML
    private Label HPActeur;

    @FXML
    private Label IDActeur;

    @FXML
    private Label NombreDeCap;









    @FXML
    void jouerUnTour(ActionEvent event) {

        terre.unTour();
        //UpdatePos(listeShape , terre);
        //updateAffichage();
    }
    @FXML
    void plusieursTour(ActionEvent event){

        String resultToString  ;
        int resultToInt =0 ;
        try {
             resultToString =  (nbrDeTour.getCharacters().toString() );
             resultToInt = Integer.parseInt(resultToString) ;
        }catch (Exception e){

        }


        for (int i = 0; i < resultToInt; i++) {
            terre.unTour();

        }
            //UpdatePos(listeShape , terre);
            //updateAffichage();

    }



    @FXML
    void ajouterActeur(ActionEvent event){
        String resultToString  ;
        int resultToInt =0 ;
        try {
            resultToString =  (nbrDActeur.getCharacters().toString() );
            resultToInt = Integer.parseInt(resultToString) ;
        }catch (Exception e){

        }

        if (resultToInt != 0 ){
            Acteur monActeur = null;
            if (loup.isSelected()){

                for (int i = 0; i < resultToInt; i++) {

                     terre.ajouter(monActeur = new Loup(terre) );


                        //root.getChildren().remove(root)
                        Circle c = new Circle( 5.0, Color.BLACK);
                        c.setId(monActeur.getId());
                        c.setOnMouseClicked(ev -> monEvent(c));
                        listeShape.add(c);

                        root.getChildren().add(c);
                        c.translateXProperty().bind(monActeur.getXProperty());
                        c.translateYProperty().bind(monActeur.getYProperty());



                }
            }
            else {
                for (int i = 0; i < resultToInt; i++) {
                    terre.ajouter(monActeur = new Mouton(terre) );

                        Circle c = new Circle(monActeur.getX(), monActeur.getY(), 5.0, Color.WHITE);
                        listeShape.add(c);
                        c.setId(monActeur.getId());
                        c.setOnMouseClicked(ev -> monEvent(c));
                        root.getChildren().add(c);

                        c.translateXProperty().bind(monActeur.getXProperty());
                        c.translateYProperty().bind(monActeur.getYProperty());

                   
                }
            }




        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listeShape = new ArrayList<>() ;

        terre = new Environnement( 657 , 449 ) ;


        prodLoup.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Loup.setReproduction((double) prodLoup.getValue());
                System.out.println("la reproduction des loup est a "+Loup.getPourcentageRepro());
            }
        });

        prodMouton.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Mouton.setReproduction((double) prodMouton.getValue());
                System.out.println("la reproduction des moutons est a "+Mouton.getPourcentageRepro());
            }
        });
        idMaxMangeMouton.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Loup.setMaxMangerTour((int) idMaxMangeMouton.getValue());
                System.out.println("Le max de mouton qui peut etre mangé est a "+Loup.getMaxMangerTour());
            }
        });


    }


    private void removeCircle(ArrayList<Circle> listeCircle ){

            for (int i = 0; i < listeCircle.size(); i++) {
                root.getChildren().remove(listeCircle.get(i)) ;
            }
        }



    private void UpdatePos(ArrayList<Circle> listeCircle  , Environnement e){
        removeCircle(listeCircle);
        ObservableList<Acteur> a = e.getActeurs() ;
        for (int i = 0; i < a.size() ; i++) {
            Acteur monActeur = a.get(i);

            if (monActeur instanceof Mouton) {
                Circle c = new Circle(monActeur.getX(), monActeur.getY(), 5.0, Color.WHITE);
                listeCircle.add(c);
                c.setId(monActeur.getId());
                c.setOnMouseClicked(ev -> monEvent(c));
                root.getChildren().add(c);



            } else {
                //root.getChildren().remove(root)
                Circle c = new Circle(monActeur.getX(), monActeur.getY(), 5.0, Color.BLACK);
                c.setId(monActeur.getId());
                c.setOnMouseClicked(ev -> monEvent(c));
                listeCircle.add(c);

                root.getChildren().add(c);

            }

        }

    }






    private void monEvent(Circle c) {
        ObservableList<Acteur> listeA = terre.getActeurs() ;
        String id = c.getId();

        Acteur a = null ;
        for (int i = 0; i < listeA.size(); i++) {
            if (listeA.get(i).getId().equals(id))
                a = listeA.get(i);
        }
        if ((a instanceof Mouton)){
            System.out.println(id +" est un mouton");
        }

        if (a instanceof Loup){
            System.out.println(id +" est un loup");
        }

        TourNaissance.setText(String.valueOf(a.getTourNaissance()));

        HPActeur.setText(String.valueOf(a.getPv()));

        IDActeur.setText(String.valueOf(a.getId()));

        NombreDeCap.setText(String.valueOf(a.getNbrCapture()));


    }



    private void updateAffichage(){

        nbrVivant.setText(String.valueOf(terre.getActeurs().size()));
        ObservableList<Acteur> maListe = terre.getActeurs() ;
        int countMouton = 0 ;
        int countLoup = 0 ;
        for (int i = 0; i < terre.getActeurs().size() ; i++) {
            if (maListe.get(i) instanceof Mouton){
                countMouton++ ;
            }else
                countLoup++ ;

        }
        nbrLoup.setText(String.valueOf(countLoup)) ;
        nbrMouton.setText(String.valueOf(countMouton)) ;
        nbrTour.setText(String.valueOf(terre.getNbTours()));
    }
}




