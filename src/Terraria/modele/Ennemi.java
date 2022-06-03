package Terraria.modele;

public class Ennemi extends Acteur {
    int move = 0  ;

    public Ennemi (int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox b ) {
        super(pv , vitesse , posX , posY , environnement ,  id , b );
    }



    public void seDeplace(Joueur j ) {

    }
}
