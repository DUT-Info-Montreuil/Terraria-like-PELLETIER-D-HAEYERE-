package Terraria.modele;

public class Zombie extends Ennemi{
    public Zombie(int pv, int vitesse, int posX, int posY, Environnement environnement, String id, HitBox b) {
        super(pv, vitesse, posX, posY, environnement, id, b);
    }




    public void seDeplace(Joueur j ){
        if (this.getBox().getX().intValue()+this.getBox().getWidth()+64 >= j.getBox().getX().intValue() && j.getBox().getX().intValue() >= this.getBox().getX().intValue()){

            System.out.println("in range right");
            this.setPosX(this.getPosX()+1*this.getVitesse());


        }else if (this.getBox().getX().intValue()-64 <= j.getBox().getX().intValue()+j.getBox().getWidth() && j.getBox().getX().intValue() <= this.getBox().getX().intValue()){
            System.out.println("in range left");
            this.setPosX(this.getPosX()+(-1*this.getVitesse()));

        }else {
            System.out.println("not in range");
            move++ ;
            if (move%5 == 0){
                int result = (int) (Math.random()*3)-1;
                System.out.println("moving "+ result);

                this.setPosX(this.getPosX()+result*this.getVitesse());
            }



        }




    }



}
