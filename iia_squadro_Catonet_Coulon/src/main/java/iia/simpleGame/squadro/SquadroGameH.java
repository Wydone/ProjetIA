package iia.simpleGame.squadro;

import iia.simpleGame.algo.AlphaBeta;
import iia.simpleGame.algo.AlphaBetaV2;
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
    	//this.me = new Player("Me", myRole, new Minimax(myRole, enemyRole));
    	
    	//this.enemy = new Player("Enemy", enemyRole, new Minimax(enemyRole, myRole)); 
    	
    	
    	//Algo alphabeta
    	this.me = new Player("Me", myRole, new AlphaBetaV2(this,myRole, enemyRole));
    	
    	this.enemy = new Player("Enemy", enemyRole, new  AlphaBetaV2(this,enemyRole, myRole)); 
    	
    	my_board.printBoard();
    	
    	
    }

    @Override
    public int getValue(String role) {
    	int h;
        // TODO heuristic for Horizontal player
    	
    	//MEME heuristique que l'autre classe
    	//int h = (this.getBoard().nbPieceAller(myRole) + (this.getBoard().nbPieceRetour(myRole)) + (this.getBoard().nbPieceDehors(myRole)*10)) - ((this.getBoard().nbPieceAller(enemyRole) + (this.getBoard().nbPieceRetour(enemyRole)) + (this.getBoard().nbPieceDehors(enemyRole)*10))); 
    	
    	//System.out.println("SquadroBoard H enemy : " + this.getBoard().nbCoupRestantAvantVictoire(enemyRole) ) ;
    	//System.out.println("SquadroBoard H MOI : " + this.getBoard().nbCoupRestantAvantVictoire(myRole) ) ;
    	//System.out.println("RES : " + (this.getBoard().nbCoupRestantAvantVictoire(enemyRole) - this.getBoard().nbCoupRestantAvantVictoire(myRole)) );
    	
    	if(role.equals("HORIZONTAL")) {
    	
    		h = this.getBoard().nbCoupRestantAvantVictoire(enemyRole) - this.getBoard().nbCoupRestantAvantVictoire(myRole); 
    		System.out.println("Nombre de coups restant pour " + enemyRole + " est : " + this.getBoard().nbCoupRestantAvantVictoire(enemyRole));
    		System.out.println("Nombre de coups restant pour " + myRole + " est : " + this.getBoard().nbCoupRestantAvantVictoire(myRole));
    		
    		System.out.println("MAX HHHHHHHHHHHHHHHHHHHhh: " + h);
    	
    	}else { //si role = vertical
    		h = this.getBoard().nbCoupRestantAvantVictoire(myRole) - this.getBoard().nbCoupRestantAvantVictoire(enemyRole); 
    		System.out.println("MIN ------------------------------------------------------------------ : " + h);
    	}
    	
        return h;
    }
    
    
    public int getValue(String role, SquadroBoard b) {
    	int h;
        // TODO heuristic for Horizontal player
    	
    	if(role.equals("HORIZONTAL")) {
    	
    		h = b.nbCoupRestantAvantVictoire(enemyRole) - b.nbCoupRestantAvantVictoire(myRole); 
    		//System.out.println("Nombre de coups restant pour " + enemyRole + " est : " + b.nbCoupRestantAvantVictoire(enemyRole));
    		//System.out.println("Nombre de coups restant pour " + myRole + " est : " + b.nbCoupRestantAvantVictoire(myRole));
    		
    		//System.out.println("MAX HHHHHHHHHHHHHHHHHHHhh: " + h);
    	
    	}else { //si role = vertical
    		h = b.nbCoupRestantAvantVictoire(myRole) - b.nbCoupRestantAvantVictoire(enemyRole); 
    		//System.out.println("MIN ------------------------------------------------------------------ : " + h);
    	}
    	
        return h;
    }
    
    public String getEnemyRole() {
    	return enemyRole;
    }
    
    public Player getMyPlayer() {
    	return me ; 
    }
    public Player getEnemyPlayer() {
    	return enemy;
    }


}
