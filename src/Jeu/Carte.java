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
		Carte carteNY=new Carte("NY", lR);
		
		for (Region region : carteNY.listeRegion) {
			if(region.getPlusGrandeRegion()) {
				if (carteNY.plusGrandeRegion==null) {
					carteNY.plusGrandeRegion=region;
				}
			}
		}
		
		return carteNY;
	}
}
