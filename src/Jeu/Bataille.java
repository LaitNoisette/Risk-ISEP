package Jeu;

public class Bataille {
	
	private Territoire territoireDEF;
	
	
	//private Joueur joueurAtt;
	private Territoire territoireATT;
	private Unite att1;
	private Unite att2;
	private Unite att3;
	
	public Bataille(Territoire tAtt,Territoire tDEF,Unite a1,Unite a2,Unite a3) {
		this.territoireATT=tAtt;
		this.territoireDEF=tDEF;
		
		this.att1=a1;
		this.att2=a2;
		this.att3=a3;
	}
	
	public Bataille(Territoire tAtt,Territoire tDEF) {
		this.territoireATT=tAtt;
		this.territoireDEF=tDEF;
	}
	
	public void setAtt1(Unite att1) {
		if(territoireATT.getNbrTotalUniteTerritoire()>1) {
			this.att1=att1;
		}
	}
	
	public void setAtt2(Unite att2) {
		if(territoireATT.getNbrTotalUniteTerritoire()>1) {
			this.att2=att2;
		}
	}
	
	public void setAtt3(Unite att3) {
		if(territoireATT.getNbrTotalUniteTerritoire()>1) {
			this.att3=att3;
		}
	}
	
	public Boolean choixTypeUniteDisponible(String typeUnite) {
		if(typeUnite.equals("Soldat")) {
			if(this.territoireATT.getListeUniteSoldat().size()>0) {
				
			}
		}
		return true;
	}
	
	
	public void jouerBataille() {
		
		//Bataille reussi
		//this.att1.
	}
	

}
