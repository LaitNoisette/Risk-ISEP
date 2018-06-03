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
		for (Territoire territoire : lT) {
			territoire.setRegion(this);
		}
	}
	
	//Constructeur pour region la plus grande
	public Region(String n,Set<Territoire> lT,Boolean plusGRegion) {
		this.nom=n;
		this.listeTerritoire=lT;
		this.plusGrandeRegion=plusGRegion;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
	
	public Boolean getPlusGrandeRegion() {
		return this.plusGrandeRegion;
	}
	
	public Set<Territoire> getListeTerritoire(){
		return this.listeTerritoire;
	}
	
	public void setProprietaire(Joueur j) {
		
		if(j!=null) {
			if(this.proprietaire!=null) {
				//Si le proprietaire recuperant le territoire est different du proprio actuel
				if(!this.proprietaire.getNom().equals(j.getNom())) {
					this.proprietaire.getListeRegion().remove(this);
					this.proprietaire=j;
					j.getListeRegion().add(this);
				}
				
			}
			else {
				this.proprietaire=j;
			}
		}
		else {
			if(this.proprietaire!=null) {
				this.proprietaire.getListeRegion().remove(this);
				this.proprietaire=null;
				
			}
			else {
				//Proprio est null
				this.proprietaire=j;
			}
		}
		
		
		
	}
	
	public boolean definirJoueurProprietaire(Joueur j) {
		boolean joueurProprietaire=false;
		
		//Parcourt tous les territoire de la region 
		for (Territoire territoireRegion : this.listeTerritoire) {
			boolean joueurProprietaireTerritoire=false;
			//Parcourt tous les territoires 
			for (Territoire territoireJoueur : j.getListeTerritoire()) {
				if(territoireRegion.territoireEquivalent(territoireJoueur)) {
					joueurProprietaireTerritoire=true;
				}
			}
			joueurProprietaire=joueurProprietaireTerritoire;
		}
		
		if(joueurProprietaire) {
			this.setProprietaire(j);
		}
		
		return joueurProprietaire;
		
		
	}
	
	//Createur region carte NY
	public static Region RegionTestCarteNY() {
		return new Region("Test",Territoire.listeTerritoireTestRegion());
	}
	//Createur region carte NY
		public static Region RegionStatenIslandCarteNY() {
			return new Region("Staten Island",Territoire.listeTerritoireRegionStatenIsland());
		}
		//Createur region carte NY
				public static Region RegionBrooklynCarteNY() {
					return new Region("Brooklyn",Territoire.listeTerritoireRegionBrooklyn());
				}
				//Createur region carte NY
				public static Region RegionQueensCarteNY() {
					return new Region("Queens",Territoire.listeTerritoireRegionQueens());
				}
				//Createur region carte NY
				public static Region RegionManhattanCarteNY() {
					return new Region("Manhattan",Territoire.listeTerritoireRegionManhattan());
				}
				//Createur region carte NY
				public static Region RegionBronxCarteNY() {
					return new Region("Bronx",Territoire.listeTerritoireRegionBronx());
				}
}
