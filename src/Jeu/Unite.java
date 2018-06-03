package Jeu;

public class Unite {
	private String nomUnite;
	private int cout;
	private int puissanceMin;
	private int puissanceMax;
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
	}
	
	public String getNom() {
		return this.getNom();
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

	
	public static Unite nouveauSoldat() {
		return new Unite("Soldat", 1, 1, 6, 2, 1, 2);
	}

	public static Unite nouveauCavalier() {
		return new Unite("Cavalier", 3, 2, 7, 1, 3, 3);
	}

	public static Unite nouveauCanon() {
		return new Unite("Canon", 7, 4, 9, 3, 2, 1);
	}

}
