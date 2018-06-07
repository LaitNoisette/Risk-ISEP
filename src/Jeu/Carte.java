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
		//Staten Island
				this.recupererTerritoireNOM("Port Richmond").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Westerleigh"));
				this.recupererTerritoireNOM("Port Richmond").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Coney Island"));
				
				this.recupererTerritoireNOM("Westerleigh").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Port Richmond"));
				this.recupererTerritoireNOM("Westerleigh").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Arden Heights"));
				
				this.recupererTerritoireNOM("Arden Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Westerleigh"));
				this.recupererTerritoireNOM("Arden Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Chinatown"));
	}
	
	public void ajouterAdjacenceTerritoireRegionManhattanCarteNY() {
		//Mahnattan
				this.recupererTerritoireNOM("Chinatown").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Arden Heights"));
				this.recupererTerritoireNOM("Chinatown").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hells Kitchen"));
				this.recupererTerritoireNOM("Chinatown").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Turtle Bay"));

				this.recupererTerritoireNOM("Hells Kitchen").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Chinatown"));
				this.recupererTerritoireNOM("Hells Kitchen").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Turtle Bay"));
				this.recupererTerritoireNOM("Hells Kitchen").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));

				this.recupererTerritoireNOM("Turtle Bay").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Chinatown"));
				this.recupererTerritoireNOM("Turtle Bay").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hells Kitchen"));
				this.recupererTerritoireNOM("Turtle Bay").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));
				this.recupererTerritoireNOM("Turtle Bay").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));
				
				this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Turtle Bay"));
				this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hells Kitchen"));
				this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Parkchester"));
				this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Bronxdale"));
				this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
				this.recupererTerritoireNOM("Harlem").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));
	}
	
	public void ajouterAdjacenceTerritoireRegionBronxCarteNY() {
		//Bronx 
		this.recupererTerritoireNOM("Parkchester").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
		this.recupererTerritoireNOM("Parkchester").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));

		
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Parkchester"));
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Bronxdale"));
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Rikers Island"));
		this.recupererTerritoireNOM("Belmont").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Longwood"));
		
		this.recupererTerritoireNOM("Bronxdale").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Rikers Island"));
		this.recupererTerritoireNOM("Bronxdale").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));
		this.recupererTerritoireNOM("Bronxdale").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));

		this.recupererTerritoireNOM("Rikers Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));
		this.recupererTerritoireNOM("Rikers Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Bronxdale"));
		this.recupererTerritoireNOM("Rikers Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Longwood"));
		this.recupererTerritoireNOM("Rikers Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Whitestone"));

		this.recupererTerritoireNOM("Longwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Rikers Island"));
		this.recupererTerritoireNOM("Longwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Belmont"));	
	}
	
	public void ajouterAdjacenceTerritoireRegionBrooklynCarteNY() {
		//Brooklyn
				this.recupererTerritoireNOM("Williamsburg").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Harlem"));
				this.recupererTerritoireNOM("Williamsburg").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Astoria"));
				this.recupererTerritoireNOM("Williamsburg").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Glendale"));
				this.recupererTerritoireNOM("Williamsburg").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Lindenwood"));
				this.recupererTerritoireNOM("Williamsburg").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Canarsie"));
				this.recupererTerritoireNOM("Williamsburg").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Midwood"));
				this.recupererTerritoireNOM("Williamsburg").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Red Hook"));

				this.recupererTerritoireNOM("Red Hook").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));
				this.recupererTerritoireNOM("Red Hook").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Midwood"));
				this.recupererTerritoireNOM("Red Hook").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Fort Hamilton"));

				this.recupererTerritoireNOM("Midwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Red Hook"));
				this.recupererTerritoireNOM("Midwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));
				this.recupererTerritoireNOM("Midwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Canarsie"));
				this.recupererTerritoireNOM("Midwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Fort Hamilton"));
				this.recupererTerritoireNOM("Midwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Coney Island"));

				this.recupererTerritoireNOM("Canarsie").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Midwood"));
				this.recupererTerritoireNOM("Canarsie").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));
				this.recupererTerritoireNOM("Canarsie").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Coney Island"));
				this.recupererTerritoireNOM("Canarsie").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Barren Island"));
				this.recupererTerritoireNOM("Canarsie").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Lindenwood"));
				this.recupererTerritoireNOM("Canarsie").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));
				
				this.recupererTerritoireNOM("Barren Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Somerville"));
				this.recupererTerritoireNOM("Barren Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Coney Island"));
				this.recupererTerritoireNOM("Barren Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Canarsie"));
				
				this.recupererTerritoireNOM("Coney Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Canarsie"));
				this.recupererTerritoireNOM("Coney Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Fort Hamilton"));
				this.recupererTerritoireNOM("Coney Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Barren Island"));
				this.recupererTerritoireNOM("Coney Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Port Richmond"));
				this.recupererTerritoireNOM("Coney Island").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Midwood"));

				this.recupererTerritoireNOM("Fort Hamilton").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Midwood"));
				this.recupererTerritoireNOM("Fort Hamilton").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Red Hook"));
				this.recupererTerritoireNOM("Fort Hamilton").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Coney Island"));
	}
	
	public void ajouterAdjacenceTerritoireRegionQueensCarteNY() {
		//Queens
				this.recupererTerritoireNOM("Whitestone").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Rikers Island"));
				this.recupererTerritoireNOM("Whitestone").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Oakland Gardens"));
				this.recupererTerritoireNOM("Whitestone").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Jackson Heights"));
				this.recupererTerritoireNOM("Whitestone").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hillcrest"));

				this.recupererTerritoireNOM("Oakland Gardens").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Whitestone"));
				this.recupererTerritoireNOM("Oakland Gardens").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Springfield"));
				this.recupererTerritoireNOM("Oakland Gardens").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hillcrest"));

				this.recupererTerritoireNOM("Springfield").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Oakland Gardens"));
				this.recupererTerritoireNOM("Springfield").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hillcrest"));
				this.recupererTerritoireNOM("Springfield").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Somerville"));
				this.recupererTerritoireNOM("Springfield").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Lindenwood"));

				this.recupererTerritoireNOM("Hillcrest").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Springfield"));
				this.recupererTerritoireNOM("Hillcrest").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Oakland Gardens"));
				this.recupererTerritoireNOM("Hillcrest").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Whitestone"));
				this.recupererTerritoireNOM("Hillcrest").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Jackson Heights"));
				this.recupererTerritoireNOM("Hillcrest").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Glendale"));
				this.recupererTerritoireNOM("Hillcrest").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Lindenwood"));

				this.recupererTerritoireNOM("Jackson Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Astoria"));
				this.recupererTerritoireNOM("Jackson Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Glendale"));
				this.recupererTerritoireNOM("Jackson Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Whitestone"));
				this.recupererTerritoireNOM("Jackson Heights").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hillcrest"));

				this.recupererTerritoireNOM("Astoria").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Glendale"));
				this.recupererTerritoireNOM("Astoria").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Jackson Heights"));
				this.recupererTerritoireNOM("Astoria").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));

				this.recupererTerritoireNOM("Lindenwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Springfield"));
				this.recupererTerritoireNOM("Lindenwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Hillcrest"));
				this.recupererTerritoireNOM("Lindenwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Williamsburg"));
				this.recupererTerritoireNOM("Lindenwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Somerville"));
				this.recupererTerritoireNOM("Lindenwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Glendale"));
				this.recupererTerritoireNOM("Lindenwood").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Canarsie"));

				this.recupererTerritoireNOM("Somerville").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Lindenwood"));
				this.recupererTerritoireNOM("Somerville").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Springfield"));
				this.recupererTerritoireNOM("Somerville").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Barren Island"));
				this.recupererTerritoireNOM("Somerville").ajouterListeTerritoireAdjacent(this.recupererTerritoireNOM("Canarsie"));
	}
}
