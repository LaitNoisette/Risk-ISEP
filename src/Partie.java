import java.util.Set;




public class Partie {
	private int nombreJoueurActif;
	private Set<Joueur> listeJoueurs;
	private Carte carteJeu;
	private boolean finPartie = false;

	public Partie(int nbrJoueur) {
		if (nbrJoueur >= 2) {
			this.nombreJoueurActif = nbrJoueur;
			int nbrArmeeBase = 0;

			// Nombre d'armee distribue au depart
			switch (this.nombreJoueurActif) {
			case 2:
				nbrArmeeBase = 40;
				break;
			case 3:
				nbrArmeeBase = 35;
				break;
			case 4:
				nbrArmeeBase = 30;
				break;
			case 5:
				nbrArmeeBase = 25;
				break;
			case 6:
				nbrArmeeBase = 20;
				break;
			}

			// Creation nombre joueur de la partie
			for (int i = 0; i < this.nombreJoueurActif; i++) {
				this.listeJoueurs.add(new Joueur("Joueur " + i, nbrArmeeBase));
			}

			// Distribution des territoires

		}

	}
	

   /*

	public static void main(String[] args) {
		
	}
	*/
}
