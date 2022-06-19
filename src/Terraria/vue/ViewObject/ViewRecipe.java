package Terraria.vue.ViewObject;


import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Environnement;
import Terraria.modele.Item.Item;
import Terraria.modele.Recipe;
import Terraria.modele.Tile;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ViewRecipe extends HBox {
    private Recipe recipe;
    private Joueur joueur;
    private Pane pane;
    private Environnement environnement;
    private HashMap<Tile, Image> hashMap;
    private Button buttonCraft;

    public ViewRecipe(Recipe recipe, Joueur joueur, Pane pane, Environnement environnement, HashMap<Tile, Image> hashMap) {
        ViewFenetreInv fenetreInv =(ViewFenetreInv) pane.lookup("#inv");
        int countItem = 1;
        this.recipe = recipe;
        this.joueur = joueur;
        this.pane = pane;
        this.setSpacing(10);
        this.environnement = environnement;
        this.hashMap = hashMap;
        for (Item i : recipe.getListItem()
        ) {
            ViewItem viewItem = new ViewItem(i, pane, environnement, hashMap);

            viewItem.toFront();
            viewItem.setId("viewrep" + countItem);
            this.getChildren().add(viewItem);
            countItem++;

        }
        buttonCraft = new Button();
        buttonCraft.setText("Craft");
        buttonCraft.setOnMouseClicked(e -> this.recipe.craftItem());
        buttonCraft.setOnMouseReleased(e -> fenetreInv.refreshInv());

        this.getChildren().add(buttonCraft);
        ViewItem itemresult =new ViewItem(recipe.getResultCraft(),pane,environnement,hashMap);
        this.getChildren().add(itemresult);
        itemresult.setId("viewrep" + countItem);
        pane.getChildren().add(this);

    }

    public void recipeIsCraftable() {
        recipe.isCraftable();
        if (recipe.getCraftable()) {
            buttonCraft.setStyle("-fx-background-color: green;");
        } else {
            buttonCraft.setStyle("-fx-background-color: red;");
        }
    }
}
