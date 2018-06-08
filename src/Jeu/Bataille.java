package Jeu;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Bataille {

	private Territoire territoireDEF=null;
	private Unite def1=null;
	private Unite def2=null;

	// private Joueur joueurAtt;
	private Territoire territoireATT=null;
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
	
	public Bataille(Territoire tDEF) {
		this.territoireDEF=tDEF;
	}
	
	public void setTerritoireAtt(Territoire tAtt) {
		if(this.territoireATT==null) {
			this.territoireATT=tAtt;
		}
		
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
					while (listeUniteIterator.hasNext() && ajout1UniteEnCours) {
						if(listeUniteIterator.hasNext()) {
													
						Unite u = listeUniteIterator.next();
						if (u.deplacementUnitePossible()) {
							if (this.att1 == null && ajout1UniteEnCours) {
								this.setAtt1(u);
								System.out.println("ATT 1 :"+u.getNom());
								//listeUnite.remove(u);
								listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							} else if (this.att2 == null && ajout1UniteEnCours) {
								this.setAtt2(u);
								System.out.println("ATT 2 :"+u.getNom());
								listeUnite.remove(u);
								//listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							} else if (this.att3 == null && ajout1UniteEnCours) {
								this.setAtt3(u);
								System.out.println("ATT 3 :"+u.getNom());
								listeUnite.remove(u);
								//listeUniteIterator.remove();
								ajout1UniteEnCours = false;
							}
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
					while (listeUniteIterator.hasNext() && ajout1UniteEnCours) {
						Unite u = listeUniteIterator.next();
						
							if (this.def1 == null && ajout1UniteEnCours) {
								this.def1=u;
								
								listeUnite.remove(u);
								//listeUniteIterator.remove();
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
					while (listeUniteIterator.hasNext() && ajout1UniteEnCours) {
						Unite u = listeUniteIterator.next();
						
							if (this.def1 == null && ajout1UniteEnCours) {
								this.def1=u;
								System.out.println("DEF é :"+u.getNom());
								listeUnite.remove(u);
								//listeUniteIterator.remove();
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
					while (listeUniteIterator.hasNext() && ajout1UniteEnCours ) {
						Unite u = listeUniteIterator.next();
						
							if (this.def1 == null && ajout1UniteEnCours) {
								this.def1=u;
								listeUnite.remove(u);
								//listeUniteIterator.remove();
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
							while (listeUniteIterator.hasNext() && ajout1UniteEnCours) {
								Unite u = listeUniteIterator.next();
								
									if (this.def2 == null && ajout1UniteEnCours) {
										this.def2=u;
										listeUnite.remove(u);
										//listeUniteIterator.remove();
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
							while (listeUniteIterator.hasNext() && ajout1UniteEnCours) {
								Unite u = listeUniteIterator.next();
								
									if (this.def2 == null && ajout1UniteEnCours) {
										this.def2=u;
										listeUnite.remove(u);
										//listeUniteIterator.remove();
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
							while (listeUniteIterator.hasNext() && ajout1UniteEnCours) {
								Unite u = listeUniteIterator.next();
								
									if (this.def2 == null && ajout1UniteEnCours) {
										this.def2=u;
										listeUnite.remove(u);
										//listeUniteIterator.remove();
										ajout1UniteEnCours = false;
									} 
								
							}
						
					}
				}
				System.out.println("DEF 1 :"+this.def1.getNom());
				System.out.println("DEF 2 : "+this.def2.getNom());
		
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
			this.att1.setPuissanceBataille(this.calculPuissance(this.att1.getPuissanceMin(), this.att1.getPuissanceMax()));
		}
		
		if(this.att2!=null) {
			this.att2.setPuissanceBataille(this.calculPuissance(this.att2.getPuissanceMin(), this.att2.getPuissanceMax()));
		}
		
		if(this.att3!=null) {
			this.att3.setPuissanceBataille(this.calculPuissance(this.att3.getPuissanceMin(), this.att3.getPuissanceMax()));
		}
		
		
		if(this.def1!=null) {
			this.def1.setPuissanceBataille(this.calculPuissance(this.def1.getPuissanceMin(), this.def1.getPuissanceMax()));
		}
		
		if(this.def2!=null) {
			this.def2.setPuissanceBataille(this.calculPuissance(this.def2.getPuissanceMin(), this.def2.getPuissanceMax()));
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
		if(this.def2!=null) {
			if(this.def2.getPuissanceBataille()>this.def1.getPuissanceBataille()) {
				Unite uTemp=this.def1;
				this.def1=this.def2;
				this.def2=uTemp;
			}
			else if(this.def2.getPuissanceBataille()==this.def1.getPuissanceBataille() && this.def2.getPrioriteDEF()<this.def1.getPrioriteDEF()) {
				Unite uTemp=this.def1;
				this.def1=this.def2;
				this.def2=uTemp;
			}
		}
	}
	
	public void trierAttaquant() {
		
		if(this.att3!=null) {
			//Liste trois attaquant
			Unite[] listeUniteATT=new Unite[3];
			listeUniteATT[0]=this.att1;
			listeUniteATT[1]=this.att2;
			listeUniteATT[2]=this.att3;
			
			boolean triFini=false;
			while(!triFini) {
				triFini=true;
				for(int i=0;i<(listeUniteATT.length-1);i++) {
					int j=i+1;
					
						if(listeUniteATT[j].getPuissanceBataille()>listeUniteATT[i].getPuissanceBataille()) {
							Unite uTemp=listeUniteATT[j];
							listeUniteATT[j]=listeUniteATT[i];
							listeUniteATT[i]=uTemp;
							triFini=false;
						}
						//Puissance equivalente on prend la priorite la plus basse
						else if(listeUniteATT[i].getPuissanceBataille()==listeUniteATT[j].getPuissanceBataille() && listeUniteATT[j].getPrioriteATT()<listeUniteATT[i].getPrioriteATT()) {
							Unite uTemp=listeUniteATT[j];
							listeUniteATT[j]=listeUniteATT[i];
							listeUniteATT[i]=uTemp;
							triFini=false;
						}
					
					
					
				}
			}
			this.att1=listeUniteATT[0];
			this.att2=listeUniteATT[1];
			this.att3=listeUniteATT[2];
			
		}
		else if(this.att2!=null) {
			if(this.att2.getPuissanceBataille()>this.att1.getPuissanceBataille()) {
				Unite uTemp=this.att1;
				this.def1=this.att2;
				this.att2=uTemp;
			}
			else if(this.att2.getPuissanceBataille()==this.att1.getPuissanceBataille() && this.att2.getPrioriteDEF()<this.att1.getPrioriteDEF()) {
				Unite uTemp=this.att1;
				this.att1=this.att2;
				this.att2=uTemp;
			}
		}
		
	}
	
	public boolean victoireAttaquant() {
		if(this.def1==null && this.def2==null && this.territoireDEF.getNbrTotalUniteTerritoire()<=0) {
			return true;
		}
		
		return false;
	}
	//Bataille entre 2 unite
	//Renvoie true si attaquant gagne
	//Renvoie false si le defenseur gagne
	public boolean batailleUnite(Unite att,Unite def) {
		if(att.getPuissanceBataille()==def.getPuissanceBataille()) {
		Set<Unite> listeUniteATT=this.territoireATT.getListeTypeUnite(att.getNom());
		if(listeUniteATT.contains(att)) {
			
			//listeUniteATT.remove(att);
		}
		return false;
		
		}
		else if(att.getPuissanceBataille()>def.getPuissanceBataille()) {
			Set<Unite> listeUniteATT=this.territoireDEF.getListeTypeUnite(def.getNom());
			if(listeUniteATT.contains(def)) {
				//listeUniteATT.remove(def);
			}
			return true;
		}
		else {
			Set<Unite> listeUniteATT=this.territoireATT.getListeTypeUnite(att.getNom());
			if(listeUniteATT.contains(att)) {
				//listeUniteATT.remove(att);
			}
			return false;
		}
	}
	
	public void gererUniteVictoireAttaquantPostBataille() {
		if(this.att1!=null) {
			this.att1.ajouterUniteConqueranteTerritoire(this.territoireDEF);
		}
		
		if(this.att2!=null) {
			this.att2.ajouterUniteConqueranteTerritoire(this.territoireDEF);

		}
		
		if(this.att3!=null) {
			this.att3.ajouterUniteConqueranteTerritoire(this.territoireDEF);
		}
	}
	
	public void gererUniteVictoireDefenseurPostBataille() {
		if(this.att1!=null) {
			//this.att1.ajouterUniteConqueranteTerritoire(this.territoireATT);
			Set<Unite> tLUnite=this.territoireATT.getListeTypeUnite(this.att1.getNom());
			tLUnite.add(this.att1);
			this.att1=null;
		}
		
		if(this.att2!=null) {
			//this.att2.ajouterUniteConqueranteTerritoire(this.territoireATT);
			Set<Unite> tLUnite=this.territoireATT.getListeTypeUnite(this.att2.getNom());
			tLUnite.add(this.att2);
			this.att2=null;
		}
		
		if(this.att3!=null) {
			//this.att3.ajouterUniteConqueranteTerritoire(this.territoireATT);
			Set<Unite> tLUnite=this.territoireATT.getListeTypeUnite(this.att3.getNom());
			tLUnite.add(this.att3);
			this.att3=null;
		}
		
		if(this.def1!=null) {
			//this.def1.ajouterUniteConqueranteTerritoire(this.territoireDEF);
			Set<Unite> tLUnite=this.territoireDEF.getListeTypeUnite(this.def1.getNom());
			tLUnite.add(this.def1);
			this.def1=null;
		}
		
		if(this.def2!=null) {
			//this.def1.ajouterUniteConqueranteTerritoire(this.territoireDEF);
			Set<Unite> tLUnite=this.territoireDEF.getListeTypeUnite(this.def2.getNom());
			tLUnite.add(this.def2);
			this.def2=null;
		}
	}

	public void jouerBataille() {
		this.ajouterUniteDefense();
		this.puissanceUniteBataille();
		System.out.println("--- Unité avant tri ---");
		System.out.println("ATT 1 :"+this.att1.getNom()+" Puissance : "+this.att1.getPuissanceBataille());
		System.out.println("ATT 2 :"+this.att2.getNom()+" Puissance : "+this.att2.getPuissanceBataille());
		System.out.println("ATT 3 :"+this.att3.getNom()+" Puissance : "+this.att3.getPuissanceBataille());
		
		System.out.println("DEF 1 :"+this.def1.getNom()+" Puissance : "+this.def1.getPuissanceBataille());
		System.out.println("DEF 2 :"+this.def2.getNom()+" Puissance : "+this.def2.getPuissanceBataille());
		
		this.trierDefenseur();
		this.trierAttaquant();
		
		System.out.println("--- Unité après tri ---");
		System.out.println("ATT 1 :"+this.att1.getNom()+" Puissance : "+this.att1.getPuissanceBataille());
		System.out.println("ATT 2 :"+this.att2.getNom()+" Puissance : "+this.att2.getPuissanceBataille());
		System.out.println("ATT 3 :"+this.att3.getNom()+" Puissance : "+this.att3.getPuissanceBataille());
		
		System.out.println("DEF 1 :"+this.def1.getNom()+" Puissance : "+this.def1.getPuissanceBataille());
		System.out.println("DEF 2 :"+this.def2.getNom()+" Puissance : "+this.def2.getPuissanceBataille());
		
		if(!victoireAttaquant()) {
			if(this.att1!=null && this.def1!=null) {
				if(this.batailleUnite(this.att1, this.def1)) {
					System.out.println("Victoire attaquant");
					this.def1=null;
					
				}
				else {
					System.out.println("Victoire défenseur");
					this.att1=null;
				}
			}
			
			if(this.att2!=null && this.def2!=null) {
				if(this.batailleUnite(this.att2, this.def2)) {
					System.out.println("Victoire attaquant");
					this.def2=null;
					
				}
				else {
					System.out.println("Victoire défenseur");

					this.att2=null;
				}
			}
			
			if(victoireAttaquant()) {
				this.territoireDEF.conquerirTerritoire(this.territoireATT.getProprietaire());
				this.gererUniteVictoireAttaquantPostBataille();
			}
			else {
				this.gererUniteVictoireDefenseurPostBataille();
			}
			
		}
		
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
