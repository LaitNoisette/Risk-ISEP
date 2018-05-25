import java.util.Set;

public class Region {
	private Joueur proprietaire = null;
	private Set<Territoire> listeTerritoire;
	
	
	public Set<Territoire> getListeTerritoire(){
		return this.listeTerritoire;
	}
}
