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

    /*
     * Cette version pour recupperer l'heuristique n'est pas à utiliser
     */
    //NE PAS UTILISER !!!
    @Override
    public int getValue(String role) {
    	int h;
      
    	if(role.equals("HORIZONTAL")) {
    	
    		h = this.getBoard().nbCoupRestantAvantVictoire(enemyRole) - this.getBoard().nbCoupRestantAvantVictoire(myRole); 
    		//System.out.println("Nombre de coups restant pour " + enemyRole + " est : " + this.getBoard().nbCoupRestantAvantVictoire(enemyRole));
    		//System.out.println("Nombre de coups restant pour " + myRole + " est : " + this.getBoard().nbCoupRestantAvantVictoire(myRole));
    		
    		//System.out.println("MAX HHHHHHHHHHHHHHHHHHHhh: " + h);
    	
    	}else { //si role = vertical
    		h = this.getBoard().nbCoupRestantAvantVictoire(myRole) - this.getBoard().nbCoupRestantAvantVictoire(enemyRole); 
    		//System.out.println("MIN ------------------------------------------------------------------ : " + h);
    	}
    	
        return h;
    }
    
    /*
     * Heuritique qui commpare mon nombre de coups restant et le nombre de coups restant pour l'adversaire avant de gagner
     *	@param Role , SquadroBoard
     *	@return int heurisitique
     */
    //A UTILISER !!
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
    
    //----------------------------------------------------------
    // GETTER & SETTER 
    //----------------------------------------------------------
    
    
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
