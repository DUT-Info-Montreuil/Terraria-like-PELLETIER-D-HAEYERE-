package Terraria.modele;

public class Zombie extends Ennemi{
    public Zombie(int pv, int vitesse, int posX, int posY, Environnement environnement, String id, HitBox b) {
        super(pv, vitesse, posX, posY, environnement, id, b);
    }




    public void seDeplace(Joueur j ){
        if (this.getBox().getX().intValue()+this.getBox().getWidth()+32 <= j.getBox().getX().intValue() && j.getBox().getX().intValue() + j.getBox().getWidth() >= this.getBox().getX().intValue()-32){

            System.out.println("in range");


        }




    }



}
