package Terraria.modele.Acteur;

import Terraria.modele.Block;
import Terraria.modele.Environnement;
import Terraria.modele.HitBox;

import java.util.ArrayList;

public abstract class Ennemi extends Acteur {
    protected int move = 0  ;
    protected int detectionRange = 10 ;

    public Ennemi (int pv, int vitesse, int posX, int posY, Environnement environnement, HitBox b ) {
        super(pv , vitesse , posX , posY , environnement ,  b );
    }


    public abstract void die() ;




    public void seDeplace(Joueur j, ArrayList<Block> allBlock) {

    }
}
