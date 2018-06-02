package Jeu;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AdjacenceTerritoire {
	Carte carte;
	// Set<Territoire> listeTerritoires=new HashSet<Territoire>();
	HashMap<Territoire, Integer> listeTerritoire = new HashMap<Territoire, Integer>();
	int[][] adjacenceTerritoire;

	public AdjacenceTerritoire(Carte carte, int nbTerritoire) {
		this.carte = carte;
		// this.listeTerritoires=carte.getAllTerritoire();
		int indice = 0;
		for (Territoire t : carte.getAllTerritoire()) {
			listeTerritoire.put(t, indice);
			indice++;
		}
		adjacenceTerritoire = new int[nbTerritoire][nbTerritoire];
		for (int i = 0; i < nbTerritoire; i++) {
			for (int j = 0; j < nbTerritoire; j++) {
				adjacenceTerritoire[i][j] = 0;
			}
		}
	}

	public void ajouterAdjacence2Territoire(Territoire t1, Territoire t2) {
		int idTerritoire1 = this.getIdTerritoire(t1);
		int idTerritoire2 = this.getIdTerritoire(t2);

		this.adjacenceTerritoire[idTerritoire1][idTerritoire2] = 1;
		this.adjacenceTerritoire[idTerritoire2][idTerritoire1] = 1;

	}

	public int getIdTerritoire(Territoire t) {
		return listeTerritoire.get(t);
	}

	public boolean getAdjacence2Territoire(Territoire t1, Territoire t2) {

		int idTerritoire1 = this.getIdTerritoire(t1);
		int idTerritoire2 = this.getIdTerritoire(t2);

		int bool = adjacenceTerritoire[idTerritoire1][idTerritoire2];
		if (bool == 1) {
			return true;
		}

		return false;
	}

}
