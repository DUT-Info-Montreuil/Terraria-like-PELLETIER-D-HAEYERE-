package Terraria.modele.Item;

import Terraria.modele.Block;
import Terraria.modele.Environnement;
import Terraria.modele.HitBox;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class OnGroundItem {
    private SimpleIntegerProperty x;
    private SimpleIntegerProperty y;
    private String OngroundId ;
    private Item item;
    private HitBox box ;
    private boolean isFalling;
    private int vitesse = 5  ;
    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public OnGroundItem(Item item, HitBox box , Environnement environnement , int x , int y) {


        this.item = item;
        this.OngroundId = item.getId();
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.box = box;
        box.getX().bind(this.getXProprety());
        box.getY().bind(this.getYProprety());
    }

    public SimpleIntegerProperty getXProprety() {
        return x   ;
    }

    public Item getItem() {
        return item;
    }

    public SimpleIntegerProperty getYProprety() {
        return y   ;
    }

    public String getId() {
        return OngroundId ;
    }



    public int collideHautBas(ArrayList<Block> blocks){
        for (Block block : blocks) {
            if (block.getBox().isSolide()) {
                if (this.getBox().getY().intValue() + this.getBox().getHeight() >= block.getBoxY().intValue() && this.getBox().getY().intValue() + this.getBox().getHeight() <= block.getBox().getY().intValue() + block.getOffSet()) {

                    int b = this.getBox().getX().intValue();
                    int d = this.getBox().getX().intValue() + this.getBox().getWidth();

                    int aPrime = block.getBoxX().intValue();
                    int cPrime = block.getBoxX().intValue() + block.getBox().getWidth();


                    if ((b <= aPrime && d >= aPrime) || (b <= cPrime && d >= cPrime) ) {

                                /*for (Block bl:blocks) {
                                    if (bl.getBox().getX().intValue() == block.getBox().getX().intValue() && bl.getBox().isSolide()){
                                        if (bl.getBox().getY().intValue() == block.getBox().getY().intValue() + bl.getBox().getHeight()){
                                            this.setPosY(block.getBoxY().intValue() - this.getBox().getHeight());
                                            return 0 ;
                                        }
                                    }
                                }*/
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

    private void setPosY(int i) {
        this.y.set(i);
    }

    public HitBox getBox() {
        return box;
    }

    public void gravite(){
        if (isFalling){
            this.y.setValue(y.getValue()+(vitesse*1.5));
        }
    }
}
