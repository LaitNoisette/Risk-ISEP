package Jeu;
import java.util.HashSet;
import java.util.Set;

public class Region {
	private Joueur proprietaire = null;
	private Set<Territoire> listeTerritoire= new HashSet<Territoire>();
	private String nom;
	private Boolean plusGrandeRegion=false;
	
	//Constructeur region de base 
	public Region(String n,Set<Territoire> lT) {
		this.nom=n;
		this.listeTerritoire=lT;
	}
	
	//Constructeur pour region la plus grande
	public Region(String n,Set<Territoire> lT,Boolean plusGRegion) {
		this.nom=n;
		this.listeTerritoire=lT;
		this.plusGrandeRegion=plusGRegion;
	}
	
	public Boolean getPlusGrandeRegion() {
		return this.plusGrandeRegion;
	}
	
	public Set<Territoire> getListeTerritoire(){
		return this.listeTerritoire;
	}
	
	//Createur region carte NY
	public static Region RegionTestCarteNY() {
		return new Region("Test",Territoire.listeTerritoireTestRegion());
	}
}
