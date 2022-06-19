package Terraria.modele;

public class UnCoeur {
    private int x;
    private int y;
    private int limiteAllume ;
    private boolean allume ;
    private int id ;
    private static int count = 0 ;



    public UnCoeur(int x, int y , int limiteAllume) {
        this.x = x;
        this.y = y;
        this.allume = false ;
        this.limiteAllume = limiteAllume ;
        this.id = this.count++;
    }


    public void update(int hp ){
        if (hp >= limiteAllume){
            allume = true ;
        }
        else{
            allume = false ;
        }
    }


    public boolean isAllume() {
        return allume;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
