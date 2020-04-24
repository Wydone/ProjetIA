package iia.simpleGame.squadro;

import java.util.ArrayList;
import iia.simpleGame.base.Player;
import iia.simpleGame.algo.AlphaBeta;

public class PlaySquadro {
    public static void main(String[] args) {
      /*  System.out.println("Hello world !");
        
        
        ChallengerSquadro c1 = new ChallengerSquadro(); 
        ChallengerSquadro c2 = new ChallengerSquadro(); 

        c1.setRole("HORIZONTAL");
        c2.setRole("VERTICAL");
        
        c1.iPlay("A2-B2");
        c2.otherPlay("A2-B2");
        
        */
    	
    	
    	 ChallengerSquadro c1 = new ChallengerSquadro(); 
         ChallengerSquadro c2 = new ChallengerSquadro(); 
         c1.setRole("HORIZONTAL");
         c2.setRole("VERTICAL");
         
        System.out.println("START");

        SquadroBoard board = new SquadroBoard();
          
    	SquadroGameH hj1 = new SquadroGameH();

        ChallengerSquadro[] lesJoueurs = new ChallengerSquadro[2];

        lesJoueurs[0] = c1; 
        lesJoueurs[1] = c2;
        
        System.out.println("TD IIA n.3 - Algorithmes pour les Jeux");
        System.out.println("Etat Initial du plateau de jeu:");

        String meilleurCoup = null;
        int jnum;



        // A chaque it�ration de la boucle, on fait jouer un des deux joueurs
        // tour a tour
        jnum = 0; // On commence par le joueur Blanc (arbitraire)
        board.printBoard();

        System.out.println("Coups possibles pour J1" + lesJoueurs[jnum].getMy_role() + " : " + board.possibleMoves(lesJoueurs[0].getMy_role()));
        System.out.println("Coups possibles pour J2" + lesJoueurs[jnum].getMy_role() + " : " + board.possibleMoves(lesJoueurs[1].getMy_role()));

        do{
        	
            System.out.println("C'est au joueur " + lesJoueurs[jnum].getMy_role() + " de jouer.");
            System.out.println("\nPlateau AVANT action.");
            board.printBoard();

            // V�rifie qu'il y a bien des coups possibles

            ArrayList<String> CoupsPossibles = board.possibleMoves(lesJoueurs[jnum].getMy_role());
            System.out.println("Coups possibles pour " + lesJoueurs[jnum].getMy_role() + " : " + CoupsPossibles);

                // Lancement de l'algo de recherche du meilleur coup
                System.out.println("Recherche du meilleur coup avec l'algo Alpha Beta");
                
                
                meilleurCoup = (((AlphaBeta)lesJoueurs[jnum].getMy_algo()).bestMove(board));
                
                System.out.println("Coup jou� : " + meilleurCoup + " par le joueur " + lesJoueurs[jnum].getMy_role());
                System.out.println("Liste coup possible jou� : " + board.possibleMoves(lesJoueurs[jnum].getMy_role()) + "\n");

                board.play(meilleurCoup, lesJoueurs[jnum].getMy_role());
                // Le coup est effectivement jou�
                jnum = 1 - jnum;

                System.out.println("\nPlateau APRES action.");
                board.printBoard();

        }while(board.gameOver());
        board.printBoard();

        System.out.println("Fin de partie");
        System.out.println("Le joueur " + lesJoueurs[1-jnum] + " a gagn� cette partie !");
    


        
    }
}
