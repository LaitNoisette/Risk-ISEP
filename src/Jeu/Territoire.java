package Jeu;
import java.util.HashSet;
import java.util.Set;

public class Territoire {
	private Joueur proprietaire = null;
	private String nom;
	//private Set<Territoire> territoireAdjacent=new HashSet<Territoire>();
	private Carte carte=null;
	
	public Territoire(String n) {
		//this.carte=c;
		this.nom=n;
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
	
	//Retourne la liste des territoires d'une region 
	public static Set<Territoire> listeTerritoireTestRegion() {
		Set<Territoire> territoireRegion= new HashSet<Territoire>();
		territoireRegion.add(new Territoire("Territory1"));
		territoireRegion.add(new Territoire("Territory2"));
		territoireRegion.add(new Territoire("Territory3"));
		
		return territoireRegion;
	}
	
	
}
