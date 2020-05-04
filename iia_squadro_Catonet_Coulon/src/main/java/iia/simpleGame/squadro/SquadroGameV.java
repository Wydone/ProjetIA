package iia.simpleGame.squadro;

import iia.simpleGame.algo.AlphaBeta;
import iia.simpleGame.algo.AlphaBetaV2;
import iia.simpleGame.algo.Minimax;
import iia.simpleGame.base.Player;

public class SquadroGameV extends ASquadroGame {

	private Player me; 
	private Player enemy;
	
	private String myRole = "VERTICAL"; 
	private String enemyRole = "HORIZONTAL" ; 
	
    public SquadroGameV(){
    	
    	super(); 
    	
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
        int h ; 
    	//Heuristique de base
    	// objectif : avoir le plus de piece dans la position 'retour' et le plus de piece en position 'Dehors'

    	//int h = (this.getBoard().nbPieceAller(myRole) + (this.getBoard().nbPieceRetour(myRole)) + (this.getBoard().nbPieceDehors(myRole)*10)) - ((this.getBoard().nbPieceAller(enemyRole) + (this.getBoard().nbPieceRetour(enemyRole)) + (this.getBoard().nbPieceDehors(enemyRole)*10))); 

        if(role.equals("VERTICAL")) {
        	
        	h = this.getBoard().nbCoupRestantAvantVictoire(enemyRole) - this.getBoard().nbCoupRestantAvantVictoire(myRole) ;  
        	
        }else { //if role = Horizontal
        	h = this.getBoard().nbCoupRestantAvantVictoire(enemyRole) - this.getBoard().nbCoupRestantAvantVictoire(myRole) ;  
        	
        } 
    	
        return h;
    }
    
    /*
     * Heuritique qui commpare mon nombre de coups restant et le nombre de coups restant pour l'adversaire avant de gagner
     *	@param Role , SquadroBoard
     *	@return int heurisitique
     */
    // A UTILISER
    public int getValue(String role, SquadroBoard b) {
        int h ; 
        
        if(role.equals("HORIZONTAL")) {
        	
        	h = b.nbCoupRestantAvantVictoire(enemyRole) - b.nbCoupRestantAvantVictoire(myRole) ;  
        
        	
        }else {
        	
        	h = b.nbCoupRestantAvantVictoire(myRole) - b.nbCoupRestantAvantVictoire(enemyRole) ;  
        	
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
