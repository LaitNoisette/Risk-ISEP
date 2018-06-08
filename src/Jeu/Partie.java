package Jeu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;




public class Partie {
	private int nombreJoueurActif;
	private Set<Joueur> listeJoueurs=new HashSet<Joueur>();
	private Set<Joueur> listeJoueursDetruit=new HashSet<Joueur>();
	//Gere ordre des tours des joueurs
	private Iterator<Joueur> tourJoueur;
	//Joueur en cours de jeu
	private Joueur joueurEnCours;
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
						Territoire t=	listeTerritoireCarteIterator.next();
						t.setProprietaire(j);
						j.getListeTerritoire().add(t);
						listeTerritoireCarteIterator.remove();
						
					}
				}	
			}
			
			
			
			
			
//Creation iterator pour gerer les tours des joueurs 
			this.tourJoueur=this.listeJoueurs.iterator();
			//this.gererTourJoueur();
			if(this.tourJoueur.hasNext()) {
				this.joueurEnCours=this.tourJoueur.next();
			}
			
		}

	}
	
	public Partie(ArrayList<String[]> listeJoueur,Carte c) {
		
		if (listeJoueur.size() >= 2) {
			this.nombreJoueurActif = listeJoueur.size();
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
			/*
			for (int i = 0; i < this.nombreJoueurActif; i++) {
				this.listeJoueurs.add(new Joueur("Joueur " + i, nbrArmeeBase));
			}
			*/
			
			for (String[] joueurParam : listeJoueur) {
				this.listeJoueurs.add(new Joueur(joueurParam, nbrArmeeBase));
			}

			// Distribution des territoires
			
			this.carteJeu=c;
			
			Set<Territoire> listeTerritoireCarte= this.carteJeu.getAllTerritoire();
			
			Iterator<Territoire> listeTerritoireCarteIterator= listeTerritoireCarte.iterator();
			
			while(listeTerritoireCarteIterator.hasNext()) {
				for (Joueur j : this.listeJoueurs) {
					if(listeTerritoireCarteIterator.hasNext()) {
						Territoire t=	listeTerritoireCarteIterator.next();
						t.setProprietaire(j);
						j.getListeTerritoire().add(t);
						listeTerritoireCarteIterator.remove();
						
					}
				}	
			}
			
			
			
			
			
//Creation iterator pour gerer les tours des joueurs 
			this.tourJoueur=this.listeJoueurs.iterator();
			//this.gererTourJoueur();
			if(this.tourJoueur.hasNext()) {
				this.joueurEnCours=this.tourJoueur.next();
			}
			
		}

	}
	
	public Joueur getJoueurEnCours() {
		return this.joueurEnCours;
	}
	
	public Carte getCarte() {
		return this.carteJeu;
	}
	
	//permet de passer le tour
	public void gererTourJoueur() {
		//Le joueur ne peut pas passer le premier tour tant qu'il n'a pas respecte les conditions
		if(!this.joueurEnCours.getPremierTourJoueur()) {
			
		System.out.println("Joueur Avant passer tour "+this.getJoueurEnCours().getNom());
		if(this.tourJoueur.hasNext()) {
			System.out.println("Le joueur va passer son tour !");
			this.joueurEnCours=this.tourJoueur.next();
			System.out.println("Joueur Avant passer tour "+this.getJoueurEnCours().getNom());
		}
		else {
			if(this.listeJoueurs.size()>1) {
				this.tourJoueur=this.listeJoueurs.iterator();
				this.gererTourJoueur();
			}
			
		}
		}
	}
	
	public void gererProprietaireTerritoire() {
		//this.carteJeu
	}
	
	//Fonction recuperant les proprio d'une region
	public void gererProprietaireRegion() {
		
		boolean joueurProprietaireRegionExistant=false;
		
		//Parcourt chaque region
		for (Region r : this.carteJeu.getListeRegion()) {
			//Verifie pour chaque joueur si la region lui appartient
			for (Joueur j : this.listeJoueurs) {
				boolean retourJoueurProprietaire;
				retourJoueurProprietaire= r.definirJoueurProprietaire(j);
				if(retourJoueurProprietaire) {
					joueurProprietaireRegionExistant=true;
				}
			}
			
			//Pas de proprietaire pour le territoire
			if(!joueurProprietaireRegionExistant) {
				r.setProprietaire(null);
			}
		}
		
	}
	
	public void gererRenfort() {
		for (Joueur joueur : this.listeJoueurs) {
			joueur.gererRenfort();
			
		}
	}
	
	public void reinitialiserDeplacementUniteJoueur() {
		for (Territoire t : this.carteJeu.getAllTerritoire()) {
			for (Unite u : t.getListeUniteSoldat()) {
				u.reinitialiserDeplacement();
			}
			for (Unite u : t.getListeUniteCavalier()) {
				u.reinitialiserDeplacement();
			}
			for (Unite u : t.getListeUniteCanon()) {
				u.reinitialiserDeplacement();
			}
		} 
	}
	
	public void reinitialiserNbrTerritoireConquisJoueur() {
		for (Joueur joueur : listeJoueurs) {
			joueur.reinitialiserNbrTerritoireConquisDernierTour();
		}
	}
	
	public void gererFinTour() {
		
		//Le joueur ne peut pas passer le premier tour tant qu'il n'a pas respecte les conditions
				if(this.joueurEnCours.conditionFinPremierTour()) {
					System.out.println("Le joueur peut passer son tour !");
		this.joueurFinPartie();
		finPartie=this.joueurPossedeTouteLaCarte();
		if(!finPartie) {
			this.gererProprietaireRegion();
			this.gererRenfort();
			this.reinitialiserDeplacementUniteJoueur();
			this.reinitialiserNbrTerritoireConquisJoueur();
			this.gererTourJoueur();
		}
				}
	}
	
	//Test si il ne reste qu'un unique joueur
	public boolean joueurPossedeTouteLaCarte() {
		if(this.listeJoueurs.size()<=0) {
			System.out.println("Fin de la partie");
			return true;
		}
		return false;
	}
	
	//Elimine les joueurs n'ayant plus de territoires 
	public void joueurFinPartie() {
		Iterator<Joueur> lJI=this.listeJoueurs.iterator();
		
		while(lJI.hasNext()) {
			Joueur j=lJI.next();
			if(j.getListeTerritoire().size()<=0) {
				listeJoueursDetruit.add(j);
				listeJoueurs.remove(j);
				lJI.remove();
			}
		}
	}
	
	public void affichageTest() {
		System.out.println("l'affichge fonctionne, lien controlleur/jeu nb joueur :"+this.nombreJoueurActif+" la carte son  nom est "+this.carteJeu.getNom());
	}
	

   

	public static void main(String[] args) {
		
	}
	
}
