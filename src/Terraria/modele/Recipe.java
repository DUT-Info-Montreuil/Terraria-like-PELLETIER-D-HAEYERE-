package Terraria.modele;

import Terraria.modele.Acteur.Joueur;
import Terraria.modele.Item.Item;
import Terraria.modele.Item.ItemBlock;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private String ouCrafter;
    private Item resultCraft;
    private ArrayList<Item> listItem;
    private HashMap<Item, Integer> quantiteItem;
    private boolean craftable;
    private Joueur joueur;
    private Environnement environnement;

    public Recipe(Environnement environnement, Item resultCraft, String ouCrafter, ArrayList<Item> listItem, HashMap<Item, Integer> quantiteItem, Joueur joueur) {
        this.resultCraft = resultCraft;
        this.ouCrafter = ouCrafter;
        this.listItem = listItem;
        this.quantiteItem = quantiteItem;
        this.environnement = environnement;
        this.joueur = joueur;
        this.craftable = false;
    }

    public Item getResultCraft() {
        return resultCraft;
    }

    public void isCraftable() {

        int compteItem = 0;
        for (Item itemInRecipe : listItem
        ) {

            for (Item itemInInventaire : joueur.getInventaire()
            ) {

                Class c = itemInInventaire.getClass();
                if (c.equals(itemInRecipe.getClass())) {
                    if (itemInInventaire instanceof ItemBlock) {
                        ItemBlock itemBlockInv = (ItemBlock) itemInInventaire;
                        ItemBlock itemBlockRec = (ItemBlock) itemInRecipe;
                        if (itemBlockInv.getCode() == itemBlockRec.getCode()) {
                            if (itemBlockInv.getQuantite() >= quantiteItem.get(itemInRecipe)) {
                                compteItem++;
                            }
                        }
                    } else {
                        if (itemInInventaire.getQuantite() >= quantiteItem.get(itemInRecipe)) {
                            compteItem++;
                        }
                    }
                }
            }
        }
        if (compteItem == listItem.size()) {
            System.out.println("craft");
        }
        this.craftable = compteItem == listItem.size();
    }

    public boolean getCraftable() {
        return this.craftable;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void craftItem() {
        this.isCraftable();
        if (craftable) {
            joueur.getInventaire().add(resultCraft);
            for (Item itemInRecipe : listItem
            ) {

                for (int i = 0; i < joueur.getInventaire().size(); i++) {


                    Class c = joueur.getInventaire().get(i).getClass();
                    if (c.equals(itemInRecipe.getClass())) {
                            joueur.getInventaire().get(i).removeQuantite(quantiteItem.get(itemInRecipe));

                    }
                }
            }
        }

    }
}
