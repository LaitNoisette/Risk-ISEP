package Jeu;

import java.util.Iterator;
import java.util.Set;

public class Bataille {

	private Territoire territoireDEF;

	// private Joueur joueurAtt;
	private Territoire territoireATT;
	private Unite att1 = null;
	private Unite att2 = null;
	private Unite att3 = null;

	public Bataille(Territoire tAtt, Territoire tDEF, Unite a1, Unite a2, Unite a3) {
		this.territoireATT = tAtt;
		this.territoireDEF = tDEF;

		this.att1 = a1;
		this.att2 = a2;
		this.att3 = a3;
	}

	public Bataille(Territoire tAtt, Territoire tDEF) {
		this.territoireATT = tAtt;
		this.territoireDEF = tDEF;
	}

	// Gestion des unités de l'attaquant en fonction
	public void ajouterUniteAttaque(String typeUnite) {
		if (this.choixTypeUniteDisponible(typeUnite)) {

			Set<Unite> listeUnite = territoireATT.getListeTypeUnite(typeUnite);
			// Boolean n'ajoutant qu'une unite
			boolean ajout1UniteEnCours = true;
			// Unite ajoute en attaque
			if (listeUnite != null) {
				Iterator<Unite> listeUniteIterator = listeUnite.iterator();
				if (territoireATT.getNbrTotalUniteTerritoire() > 1) {
					while (listeUniteIterator.hasNext()) {
						Unite u = listeUniteIterator.next();
						if (u.deplacementUnitePossible()) {
							if (this.att1 == null && ajout1UniteEnCours) {
								this.setAtt1(u);
								listeUnite.remove(u);
								listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							} else if (this.att2 == null && ajout1UniteEnCours) {
								this.setAtt2(u);
								listeUnite.remove(u);
								listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							} else if (this.att3 == null && ajout1UniteEnCours) {
								this.setAtt3(u);
								listeUnite.remove(u);
								listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							}
						}
					}
				}
			}
		}

	}

	public void setAtt1(Unite att1) {
		if (territoireATT.getNbrTotalUniteTerritoire() > 1) {
			this.att1 = att1;
		}
	}
	
	public void retirerAtt1() {
		this.territoireATT.getListeTypeUnite(this.att1.getNom()).add(att1);
		this.att1=null;
	}
	public void retirerAtt2() {
		this.territoireATT.getListeTypeUnite(this.att2.getNom()).add(att2);
		this.att2=null;
	}
	public void retirerAtt3() {
		this.territoireATT.getListeTypeUnite(this.att3.getNom()).add(att3);
		this.att3=null;
	}

	public void setAtt2(Unite att2) {
		if (territoireATT.getNbrTotalUniteTerritoire() > 1) {
			this.att2 = att2;
		}
	}

	public void setAtt3(Unite att3) {
		if (territoireATT.getNbrTotalUniteTerritoire() > 1) {
			this.att3 = att3;
		}
	}

	public Boolean choixTypeUniteDisponible(String typeUnite) {

		if (typeUnite.equals("Soldat")) {
			if (this.territoireATT.getListeUniteSoldat().size() > 0) {
				return true;
			} else {
				return false;
			}
		} else if (typeUnite.equals("Cavalier")) {
			if (this.territoireATT.getListeUniteCavalier().size() > 0) {
				return true;
			} else {
				return false;
			}
		} else if (typeUnite.equals("Canon")) {
			if (this.territoireATT.getListeUniteCanon().size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	public void jouerBataille() {

		// Bataille reussi
		// Consommation deplacement unite
		// Changement territoire unite
		//
		// this.att1.
	}

}
