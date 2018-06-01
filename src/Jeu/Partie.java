package Jeu;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;




public class Partie {
	private int nombreJoueurActif;
	private Set<Joueur> listeJoueurs=new HashSet<Joueur>();
	private Carte carteJeu;
	private boolean finPartie = false;

	public Partie(int nbrJoueur,Carte c) {
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
			
			this.carteJeu=c;
			
			Set<Territoire> listeTerritoireCarte= this.carteJeu.getAllTerritoire();
			
			Iterator<Territoire> listeTerritoireCarteIterator= listeTerritoireCarte.iterator();
			
			while(listeTerritoireCarteIterator.hasNext()) {
				for (Joueur j : this.listeJoueurs) {
					if(listeTerritoireCarteIterator.hasNext()) {
						listeTerritoireCarteIterator.next().setProprietaire(j);
						listeTerritoireCarteIterator.remove();
					}
				}	
			}
			
			
			
			
			

		}

	}
	
	public void affichageTest() {
		System.out.println("l'affichge fonctionne, lien controlleur/jeu nb joueur :"+this.nombreJoueurActif+" la carte son  nom est "+this.carteJeu.getNom());
	}
	

   

	public static void main(String[] args) {
		
	}
	
}
