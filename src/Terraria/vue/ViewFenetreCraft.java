package Terraria.vue;

import Terraria.modele.Environnement;
import Terraria.modele.Recipe;
import Terraria.modele.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ViewFenetreCraft extends ImageView {

    private Pane pane;
    private Environnement e1;
    private HashMap<Tile, Image> mapLienIdImage;
    private boolean afficheCraft;

    public ViewFenetreCraft(Pane pane, Environnement e1, HashMap<Tile, Image> mapLienIdImage) {
        this.pane = pane;
        this.e1 = e1;
        this.mapLienIdImage = mapLienIdImage;
        this.afficheCraft = false;
        Image imgCraft = new Image(String.valueOf(getClass().getResource("/FenetreCraft.png")));

        this.setImage(imgCraft);
        pane.getChildren().add(this);
        this.setVisible(false);
        this.setX(5);
        this.setY(100);
        this.setId("FenetreCraft");
    }

    public void afficheCraft() {
        int countRecipe = 0;
        if (!afficheCraft) {
            afficheCraft=true;
            for (Recipe recipe : e1.getJoueur1().getListCraft()
            ) {
                ViewRecipe viewRecipe = new ViewRecipe(recipe, e1.getJoueur1(), pane, e1, mapLienIdImage);
                viewRecipe.setTranslateX(this.getX() + 15);
                viewRecipe.setTranslateY(this.getY() +(countRecipe+1)*10);
                viewRecipe.setId("Hbox" + countRecipe);
            }
        }
    }
    public void closeCraft(){
        if (afficheCraft){
            afficheCraft=false;
            for (int i = 0; i < e1.getJoueur1().getListCraft().size(); i++) {
                pane.getChildren().remove( pane.lookup("#Hbox" + i));
            }
        }

    }

    public void checkCraftable() {
        if (afficheCraft) {
            for (int i = 0; i < e1.getJoueur1().getListCraft().size(); i++) {
                ViewRecipe viewrep = (ViewRecipe) pane.lookup("#Hbox" + i);
                viewrep.recipeIsCraftable();
            }
        }

    }
}
