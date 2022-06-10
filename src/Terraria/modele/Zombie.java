package Terraria.modele;

import java.util.ArrayList;

public class Zombie extends Ennemi {


    public Zombie(int pv, int vitesse, int posX, int posY, Environnement environnement, String id, HitBox b) {
        super(pv, vitesse, posX, posY, environnement, id, b);
        this.reach = 2;

    }


    public void seDeplace(Joueur j, ArrayList<Block> allBlock) {
        if (j.getBox().getY().intValue() <= this.getBox().getY().intValue() + 16 * detectionRange && j.getBox().getY().intValue() >= this.getBox().getY().intValue() - 16 * detectionRange) {
            if (this.getBox().getX().intValue() + this.getBox().getWidth() + 16 * detectionRange >= j.getBox().getX().intValue() && j.getBox().getX().intValue() >= this.getBox().getX().intValue()) {


                //System.out.println("in range right");
                //System.out.println(this.collideGaucheDroite(allBlock));
                if ((this.collideGaucheDroite(allBlock) != 1)) {
                    this.setPosX(this.getPosX() + 1 * this.getVitesse());

                } else {
                    this.saute();
                    this.setPosX(this.getPosX() + 1 * this.getVitesse());
                }


            } else if (this.getBox().getX().intValue() - 16 * detectionRange <= j.getBox().getX().intValue() + j.getBox().getWidth() && j.getBox().getX().intValue() <= this.getBox().getX().intValue()) {
                //System.out.println("in range left");


                if ((this.collideGaucheDroite(allBlock) != -1)) {
                    this.setPosX(this.getPosX() + (-1 * this.getVitesse()));
                } else {

                    this.saute();
                    this.setPosX(this.getPosX() + (-1 * this.getVitesse()));
                }


            } else {
                //System.out.println("not in range");
                move++;
                //die();
                if (move % 5 == 0) {
                    //System.out.println("idle");

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


                } else {
                    //System.out.println("not in range");
                    move++;
                    die();
                    if (move % 5 == 0) {
                        System.out.println("idle");

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
        }


    }

    public void die () {
        this.isAlive = false;
        this.environnement.getListActeur().remove(this);
        this.environnement.getListEnnemi().remove(this);
        Item drop = new RottenFlesh(1, this.environnement);
        this.environnement.getOnGroundItem().add(new OnGroundItem(drop,new HitBox(this.getPosX() ,this.getPosY() , 16 , 16 , true ) ,  this.environnement, this.getPosX(), this.getPosY()));
    }
}
