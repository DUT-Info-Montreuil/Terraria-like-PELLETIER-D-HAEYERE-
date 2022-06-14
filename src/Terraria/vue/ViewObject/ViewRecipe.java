package Terraria.vue.ViewObject;


import Terraria.modele.Item.Item;
import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Recipe;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ViewRecipe extends HBox {
    private Recipe recipe;
    private Joueur joueur;
    private Pane pane;

    public ViewRecipe(Recipe recipe,Joueur joueur,Pane pane) {
        int countCircle=1;
        this.recipe=recipe;
        this.joueur=joueur;
        this.pane=pane;
        this.setSpacing(10);
        for (Item i:recipe.getListItem()
             ) {
            Circle circle = new Circle(12 , Color.RED);

            circle.setId(String.valueOf(countCircle));
            countCircle++;
            this.getChildren().add(circle);

            circle.toFront();
        }
        Button buttonCraft = new Button(); //changer en rectangle avec eventFilter
        buttonCraft.setText("Craft");
        buttonCraft.setOnMouseReleased(e -> pane.requestFocus());
        this.getChildren().add(buttonCraft);
        pane.getChildren().add(this);
    }
}
