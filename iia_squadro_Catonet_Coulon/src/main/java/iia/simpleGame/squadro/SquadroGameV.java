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
        // TODO heuristic for Vertical player
        return 0;
    }
}
