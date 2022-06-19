package Terraria.modele.Acteur;

import Terraria.modele.*;
import Terraria.modele.Item.Item;
import Terraria.modele.Item.OnGroundItem;
import Terraria.modele.Item.RottenFlesh;

import java.util.ArrayList;

public class Zombie extends Ennemi {


    public Zombie(int pv, int vitesse, int posX, int posY, Environnement environnement, HitBox b) {
        super(pv, vitesse, posX, posY, environnement, b);
        this.reach = 2;

    }


    public void seDeplace(Joueur j, ArrayList<Block> allBlock) {
        if (j.getBox().getY().intValue() <= this.getBox().getY().intValue() + 16 * detectionRange && j.getBox().getY().intValue() >= this.getBox().getY().intValue() - 16 * detectionRange) {
            if (this.getBox().getX().intValue() + this.getBox().getWidth() + 16 * detectionRange >= j.getBox().getX().intValue() && j.getBox().getX().intValue() >= this.getBox().getX().intValue()) {



                if ((this.collideGaucheDroite(allBlock) != 1)) {
                    this.setPosX(this.getPosX() + 1 * this.getVitesse());
                    if (this.playerCollide() == 1) {
                        this.environnement.getJoueur1().takeDomage(1);

                    }
                } else {
                    this.saute();
                    this.setPosX(this.getPosX() + 1 * this.getVitesse());
                }


            } else if (this.getBox().getX().intValue() - 16 * detectionRange <= j.getBox().getX().intValue() + j.getBox().getWidth() && j.getBox().getX().intValue() <= this.getBox().getX().intValue()) {





                    if ((this.collideGaucheDroite(allBlock) != 1)) {
                        this.setPosX(this.getPosX() + -1 * this.getVitesse());
                        if (this.playerCollide() == -1) {
                            this.environnement.getJoueur1().takeDomage(1);

                        }
                    } else {

                        this.saute();
                        this.setPosX(this.getPosX() + (-1 * this.getVitesse()));
                    }



            }else {

                move++;
                //die();
                if (move % 5 == 0) {

                    int result = (int) (Math.random() * 3) - 1;

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
                    } else {

            move++;

            if (move % 5 == 0) {


                int result = (int) (Math.random() * 3) - 1;

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
    }
    @Override
    public void die () {

        this.isAlive = false;
        this.environnement.getListActeur().remove(this);
        this.environnement.getListEnnemi().remove(this);
        Item drop = new RottenFlesh(1, this.environnement);
        this.environnement.getOnGroundItem().add(new OnGroundItem(drop,new HitBox(this.getPosX() ,this.getPosY() , 16 , 16 , true ) ,  this.environnement, this.getPosX(), this.getPosY()));
    }


    public int playerCollide(){
        Joueur j = this.environnement.getJoueur1() ;

            int a = this.getBox().getY().intValue()-1;
            int b = a + this.getBox().getHeight()-1 ;
            int aPrime = j.getBox().getY().intValue()-1 ;
            int bPrime = j.getBox().getX().intValue() +j.getBox().getHeight() -1;

            if (( a >= aPrime && a <= bPrime) || (b >= aPrime && b <= bPrime)) {
                if (this.getBox().getX().intValue() <= j.getBox().getX().intValue() + j.getBox().getWidth() && this.getBox().getX().intValue() + this.getBox().getWidth() >= j.getBox().getX().intValue() + j.getBox().getWidth() ) {
                    //test collision gauche

                    return -1 ;
                }
                if (this.getBox().getX().intValue() <= j.getBox().getX().intValue()  && this.getBox().getX().intValue() + this.getBox().getWidth() >= j.getBox().getX().intValue()   ) {

                    return 1 ;
                }
            }



        return 0 ;
        }


}
