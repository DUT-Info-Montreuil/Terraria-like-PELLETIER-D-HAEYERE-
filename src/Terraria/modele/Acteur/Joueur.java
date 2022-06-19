package Terraria.modele.Acteur;

import Terraria.modele.*;
import Terraria.modele.Item.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;


public class Joueur extends Acteur {
    private Item itemEquipe;


    public ArrayList<UnCoeur> getAllCoeurs() {
        return allCoeurs;
    }

    private ArrayList<Recipe> listCraft = new ArrayList<Recipe>();
    private ArrayList<UnCoeur> allCoeurs = new ArrayList<>();

    public Joueur(int pv, int vitesse, int posX, int posY, Environnement environnement, HitBox h, Item itemEquipe) {
        super(pv, vitesse, posX, posY, environnement, h);


        h.getY().bind(this.getYProprety());
        h.getX().bind(this.getXProprety());
        this.itemEquipe = itemEquipe;


        initInv();
        initCraft();


        for (int i = 0; i < 10; i++) {
            allCoeurs.add(new UnCoeur(94+(8*i) , 35 , i+1));

        }
    }

    public void setItemEquipe(Item itemEquipe) {
        this.itemEquipe = itemEquipe;
        System.out.println("changement item");
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


                } else if (this.getBox().getX().intValue() <= block.getBoxX().intValue() && this.getBox().getX().intValue() + this.getBox().getWidth() >= block.getBoxX().intValue() /*&& this.getBox().getX().intValue() + this.getBox().getWidth() <= block.getBoxX().intValue() + block.getOffSet()*/) {
                    // test collision droite
                    if (block.getBox().isSolide()) {
                        this.setPosX(block.getBox().getX().intValue() - this.getBox().getWidth());

                        return 1;
                    }


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


                }

            }


        }
        this.setFalling(true);
        return 0;
    }


    public boolean checkDistanceInReach(int posX, int posY) {
        return (valABS(this.getPosX() - posX) < this.getReach() * 16) && (valABS(this.getPosY() - posY) < this.getReach() * 16);
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


    public void takeDomage(int damage) {
        this.pv -= damage;
        if (this.pv <= 0) {
            die();


        }
    }

    @Override
    public void die() {

    }

    public void initCraft() {
        RottenFlesh r1 = new RottenFlesh(1, this.getEnvironnement());
        ArrayList<Item> listCraft = new ArrayList<Item>();
        HashMap<Item, Integer> itemQuantite = new HashMap<Item, Integer>();
        ItemBlock craft = new ItemBlock(0, this.getEnvironnement(), 2);
        listCraft.add(craft);
        itemQuantite.put(craft, 3);
        craft = new ItemBlock(0, this.getEnvironnement(), 4);
        listCraft.add(craft);

        itemQuantite.put(craft, 2);
        Pioche craftP = new Pioche(250, 10, environnement);
        listCraft.add(craftP);
        itemQuantite.put(craftP, 1);
        Recipe rec = new Recipe(this.getEnvironnement(), r1, "Joueur", listCraft, itemQuantite, this);
        this.listCraft.add(rec);





    }
    public ArrayList<Recipe> getListCraft() {
        return listCraft;
    }
    public void updateCoeur(){
        for (UnCoeur c:allCoeurs) {
            c.update(this.getPv());
        }
    }
    public OnGroundItem itemCollide(ObservableList<OnGroundItem> allItem){
        for (int is= 0; is < allItem.size(); is++) {
            int a = this.getBox().getY().intValue()-1;
            int b = a + this.getBox().getHeight()-1 ;
            int aPrime = allItem.get(is).getBox().getY().intValue()-1 ;
            int bPrime = allItem.get(is).getBox().getX().intValue() + allItem.get(is).getBox().getHeight() -1;

            if (( a >= aPrime && a <= bPrime) || (b >= aPrime && b <= bPrime)) {
                if (this.getBox().getX().intValue() <= allItem.get(is).getBox().getX().intValue() + allItem.get(is).getBox().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() >= allItem.get(is).getBox().getX().intValue() + allItem.get(is).getBox().getWidth() ) {
                    //test collision gauche
                    System.out.println("in item left");
                    return allItem.get(is);
                }
                if (this.getBox().getX().intValue() <= allItem.get(is).getBox().getX().intValue()  && this.getBox().getX().intValue() + this.getBox().getWidth() >= allItem.get(is).getBox().getX().intValue()   ) {
                    System.out.println("in item right");
                    return allItem.get(is);
                }
            }





        }
        return null ;
    }
}

