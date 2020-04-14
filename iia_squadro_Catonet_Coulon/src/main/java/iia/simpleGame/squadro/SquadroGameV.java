package iia.simpleGame.squadro;

import iia.simpleGame.algo.Minimax;
import iia.simpleGame.base.Player;

public class SquadroGameV extends ASquadroGame {

	private Player me; 
	private Player enemy;
	
	private String myRole = "VERTICAL"; 
	private String enemyRole = "HORIZONTAL" ; 	 

    public SquadroGameV(){
    	
    	super(); 
    	  
    	//Initialisation des joueurs avec les algos avec lesquels on veut pouvoir jouer 
    	this.me = new Player("Me", myRole, new Minimax(myRole, enemyRole));
    	
    	this.enemy = new Player("Enemy", enemyRole, new Minimax(enemyRole, myRole)); 
    	
    }

 
    @Override
    public int getValue(String role) {
        
    	//Heuristique de base
    	// objectif : avoir le plus de piece dans la position 'retour' et le plus de piece en position 'Dehors'

    	int h = (this.getBoard().nbPieceAller(myRole) + (this.getBoard().nbPieceRetour(myRole)*10) + (this.getBoard().nbPieceDehors(myRole)*30)) - ((this.getBoard().nbPieceAller(enemyRole) + (this.getBoard().nbPieceRetour(enemyRole)*10) + (this.getBoard().nbPieceDehors(enemyRole)*30))); 
    	
        return h;
    }
}
