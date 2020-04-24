package iia.simpleGame.squadro;


public class PieceSquadro {
	
	private int x ; 
	private int y ; 
	
	private int valeurDeplacement ; 
	private char statut ; // A = aller , R = retour , D = en dehors du plateu 
	
	public PieceSquadro(int posX, int posY, int valDepl, char statut) {
		
		this.x = posX; 
		this.y = posY ; 
		this.valeurDeplacement = valDepl ; 
		this.statut = statut ; 
		
	}
	
	public PieceSquadro copy(int x, int y, int valeurDeplacement, char statut) {
		return new PieceSquadro(x,y,valeurDeplacement, statut);
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValeurDeplacement() {
		return valeurDeplacement;
	}

	public void setValeurDeplacement(int valeurDeplacement) {
		this.valeurDeplacement = valeurDeplacement;
	}

	public char getStatut() {
		return statut;
	}

	public void setStatut(char statut) {
		this.statut = statut;
	}
	
	

}
