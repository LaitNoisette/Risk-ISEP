package Jeu;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.paint.Color;

public class Joueur {

	private String nomJoueur;
	private Set<Unite> listeUnite=new HashSet<Unite>();
	
	private int pointUniteJoueur=0;
	
	private Set<Unite> listeUniteSoldat=new HashSet<Unite>();
	private Set<Unite> listeUniteCavalier=new HashSet<Unite>();
	private Set<Unite> listeUniteCanon=new HashSet<Unite>();
	
	private Set<Territoire> listeTerritoire=new HashSet<Territoire>();
	private Set<Region> listeRegion=new HashSet<Region>();
	
	private boolean joueurDetruit = false;
	private boolean premierTour=true;
	//private int pointUnite = 0;
	private Color couleur;

	private int nbTerritoireCaptureTourPrecedent=0;
	
	public Joueur(String nom, int nbrArmeeBase) {
		this.nomJoueur = nom;
		this.pointUniteJoueur=nbrArmeeBase;

		// Creation de l'armee du joueur
		/*
		for (int i = 0; i < nbrArmeeBase; i++) {
			this.listeUniteSoldat.add(Unite.nouveauSoldat());
		}
		*/
	}
	
	public String getNom() {
		return this.nomJoueur;
	}
	
	public int getNbTerritoireCaptureTourPrecedent() {
		return this.nbTerritoireCaptureTourPrecedent;
	}
	
	public int getPointUnite() {
		return this.pointUniteJoueur;
	}
	
	public boolean getPremierTourJoueur() {
		return this.premierTour;
	}
	
	public Set<Region> getListeRegion(){
		return this.listeRegion;
	}
	
	public Set<Territoire> getListeTerritoire(){
		return this.listeTerritoire;
	}
	
	public Set<Unite> getListeUnite() {
		return this.listeUnite;
	}

	public Set<Unite> getListeUniteSoldat() {
		return this.listeUniteSoldat;
	}
	public Set<Unite> getListeUniteCavalier() {
		return this.listeUniteCavalier;
	}
	public Set<Unite> getListeUniteCanon() {
		return this.listeUniteCanon;
	}
	
	public int getNbrUniteSoldatTotal() {
		int nbSoldat=0;
		for (Territoire t : listeTerritoire) {
			nbSoldat+=t.getListeUniteSoldat().size();
		}
		return nbSoldat;
	}
	
	public int getNbrUniteCavalierTotal() {
		int nbSoldat=0;
		for (Territoire t : listeTerritoire) {
			nbSoldat+=t.getListeUniteCavalier().size();
		}
		return nbSoldat;
	}
	
	public int getNbrUniteCanonTotal() {
		int nbSoldat=0;
		for (Territoire t : listeTerritoire) {
			nbSoldat+=t.getListeUniteCanon().size();
		}
		return nbSoldat;
	}
	
	
	public int getNbrTotalUnite() {
		int nbTotal=0;
		nbTotal+=this.getNbrUniteSoldatTotal();
		nbTotal+=this.getNbrUniteCavalierTotal();
		nbTotal+=this.getNbrUniteCanonTotal();
		return nbTotal;
	}
	
	public void incrementerNbrTerritoireConquisDernierTour() {
		this.nbTerritoireCaptureTourPrecedent++;
	}
	
	public void reinitialiserNbrTerritoireConquisDernierTour() {
		this.nbTerritoireCaptureTourPrecedent=0;
	}
	
	public void ajouterPointUniteJoueur(int pUniteAjouter) {
		if(pUniteAjouter>0) {
			this.pointUniteJoueur+=pUniteAjouter;
		}
		
	}
	
	public void addUnite(Unite u) {
		this.listeUnite.add(u);
	}
	
	//Test le respect des conditions du joueurs pour passer son premier tour
	public boolean conditionFinPremierTour() {
		if(this.premierTour) {
			//Le joueur ne doit plus avoir de points d'unite a depenser
			if(this.pointUniteJoueur>0) {
				return false;
			}
			else {
				this.premierTour=false;
				return true;
			}
		}
		else {
			//Premier tour du joueur passé
			return true;
		}
	}

	public boolean addUniteSoldat() {
		if(this.pointUniteJoueur>=1) {
			this.pointUniteJoueur-=1;
			return true;
		}
		//Verifie si le joueur peut passer son premier tour
		this.conditionFinPremierTour();
		return false;
		//this.listeUnite.add(Unite.nouveauSoldat());
	}
	
	public boolean addUniteCanon() {
		//Pas unite autre que soldat au premier tour 
		if(!this.premierTour) {
		if(this.pointUniteJoueur>=7) {
			this.pointUniteJoueur-=7;
			return true;
		}
		}
		return false;
		//this.listeUnite.add(Unite.nouveauCanon());
	}
	
	public boolean addUniteCavalier() {
		//Pas unite autre que soldat au premier tour 
		if(!this.premierTour) {
		if(this.pointUniteJoueur>=3) {
			this.pointUniteJoueur-=3;
			return true;
		}
		}
		return false;
		//this.listeUnite.add(Unite.nouveauCavalier());
	}
	
	public void gererRenfort() {
		//Nombre de territoire
		
		int renfort =Math.floorDiv(this.getListeTerritoire().size(), 3);
		for (Region r : this.getListeRegion()) {
			renfort+=Math.floorDiv(r.getListeTerritoire().size(), 2);
		}
		Double rnd=Math.random();
		rnd=rnd*100;
		rnd=rnd%2;
		
		if(rnd==1) {
			renfort+=this.nbTerritoireCaptureTourPrecedent;
		}
		
		if(renfort<2) {
			//Min point unite recue
			this.ajouterPointUniteJoueur(2);
		}
		else {
			this.ajouterPointUniteJoueur(renfort);
		}
		
	}
	
}
