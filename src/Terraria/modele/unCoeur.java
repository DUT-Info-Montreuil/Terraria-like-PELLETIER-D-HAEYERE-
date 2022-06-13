package Terraria.modele;

public class unCoeur {
    private int x;
    private int y;
    private int limiteAllume ;
    private boolean allume ;



    public unCoeur(int x, int y , int limiteAllume) {
        this.x = x;
        this.y = y;
        this.allume = false ;
        this.limiteAllume = limiteAllume ;
    }


    public void update(int hp ){
        if (hp >= limiteAllume){
            allume = true ;
        }
        else{
            allume = false ;
        }
    }


}
