package iia.simpleGame.squadro;

import iia.simpleGame.algo.Minimax;
import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IGame;
import iia.simpleGame.base.Player;
import iia.simpleGame.nim.ANimGame;

import java.util.ArrayList;


//Classe intermediaire pour bien utiliser les algos
public abstract class ASquadroGame extends AGame {
	
	SquadroBoard my_board ; 
	
	//Creation d'un game en interne
    public ASquadroGame(){
      	
    	my_board = new SquadroBoard(); 
    }

    @Override
    public IGame play(String move, String role) {
    		
    	//Joueur le coup sur le plateau 
    	my_board.play(move, role);
    		
    	//Retourne l'etat de la game	
        return this;
    }

    //return la liste des coups possible pour un joueur
    @Override
    public ArrayList<String> possibleMoves(String role) {
        
    	
    	ArrayList<String> resultat = new ArrayList<String>() ; 
    	String[] coupsPossibles = my_board.possibleMoves(role); 
    	
    	for(int i=0; i<coupsPossibles.length; i++) {
    		resultat.add(coupsPossibles[i]);
    	}
    	
        return resultat;
    }

    @Override
    public boolean isValidMove(String move, String role) {
        
        return my_board.isValidMove(move, role);
    }

    @Override
    public boolean isGameOver() {
        
        return my_board.gameOver();
    }

   
	public SquadroBoard getBoard() {
		return this.my_board;
	}
}
