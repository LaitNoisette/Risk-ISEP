import java.util.HashSet;
import java.util.Set;

public class Territoire {
	private Joueur proprietaire = null;
	//private Set<Territoire> territoireAdjacent=new HashSet<Territoire>();
	private Carte carte;
	
	public Territoire(Carte c) {
		this.carte=c;
	}
	
	public void ajouterAdjacence(Territoire tAdjacent) {
		this.carte.getAdjacenceTerritoire().ajouterAdjacence2Territoire(this, tAdjacent);
	}
	
	public boolean getAdjacenceTerritoire(Territoire t) {
		return this.carte.getAdjacenceTerritoire().getAdjacence2Territoire(this, t);
	}
	
}
