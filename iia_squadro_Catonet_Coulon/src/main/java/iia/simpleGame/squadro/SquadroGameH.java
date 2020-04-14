package iia.simpleGame.squadro;

import iia.simpleGame.algo.Minimax;
import iia.simpleGame.base.Player;


//Objet signifiant que dans cette partie, NOUS sommes le joueur Horizontal
public class SquadroGameH extends ASquadroGame {
	
	private Player me; 
	private Player enemy;
	
	private String myRole = "HORIZONTAL"; 
	private String enemyRole = "VERTICAL" ; 	 

    public SquadroGameH(){
    	
    	super(); 
    	  
    	//Initialisation des joueurs avec les algos avec lesquels on veut pouvoir jouer 
    	this.me = new Player("Me", myRole, new Minimax(myRole, enemyRole));
    	
    	this.enemy = new Player("Enemy", enemyRole, new Minimax(enemyRole, myRole)); 
    	
    }

    @Override
    public int getValue(String role) {
        // TODO heuristic for Horizontal player
    	
    	//MEME heuristique que l'autre classe
    	int h = (this.getBoard().nbPieceAller(myRole) + (this.getBoard().nbPieceRetour(myRole)*10) + (this.getBoard().nbPieceDehors(myRole)*30)) - ((this.getBoard().nbPieceAller(enemyRole) + (this.getBoard().nbPieceRetour(enemyRole)*10) + (this.getBoard().nbPieceDehors(enemyRole)*30))); 
    	
    	
        return 0;
    }
}
