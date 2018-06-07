package Jeu;

public class Unite {
	private String nomUnite;
	private int cout;
	private int puissanceMin;
	private int puissanceMax;
	private int puissanceBataille=0;
	private int prioriteATT;
	private int prioriteDEF;
	//Reference du nombre de deplacement
	private int mvtTour;
	//Deplacement restant a l'unite pendant ce tour
	private int mvtTourReel;
	private boolean uniteDetruit = false;

	public Unite(String nom, int cout, int puisMin, int puisMax, int prioATT, int prioDEF, int mvtTour) {
		this.nomUnite = nom;
		this.cout = cout;
		this.puissanceMin = puisMin;
		this.puissanceMax = puisMax;
		this.prioriteATT = prioATT;
		this.prioriteDEF = prioDEF;
		this.mvtTour = mvtTour;
		this.mvtTourReel=this.mvtTour;
	}
	
	public String getNom() {
		return this.nomUnite;
	}
	
	public int getPuissanceMin() {
		return this.puissanceMin;
	}
	
	public int getPuissanceMax() {
		return this.puissanceMax;
	}
	
	public int getCout() {
		return this.cout;
	}
	
	public int getPuissanceBataille() {
		return this.puissanceBataille;
	}
	
	public int getPrioriteDEF() {
		return this.prioriteDEF;
	}
	
	public int getPrioriteATT() {
		return this.prioriteATT;
	}
	public void setPuissanceBataille(int puissanceBataille) {
		this.puissanceBataille=puissanceBataille;
	}
	
	public void reinitialiserPuissanceBataille() {
		this.puissanceBataille=this.puissanceMin;
	}
	
	public void reinitialiserDeplacement() {
		this.mvtTourReel=this.mvtTour;
	}
	
	public void diminuerDeplacementUnite() {
		this.mvtTourReel--;
	}
	
	public boolean deplacementUnitePossible() {
		if(this.mvtTourReel>=1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void ajouterUniteConqueranteTerritoire(Territoire tConquis) {
		this.diminuerDeplacementUnite();
		tConquis.getListeTypeUnite(this.getNom()).add(this);
	}

	
	public static Unite nouveauSoldat() {
		//On changera le nom plus tard faut que je reverifie dans toute les classes ou ce nom est utilise en dur Machine Gun
		return new Unite("Soldat", 1, 1, 6, 2, 1, 2);
	}

	public static Unite nouveauCavalier() {
		//On changera le nom plus tard faut que je reverifie dans toute les classes ou ce nom est utilise en dur Modern Armor
		return new Unite("Cavalier", 3, 2, 7, 1, 3, 3);
	}

	public static Unite nouveauCanon() {
		//On changera le nom plus tard faut que je reverifie dans toute les classes ou ce nom est utilise en dur Modern AT
		return new Unite("Canon", 7, 4, 9, 3, 2, 1);
	}
	
	

}
