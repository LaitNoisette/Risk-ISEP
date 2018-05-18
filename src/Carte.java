import java.util.HashSet;
import java.util.Set;

public class Carte {
	private Set<Region> listeRegion;
	private Region plusGrandeRegion;
	private AdjacenceTerritoire adjacenceTerritoire;
	
	public Carte() {
		//this.adjacenceTerritoire=new AdjacenceTerritoire(this, nbTerritoire)
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
}
