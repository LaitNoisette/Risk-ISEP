package Jeu;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Bataille {

	private Territoire territoireDEF;
	private Unite def1=null;
	private Unite def2=null;

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
		if (territoireATT.choixTypeUniteDisponible(typeUnite)) {

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
	
	
	public void ajouterUniteDefense() {
		//Choix unite defense 1
		//Cavalier
		if(territoireDEF.choixTypeUniteDisponible("Cavalier")) {
			Set<Unite> listeUnite = territoireDEF.getListeTypeUnite("Cavalier");
			// Boolean n'ajoutant qu'une unite
			boolean ajout1UniteEnCours = true;
			// Unite ajoute en attaque
			if (listeUnite != null) {
				Iterator<Unite> listeUniteIterator = listeUnite.iterator();
					while (listeUniteIterator.hasNext()) {
						Unite u = listeUniteIterator.next();
						
							if (this.def1 == null && ajout1UniteEnCours) {
								this.def1=u;
								listeUnite.remove(u);
								listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							} 
						
					}
				
			}
		}
		//Canon
		else if (territoireDEF.choixTypeUniteDisponible("Canon")) {
			Set<Unite> listeUnite = territoireDEF.getListeTypeUnite("Canon");
			// Boolean n'ajoutant qu'une unite
			boolean ajout1UniteEnCours = true;
			// Unite ajoute en attaque
			if (listeUnite != null) {
				Iterator<Unite> listeUniteIterator = listeUnite.iterator();
					while (listeUniteIterator.hasNext()) {
						Unite u = listeUniteIterator.next();
						
							if (this.def1 == null && ajout1UniteEnCours) {
								this.def1=u;
								listeUnite.remove(u);
								listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							}
						
					}
				
			}
		}
		//Soldat
		else if(territoireDEF.choixTypeUniteDisponible("Soldat")){
			Set<Unite> listeUnite = territoireDEF.getListeTypeUnite("Soldat");
			// Boolean n'ajoutant qu'une unite
			boolean ajout1UniteEnCours = true;
			// Unite ajoute en attaque
			if (listeUnite != null) {
				Iterator<Unite> listeUniteIterator = listeUnite.iterator();
					while (listeUniteIterator.hasNext()) {
						Unite u = listeUniteIterator.next();
						
							if (this.def1 == null && ajout1UniteEnCours) {
								this.def1=u;
								listeUnite.remove(u);
								listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							} 
						
					}
				
			}
		}
		
		//Choix unite defense 2
		//Cavalier
				if(territoireDEF.choixTypeUniteDisponible("Cavalier")) {
					Set<Unite> listeUnite = territoireDEF.getListeTypeUnite("Cavalier");
					// Boolean n'ajoutant qu'une unite
					boolean ajout1UniteEnCours = true;
					// Unite ajoute en attaque
					if (listeUnite != null) {
						Iterator<Unite> listeUniteIterator = listeUnite.iterator();
							while (listeUniteIterator.hasNext()) {
								Unite u = listeUniteIterator.next();
								
									if (this.def2 == null && ajout1UniteEnCours) {
										this.def2=u;
										listeUnite.remove(u);
										listeUniteIterator.remove();
										ajout1UniteEnCours = false;
									} 
								
							}
						
					}
				}
				//Canon
				else if (territoireDEF.choixTypeUniteDisponible("Canon")) {
					Set<Unite> listeUnite = territoireDEF.getListeTypeUnite("Canon");
					// Boolean n'ajoutant qu'une unite
					boolean ajout1UniteEnCours = true;
					// Unite ajoute en attaque
					if (listeUnite != null) {
						Iterator<Unite> listeUniteIterator = listeUnite.iterator();
							while (listeUniteIterator.hasNext()) {
								Unite u = listeUniteIterator.next();
								
									if (this.def2 == null && ajout1UniteEnCours) {
										this.def2=u;
										listeUnite.remove(u);
										listeUniteIterator.remove();
										ajout1UniteEnCours = false;
									}
								
							}
						
					}
				}
				//Soldat
				else if(territoireDEF.choixTypeUniteDisponible("Soldat")){
					Set<Unite> listeUnite = territoireDEF.getListeTypeUnite("Soldat");
					// Boolean n'ajoutant qu'une unite
					boolean ajout1UniteEnCours = true;
					// Unite ajoute en attaque
					if (listeUnite != null) {
						Iterator<Unite> listeUniteIterator = listeUnite.iterator();
							while (listeUniteIterator.hasNext()) {
								Unite u = listeUniteIterator.next();
								
									if (this.def2 == null && ajout1UniteEnCours) {
										this.def2=u;
										listeUnite.remove(u);
										listeUniteIterator.remove();
										ajout1UniteEnCours = false;
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
	
	public void puissanceUniteBataille() {
		if(this.att1!=null) {
			this.att1.setPuissanceBataille(calculPuissance(this.att1.getPuissanceMin(), this.att1.getPuissanceMax()));
		}
		
		if(this.att2!=null) {
			this.att2.setPuissanceBataille(calculPuissance(this.att2.getPuissanceMin(), this.att2.getPuissanceMax()));
		}
		
		if(this.att3!=null) {
			this.att3.setPuissanceBataille(calculPuissance(this.att3.getPuissanceMin(), this.att3.getPuissanceMax()));
		}
		
		
		if(this.def1!=null) {
			this.def1.setPuissanceBataille(calculPuissance(this.def1.getPuissanceMin(), this.def1.getPuissanceMax()));
		}
		
		if(this.def2!=null) {
			this.def2.setPuissanceBataille(calculPuissance(this.def2.getPuissanceMin(), this.def2.getPuissanceMax()));
		}
	}
	
	public int calculPuissance(int min,int max) {
		int randomNum=ThreadLocalRandom.current().nextInt(min, max+1);
		return randomNum;
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
	
	public void trierDefenseur() {
		
	}
	
	public void trierAttaquant() {
		
	}

	public void jouerBataille() {
		this.ajouterUniteDefense();
		this.puissanceUniteBataille();
		
		//Parcourir chaque attaquant et prendre le plus puissant avec la priorité la plus faible
		
		
		//Parcourir chaque defenseur et prendre le plus puissant avec la priorité la plus faible
		
		
		//Bataille
		//Detruire les unite ayant perdus
		
		//Rendre unite restante territoire
		
		// Bataille reussi
		// Consommation deplacement unite
		// Changement territoire unite
		//
		// this.att1.
		
	}

}
