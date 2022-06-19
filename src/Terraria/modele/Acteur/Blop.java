package Terraria.modele.Acteur;

import Terraria.modele.Block;
import Terraria.modele.Environnement;
import Terraria.modele.HitBox;
import Terraria.modele.Item.Item;
import Terraria.modele.Item.OnGroundItem;
import Terraria.modele.Item.RottenFlesh;

import java.util.ArrayList;

public class Blop extends Ennemi{

    public Blop(int pv, int vitesse, int posX, int posY, Environnement environnement, HitBox b) {
        super(pv, vitesse, posX, posY, environnement, b);
    }


    public void seDeplace(Joueur j, ArrayList<Block> allBlock){

            //System.out.println("not in range");
            move++;

            if (move % 5 == 0) {
                System.out.println("idle");

                int result = (int) (Math.random() * 3) - 1;
                //System.out.println("moving " + result);
                //System.out.println("collision a gauche");
                //System.out.println(this.collideGaucheDroite(allBlock) != -1);
                //System.out.println("collision a droite");
                //System.out.println(this.collideGaucheDroite(allBlock) != 1);

                //System.out.println(this.collideGaucheDroite(allBlock));
                if ((result == -1) && (this.collideGaucheDroite(allBlock) != -1)) {
                    this.setPosX(this.getPosX() + result * this.getVitesse());
                } else if ((result == 1) && (this.collideGaucheDroite(allBlock) != 1)) {
                    this.setPosX(this.getPosX() + result * this.getVitesse());
                } else if (this.collideGaucheDroite(allBlock) != 0) {
                    this.saute();
                    this.setPosX(this.getPosX() + result * this.getVitesse());
                }
            }
        }






    @Override
    public void die() {
        this.isAlive = false;
        this.environnement.getListActeur().remove(this);
        this.environnement.getListEnnemi().remove(this);
        Item drop = new RottenFlesh(1, this.environnement);
        this.environnement.getOnGroundItem().add(new OnGroundItem(drop,new HitBox(this.getPosX() ,this.getPosY() , 16 , 16 , true ) ,  this.environnement, this.getPosX(), this.getPosY()));
    }
}
