package Jeu;
import java.util.HashSet;
import java.util.Set;

public class Territoire {
	private Joueur proprietaire = null;
	private String nom;
	//private Set<Territoire> territoireAdjacent=new HashSet<Territoire>();
	private Carte carte=null;
	
	private Set<Unite> listeUniteSoldat=new HashSet<Unite>();
	private Set<Unite> listeUniteCavalier=new HashSet<Unite>();
	private Set<Unite> listeUniteCanon=new HashSet<Unite>();
	
	public Territoire(String n) {
		//this.carte=c;
		this.nom=n;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
	
	public Set<Unite> getListeUniteSoldat(){
		return this.listeUniteSoldat;
	}
	
	public Set<Unite> getListeUniteCavalier(){
		return this.listeUniteCavalier;
	}
	
	public Set<Unite> getListeUniteCanon(){
		return this.listeUniteCanon;
	}
	
	public void setProprietaire(Joueur j) {
		this.proprietaire=j;
	}
	
	public void ajouterAdjacence(Territoire tAdjacent) {
		this.carte.getAdjacenceTerritoire().ajouterAdjacence2Territoire(this, tAdjacent);
	}
	
	public boolean getAdjacenceTerritoire(Territoire t) {
		return this.carte.getAdjacenceTerritoire().getAdjacence2Territoire(this, t);
	}
	
	public boolean territoireEquivalent(Territoire t) {
		return this.nom.equals(t.getNom());
	}
	
	public int getNbrTotalUniteTerritoire() {
		int nbUnitTerritoire=this.listeUniteSoldat.size();
		nbUnitTerritoire+=this.listeUniteCavalier.size();
		nbUnitTerritoire+=this.listeUniteCanon.size();
		
		return nbUnitTerritoire;
	}
	
	public void ajouterUniteSoldat() {
		if(this.proprietaire.addUniteSoldat()) {
			this.listeUniteSoldat.add(Unite.nouveauSoldat());
		}
		
	}
	
	public void ajouterUniteCavalier() {
		if(this.proprietaire.addUniteCavalier()) {		
			this.listeUniteCavalier.add(Unite.nouveauCavalier());
		}
	}
	
	public void ajouterUniteCanon() {
		if(this.proprietaire.addUniteCanon()) {		
			this.listeUniteCanon.add(Unite.nouveauCanon());
		}
		
	}
	
	
	//Retourne la liste des territoires d'une region 
	public static Set<Territoire> listeTerritoireTestRegion() {
		Set<Territoire> territoireRegion= new HashSet<Territoire>();
		territoireRegion.add(new Territoire("Territory1"));
		territoireRegion.add(new Territoire("Territory2"));
		territoireRegion.add(new Territoire("Territory3"));
		
		
		//Ajouter adjacence entre territoire
		
		return territoireRegion;
	}
	
	
	
	
}