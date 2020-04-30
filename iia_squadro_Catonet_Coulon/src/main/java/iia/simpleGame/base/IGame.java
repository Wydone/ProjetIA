package iia.simpleGame.base;


import java.util.ArrayList;

import iia.simpleGame.squadro.SquadroBoard;

public interface IGame {
    /*
    Symmetric +INFINITY and -INFINITY
    Necessary for NegAlphaBeta !
     */
    int MAX_VALUE = Integer.MAX_VALUE;
    int MIN_VALUE = - MAX_VALUE;

    int getValue(String role);
    IGame play(String move, String role);
    ArrayList<String> possibleMoves(String role);
    ArrayList<IGame> successors(String role);
    boolean isValidMove(String move, String role);
    boolean isGameOver();
    int getValue(String role, SquadroBoard p);
}
