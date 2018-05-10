import java.util.Set;

public class Joueur {

	private String nomJoueur;
	private Set<Unite> listeUnite;
	private boolean joueurDetruit = false;
	private int pointUnite = 0;

	public Joueur(String nom, int nbrArmeeBase) {
		this.nomJoueur = nom;

		// Creation de l'armee du joueur
		for (int i = 0; i < nbrArmeeBase; i++) {
			this.listeUnite.add(Unite.nouveauSoldat());
		}
	}

	public Set<Unite> getListeUnite() {
		return this.listeUnite;
	}

	public void addUnite(Unite u) {
		this.listeUnite.add(u);
	}
}
