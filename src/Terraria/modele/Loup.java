package Terraria.modele;

public class Loup extends Acteur{
	private static double pourcentageRepro=3;
	private static int maxMangerTour = 1 ;
	private boolean aMange= false;



	public Loup(int x, int y, Environnement env) {
		super(x, y,10, env,80 ,env.getNbTours() );

	}





	public Loup(Environnement env) {
		super(10, env,80 ,env.getNbTours());

	}

	public static void setPourcentageRepro(double pourcentageRepro) {
		Loup.pourcentageRepro = pourcentageRepro;
	}

	public static void setReproduction(double n){
		pourcentageRepro=n;
	}


	public static void setMaxMangerTour(int maxMangerTour) {
		Loup.maxMangerTour = maxMangerTour;
	}

	public static double getPourcentageRepro() {
		return pourcentageRepro;
	}

	public static int getMaxMangerTour() {
		return maxMangerTour;
	}

	@Override
	public void agit() {
		int ax=this.getX();
		int ay= this.getY();
		this.seDeplace();
		System.out.println("L " + this.getId()+ " se deplace de " + ax + ","+ ay + "vers " + this.getX()+ ","+ this.getY());
		for (int i = 0; i < maxMangerTour; i++) {
			Acteur m = this.essaieCapturerMouton();
			if(m!=null){
				System.out.println(this.getId() +" a manger son "+i+1+" mouton"+m.getId());
				System.out.println("L "+ this.getId()+ "capture mouton");
				m.meurt();
				nbrCapture++;
				aMange =true;
			}
		}

		if(!aMange){
			// il a faim donc s'affaiblit
			this.decrementerPv(2);
			//System.out.println("L s'affaiblit");
		}
		aMange =false ;
		if(reussitProba(pourcentageRepro)){
			Loup bebe= new Loup(this.getX()+6, this.getY()+6,this.env);
			System.out.println("naissance de " + bebe);
			this.env.ajouter(bebe);
		}
		this.decrementerPv(1);
	}



	private Acteur essaieCapturerMouton() {
		// on regarde s'il y a un mouton a moins de 5 pixels de lui.
		for(Acteur m : this.env.getActeurs()){
			if(m instanceof Mouton){
				if(		(this.getY()-5<= m.getY() && m.getY()<=this.getY()+5) &&
						(this.getX()-5<= m.getX() && m.getX()<=this.getX()+5)  
						){
					return m;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Loup" + super.toString() ;
	}

}
