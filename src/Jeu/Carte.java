package Jeu;
import java.util.HashSet;
import java.util.Set;

public class Carte {
	private Set<Region> listeRegion= new HashSet<Region>();
	private Region plusGrandeRegion=null;
	private AdjacenceTerritoire adjacenceTerritoire;
	private String nom;
	
	public Carte(String n, Set<Region> lR) {
		//this.adjacenceTerritoire=new AdjacenceTerritoire(this, nbTerritoire)
		
		this.nom=n;
		this.listeRegion=lR;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Set<Region> getListeRegion(){
		return this.listeRegion;
	}
	
	
	/*
	 * Fonction recuperant tous les territoires de toute les regions
	 */
	public Set<Territoire> getAllTerritoire(){
		Set<Territoire> listeEnsembleTerritoire= new HashSet<Territoire>();
		
		for (Region r : this.listeRegion) {
			for (Territoire t : r.getListeTerritoire()) {
				listeEnsembleTerritoire.add(t);
			}
		}
		
		
		return listeEnsembleTerritoire;
	}
	
	public AdjacenceTerritoire getAdjacenceTerritoire() {
		return this.adjacenceTerritoire;
	}
	
	//Recupere un territoire a partir de son nom
	public Territoire recupererTerritoireNOM(String nomTerritoire) {
		Territoire tRecup=null;
		for (Territoire t : this.getAllTerritoire()) {
			String tNom=t.getNom();
			if(tNom.equals(new String(nomTerritoire))) {
				tRecup=t;
			}
		}
		return tRecup;
	}
	
	public static Carte CarteTestNY() {
		Set<Region> lR= new HashSet<Region>();
		lR.add(Region.RegionTestCarteNY());
		Carte carteTestNY=new Carte("NY", lR);
		
		for (Region region : carteTestNY.listeRegion) {
			if(region.getPlusGrandeRegion()) {
				if (carteTestNY.plusGrandeRegion==null) {
					carteTestNY.plusGrandeRegion=region;
				}
			}
		}
		
		return carteTestNY;
	}
	
	public static Carte CarteNY() {
		Set<Region> lR= new HashSet<Region>();
		lR.add(Region.RegionStatenIslandCarteNY());
		lR.add(Region.RegionBronxCarteNY());
		lR.add(Region.RegionBrooklynCarteNY());
		lR.add(Region.RegionManhattanCarteNY());
		lR.add(Region.RegionQueensCarteNY());
		Carte carteNY=new Carte("NY", lR);
		
		carteNY.ajouterAdjacenceTerritoireRegionStatenIslandCarteNY();
		carteNY.ajouterAdjacenceTerritoireRegionBronxCarteNY();
		carteNY.ajouterAdjacenceTerritoireRegionManhattanCarteNY();
		
		for (Region region : carteNY.listeRegion) {
			if(region.getPlusGrandeRegion()) {
				if (carteNY.plusGrandeRegion==null) {
					carteNY.plusGrandeRegion=region;
				}
			}
		}
		
		return carteNY;
	}
	
	public void ajouterAdjacenceTerritoireRegionStatenIslandCarteNY() {
		//Region interne
		this.recupererTerritoireNOM("Port Richmond").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Westerleigh"));
		
		this.recupererTerritoireNOM("Westerleigh").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Port Richmond"));
		this.recupererTerritoireNOM("Westerleigh").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Arden Heights"));
		
		this.recupererTerritoireNOM("Arden Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Westerleigh"));
		
		//Lien autre region
		this.recupererTerritoireNOM("Arden Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Chinatown"));
		this.recupererTerritoireNOM("Arden Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Fort Hamilton"));
	}
	
	public void ajouterAdjacenceTerritoireRegionManhattanCarteNY() {
		//Region interne
		this.recupererTerritoireNOM("Chinatown").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hells Kitchen"));
		this.recupererTerritoireNOM("Chinatown").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Turtle Bay"));
		
		this.recupererTerritoireNOM("Hells Kitchen").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Chinatown"));
		this.recupererTerritoireNOM("Hells Kitchen").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Turtle Bay"));
		this.recupererTerritoireNOM("Hells Kitchen").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));
		
		this.recupererTerritoireNOM("Turtle Bay").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Chinatown"));
		this.recupererTerritoireNOM("Turtle Bay").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hells Kitchen"));
		this.recupererTerritoireNOM("Turtle Bay").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));
		
		this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Turtle Bay"));
		this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hells Kitchen"));
		
		//Lien autre region
		this.recupererTerritoireNOM("Chinatown").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Arden Heights"));
		
		this.recupererTerritoireNOM("Hells Kitchen").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Red Hook"));
		
		this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Parkchester"));
		this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
		this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Bronxdale"));
	}
	
	public void ajouterAdjacenceTerritoireRegionBronxCarteNY() {
		//Region interne
		this.recupererTerritoireNOM("Parkchester").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
		
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Parkchester"));
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Bronxdale"));
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Longwood"));
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Rikers Island"));
		
		this.recupererTerritoireNOM("Bronxdale").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
		this.recupererTerritoireNOM("Bronxdale").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Rikers Island"));
		
		this.recupererTerritoireNOM("Rikers Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Bronxdale"));
		this.recupererTerritoireNOM("Rikers Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
		this.recupererTerritoireNOM("Rikers Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Longwood"));
		
		this.recupererTerritoireNOM("Longwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
		this.recupererTerritoireNOM("Longwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Rikers Island"));
		
		//Lien autre region
		this.recupererTerritoireNOM("Chinatown").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Arden Heights"));
		
	}
}
