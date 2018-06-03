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
	//private int pointUnite = 0;
	private Color couleur;

	private int nbTerritoireCaptureTourPrecedent=0;
	
	public Joueur(String nom, int nbrArmeeBase) {
		this.nomJoueur = nom;

		// Creation de l'armee du joueur
		for (int i = 0; i < nbrArmeeBase; i++) {
			this.listeUniteSoldat.add(Unite.nouveauSoldat());
		}
	}
	
	public String getNom() {
		return this.nomJoueur;
	}
	
	public int getNbTerritoireCaptureTourPrecedent() {
		return this.nbTerritoireCaptureTourPrecedent;
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
	
	public void ajouterPointUniteJoueur(int pUniteAjouter) {
		if(pUniteAjouter>0) {
			this.pointUniteJoueur+=pUniteAjouter;
		}
		
	}
	
	public void addUnite(Unite u) {
		this.listeUnite.add(u);
	}

	public boolean addUniteSoldat() {
		if(this.pointUniteJoueur>=1) {
			this.pointUniteJoueur-=1;
			return true;
		}
		return false;
		//this.listeUnite.add(Unite.nouveauSoldat());
	}
	
	public boolean addUniteCanon() {
		if(this.pointUniteJoueur>=7) {
			this.pointUniteJoueur-=7;
			return true;
		}
		return false;
		//this.listeUnite.add(Unite.nouveauCanon());
	}
	
	public boolean addUniteCavalier() {
		if(this.pointUniteJoueur>=3) {
			this.pointUniteJoueur-=3;
			return true;
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
