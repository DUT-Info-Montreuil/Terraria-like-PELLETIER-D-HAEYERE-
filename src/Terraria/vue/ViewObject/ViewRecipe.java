package Terraria.vue.ViewObject;


import Terraria.modele.Environnement;
import Terraria.modele.Item.Item;
import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Recipe;
import Terraria.modele.Tile;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


import java.util.HashMap;

public class ViewRecipe extends HBox {
    private Recipe recipe;
    private Joueur joueur;
    private Pane pane;
    private Environnement environnement;
    private HashMap<Tile, Image>hashMap;
    private Button buttonCraft;

    public ViewRecipe(Recipe recipe,Joueur joueur,Pane pane, Environnement environnement,HashMap<Tile,Image>hashMap) {
        int countItem=1;
        this.recipe=recipe;
        this.joueur=joueur;
        this.pane=pane;
        this.setSpacing(10);
        this.environnement=environnement;
        this.hashMap=hashMap;
        for (Item i:recipe.getListItem()
             ) {
            ViewItem viewItem = new ViewItem(i,pane,environnement,hashMap);

            viewItem.toFront();
            viewItem.setId("viewrep"+ countItem);
            this.getChildren().add(viewItem);
            countItem++;

        }
        buttonCraft = new Button();
        buttonCraft.setText("Craft");
        buttonCraft.setOnMouseReleased(e -> pane.requestFocus());

        this.getChildren().add(buttonCraft);
        pane.getChildren().add(this);

    }

    public void recipeIsCraftable(){
        recipe.isCraftable();
        if (recipe.getCraftable()){
            buttonCraft.setStyle("-fx-background-color: green;");
        }else {
            buttonCraft.setStyle("-fx-background-color: red;");
        }
    }
}
