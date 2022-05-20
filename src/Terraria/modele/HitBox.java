package Terraria.modele;

public class HitBox {
    private boolean isSolide ;
    private int height ;
    private int width ;


    public  HitBox ( int height , int width , boolean solide){
        this.isSolide = solide ;
        this.height = height ;
        this.width = width ;

    }
}
