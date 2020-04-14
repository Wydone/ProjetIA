package iia.simpleGame.algo;

import java.util.ArrayList;

import iia.simpleGame.base.IGame;

public class AlwaysFirst implements IAlgo {

	@Override
	public String bestMove(IGame game, String role) {
		
		ArrayList<String> all_moves = game.possibleMoves(role); 
		
		return (all_moves.size() > 0) ? all_moves.get(0) : null ;
	}

	
}
