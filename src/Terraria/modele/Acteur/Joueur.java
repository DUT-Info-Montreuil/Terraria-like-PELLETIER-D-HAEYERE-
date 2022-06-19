package Terraria.modele.Acteur;

import Terraria.modele.*;
import Terraria.modele.Item.Item;
import Terraria.modele.Item.ItemBlock;
import Terraria.modele.Item.Pioche;
import Terraria.modele.Item.RottenFlesh;

import java.util.ArrayList;
import java.util.HashMap;


public class Joueur extends Acteur {
    private Item itemEquipe;
    private boolean toucheCrafting;
    private ArrayList<Recipe>listCraft = new ArrayList<Recipe>();
    public Joueur(int pv, int vitesse, int posX, int posY, Environnement environnement, String id, HitBox h, Item itemEquipe) {
        super(pv, vitesse, posX, posY, environnement, id, h);

        h.getY().bind(this.getYProprety());
        h.getX().bind(this.getXProprety());
        this.itemEquipe = itemEquipe;
        toucheCrafting = false;

        initInv();
        initCraft();
    }

    public void setItemEquipe(Item itemEquipe) {
        this.itemEquipe = itemEquipe;
        System.out.println("changement item");
    }

    public boolean isToucheCrafting() {
        return toucheCrafting;
    }

    public int collideGaucheDroite(ArrayList<Block> blocks) {
        //code dupli avec joueur suprimmer ?

        for (Block block : blocks) {
                int a = this.getBox().getY().intValue() - 1;
                int b = a + this.getBox().getHeight() - 1;
                int aPrime = block.getBoxY().intValue() - 1;
                int bPrime = block.getBoxY().intValue() + block.getTile().getBox().getHeight() - 1;

                if ((a >= aPrime && a <= bPrime) || (b >= aPrime && b <= bPrime)) {

                    if (this.getBox().getX().intValue() <= block.getBoxX().intValue() + block.getTile().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() + block.getTile().getWidth()) {
                        //test collision gauche
                        if (block.getBox().isSolide()) {
                            this.setPosX(block.getBox().getX().intValue() + block.getBox().getWidth());

                            return -1;
                        }

                        collisionCraft(block);

                    } else if (this.getBox().getX().intValue() <= block.getBoxX().intValue() && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() /*&& this.getBox().getX().intValue() + this.getBox().getWidth() <= block.getBoxX().intValue() + block.getOffSet()*/) {
                        // test collision droite
                        if (block.getBox().isSolide()) {
                            this.setPosX(block.getBox().getX().intValue() - this.getBox().getWidth());

                            return 1;
                        }

                        collisionCraft(block);
                    }
                }




        }

        return 0;
    }


    public int collideHautBas(ArrayList<Block> blocks) {
        for (Block block : blocks) {

            if (this.getBox().getY().intValue() + this.getBox().getHeight() >= block.getBoxY().intValue() && this.getBox().getY().intValue() + this.getBox().getHeight() <= block.getBox().getY().intValue() + block.getOffSet()) {
                int b = this.getBox().getX().intValue();
                int d = this.getBox().getX().intValue() + this.getBox().getWidth();

                int aPrime = block.getBoxX().intValue();
                int cPrime = block.getBoxX().intValue() + block.getBox().getWidth();
                // System.out.println("yes");
                //
                if ((b <= aPrime && d >= aPrime) || (b <= cPrime && d >= cPrime)) {
                    //System.out.println("test");
                                /*for (Block bl:blocks) {
                                    if (bl.getBox().getX().intValue() == block.getBox().getX().intValue() && bl.getBox().isSolide()){
                                        if (bl.getBox().getY().intValue() == block.getBox().getY().intValue() + bl.getBox().getHeight()){
                                            this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());
                                            return 0 ;
                                        }
                                    }
                                }*/


                    //System.out.println("gauche");
                    if (block.getBox().isSolide()) {
                        this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());
                        this.setFalling(false);

                        return 1;
                    }

                    collisionCraft(block);
                }

            }


        }
        this.setFalling(true);
        return 0;
    }

    public boolean checkDistanceInReach(int posX, int posY) {
        if ((valABS(this.getPosX() - posX) < this.getReach() * 16) && (valABS(this.getPosY() - posY) < this.getReach() * 16)) {
            return true;
        }
        return false;
    }

    public int valABS(int valeur) {
        if (valeur < 0)
            return -valeur;
        return valeur;
    }

    public Item getItemEquipe() {
        return this.itemEquipe;
    }

    public void initInv() {
        for (Tile t : environnement.getAllTiles()
        ) {
            if (t.getId() != 1) {
                this.addItem(new ItemBlock(0, environnement, t.getId()));
            }

        }
    }

    public void collisionCraft(Block b) {

        if (b.getTile().getId() == 26) {

            this.toucheCrafting = true;
        } else {
            this.toucheCrafting = false;

        }
    }
    public void initCraft(){
        RottenFlesh r1 = new RottenFlesh(1,this.getEnvironnement());
        ArrayList<Item>listCraft= new ArrayList<Item>();
        HashMap<Item,Integer>itemQuantite = new HashMap<Item,Integer>();
        ItemBlock craft =new ItemBlock(0,this.getEnvironnement(),2);
        listCraft.add(craft);
        itemQuantite.put(craft,3);
        craft = new ItemBlock(0,this.getEnvironnement(),4);
        listCraft.add(craft);

        itemQuantite.put(craft,2);
        Pioche craftP = new Pioche(250, 10, environnement);
        listCraft.add(craftP);
        itemQuantite.put(craftP,1);
        Recipe rec = new Recipe(this.getEnvironnement(),r1,"Joueur",listCraft,itemQuantite,this);
        this.listCraft.add(rec);



    }

    public ArrayList<Recipe> getListCraft() {
        return listCraft;
    }
}
