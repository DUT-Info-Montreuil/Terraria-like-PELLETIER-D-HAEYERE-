package Terraria.modele;

import java.util.ArrayList;

public class Ennemi extends Acteur {
    protected int move = 0  ;
    protected int detectionRange = 10 ;

    public Ennemi (int pv, int vitesse, int posX, int posY, Environnement environnement, String id , HitBox b ) {
        super(pv , vitesse , posX , posY , environnement ,  id , b );
    }



    public void seDeplace(Joueur j, ArrayList<Block> allBlock) {

    }
}
