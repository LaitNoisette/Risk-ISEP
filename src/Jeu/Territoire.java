package Jeu;
import java.util.HashSet;
import java.util.Set;

public class Territoire {
	private Joueur proprietaire = null;
	private String nom;
	//private Set<Territoire> territoireAdjacent=new HashSet<Territoire>();
	private Carte carte=null;
	private Region region=null;
	
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
	
	public Region getRegion() {
		return this.region;
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
	
	public void setRegion(Region r) {
		this.region=r;
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
	
	public Set<Unite> getListeTypeUnite(String typeUnite) {
		if(typeUnite.equals("Soldat")) {
			return this.listeUniteSoldat;
		}
		else if (typeUnite.equals("Cavalier")) {
		return this.listeUniteCavalier;
		}
		else if(typeUnite.equals("Canon")) {
			return this.listeUniteCanon;
		}
		return null;
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
	
	//Place unite dans la bonne liste
	public void accueillirUnite(Unite u) {
		if(u.getNom().equals("Soldat")) {
			this.listeUniteSoldat.add(u);
		}
		else if(u.getNom().equals("Cavalier")) {
			this.listeUniteCavalier.add(u);
		}
		else if(u.getNom().equals("Canon")) {
			this.listeUniteCanon.add(u);
		}
	}
	
	//Retire unite de la bonne liste
	public void retirerUniteReserve(Unite u) {
		if(u.getNom().equals("Soldat")) {
			this.listeUniteSoldat.remove(u);
		}
		else if(u.getNom().equals("Cavalier")) {
			this.listeUniteCavalier.remove(u);
		}
		else if(u.getNom().equals("Canon")) {
			this.listeUniteCanon.remove(u);
		}
	}
	
	//Deplace une unite entre deux territoire
	public void deplacementUniteTerritoire(Territoire tDestination, Unite uDeplacer) {
		if(uDeplacer.deplacementUnitePossible()) {
			uDeplacer.diminuerDeplacementUnite();
			tDestination.accueillirUnite(uDeplacer);
			this.retirerUniteReserve(uDeplacer);
		}
		
	}
	
	//Joueur conquiert un territoire ennemi
	public void conquerirTerritoire(Joueur conquerant) {
		if(!this.getProprietaire().equals(conquerant)) {
			this.getProprietaire().getListeTerritoire().remove(this);
			this.proprietaire=conquerant;
			conquerant.getListeTerritoire().add(this);
		}
	}
	
	
	public Boolean choixTypeUniteDisponible(String typeUnite) {

		if (typeUnite.equals("Soldat")) {
			if (this.getListeUniteSoldat().size() > 0) {
				return true;
			} else {
				return false;
			}
		} else if (typeUnite.equals("Cavalier")) {
			if (this.getListeUniteCavalier().size() > 0) {
				return true;
			} else {
				return false;
			}
		} else if (typeUnite.equals("Canon")) {
			if (this.getListeUniteCanon().size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
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
	
	//Retourne la liste des territoires d'une region 
		public static Set<Territoire> listeTerritoireRegionStatenIsland() {
			Set<Territoire> territoireRegion= new HashSet<Territoire>();
			territoireRegion.add(new Territoire("Port Richmond"));
			territoireRegion.add(new Territoire("Westerleigh"));
			territoireRegion.add(new Territoire("Arden Heights"));
			
			
			//Ajouter adjacence entre territoire
			
			return territoireRegion;
		}
		
		//Retourne la liste des territoires d'une region 
				public static Set<Territoire> listeTerritoireRegionBrooklyn() {
					Set<Territoire> territoireRegion= new HashSet<Territoire>();
					territoireRegion.add(new Territoire("Port Richmond"));
					territoireRegion.add(new Territoire("Westerleigh"));
					territoireRegion.add(new Territoire("Territory3"));
					
					
					//Ajouter adjacence entre territoire
					
					return territoireRegion;
				}
				//Retourne la liste des territoires d'une region 
				public static Set<Territoire> listeTerritoireRegionQueens() {
					Set<Territoire> territoireRegion= new HashSet<Territoire>();
					territoireRegion.add(new Territoire("Port Richmond"));
					territoireRegion.add(new Territoire("Westerleigh"));
					territoireRegion.add(new Territoire("Territory3"));
					
					
					//Ajouter adjacence entre territoire
					
					return territoireRegion;
				}
				
				//Retourne la liste des territoires d'une region 
				public static Set<Territoire> listeTerritoireRegionManhattan() {
					Set<Territoire> territoireRegion= new HashSet<Territoire>();
					territoireRegion.add(new Territoire("Port Richmond"));
					territoireRegion.add(new Territoire("Westerleigh"));
					territoireRegion.add(new Territoire("Territory3"));
					
					
					//Ajouter adjacence entre territoire
					
					return territoireRegion;
				}
				
				//Retourne la liste des territoires d'une region 
				public static Set<Territoire> listeTerritoireRegionBronx() {
					Set<Territoire> territoireRegion= new HashSet<Territoire>();
					territoireRegion.add(new Territoire("Port Richmond"));
					territoireRegion.add(new Territoire("Westerleigh"));
					territoireRegion.add(new Territoire("Territory3"));
					
					
					//Ajouter adjacence entre territoire
					
					return territoireRegion;
				}
	
	
	
}
