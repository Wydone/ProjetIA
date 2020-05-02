package iia.simpleGame.squadro;

import java.util.ArrayList;
import java.util.Date;

import iia.simpleGame.base.Player;
import iia.simpleGame.algo.AlphaBeta;
import iia.simpleGame.algo.AlphaBetaV2;

public class PlaySquadro {
    public static void main(String[] args) {
        System.out.println("Hello world !");
        
        ChallengerSquadro c1 = new ChallengerSquadro(); 
        ChallengerSquadro c2 = new ChallengerSquadro(); 
        c1.setRole("HORIZONTAL");
        c2.setRole("VERTICAL");
       
        c1.iPlay("A2-B2");
        c2.otherPlay("A2-B2");
        c1.iPlay("B2-C2");
        c2.otherPlay("B2-C2");
        c1.iPlay("C2-D2");
        c2.otherPlay("C2-D2");
        c1.iPlay("D2-E2");
        c2.otherPlay("D2-E2");
        
        c1.iPlay("A3-D3");
        c2.otherPlay("A3-D3");
        c1.iPlay("D3-G3");
        c2.otherPlay("D3-G3");
        c1.iPlay("G3-F3");
        c2.otherPlay("G3-F3");
        
        c1.iPlay("A4-C4");
        c2.otherPlay("A4-C4");
        c1.iPlay("C4-E4");
        c2.otherPlay("C4-E4");
        c1.iPlay("E4-G4");
        c2.otherPlay("E4-G4");
        c1.iPlay("G4-E4");
        c2.otherPlay("G4-E4");
        c1.iPlay("E4-C4");
        c2.otherPlay("E4-C4");
        c1.iPlay("C4-A4");
        c2.otherPlay("C4-A4");
        
        c1.iPlay("A5-D5");
        c2.otherPlay("A5-D5");
        c1.iPlay("D5-G5");
        c2.otherPlay("D5-G5");
        
        // vertical 
        c2.iPlay("B7-B4");
        c1.otherPlay("B7-B4");
        c2.iPlay("B4-B1");
        c1.otherPlay("B4-B1");
        c2.iPlay("B1-B2");
        c1.otherPlay("B1-B2");
        c2.iPlay("B2-B3");
        c1.otherPlay("B2-B3");
        c2.iPlay("B3-B4");
        c1.otherPlay("B3-B4");
        c2.iPlay("B4-B5");
        c1.otherPlay("B4-B5");
        c2.iPlay("B5-B6");
        c1.otherPlay("B5-B6");
        c2.iPlay("B6-B7");
        c1.otherPlay("B6-B7");
        
        c2.iPlay("C7-C6");
        c1.otherPlay("C7-C6");
        c2.iPlay("C6-C5");
        c1.otherPlay("C6-C5");
        c2.iPlay("C5-C4");
        c1.otherPlay("C5-C4");
        c2.iPlay("C4-C3");
        c1.otherPlay("C4-C3");
        c2.iPlay("C3-C2");
        c1.otherPlay("C3-C2");
        c2.iPlay("C2-C1");
        c1.otherPlay("C2-C1");
        c2.iPlay("C1-C4");
        c1.otherPlay("C1-C4");
        c2.iPlay("C4-C7");
        c1.otherPlay("C4-C7");
        
        c2.iPlay("D7-D5");
        c1.otherPlay("D7-D5");
        c2.iPlay("D5-D3");
        c1.otherPlay("D5-D3");
        c2.iPlay("D3-D1");
        c1.otherPlay("D3-D1");
        c2.iPlay("D1-D3");
        c1.otherPlay("D1-D3");
        c2.iPlay("D3-D5");
        c1.otherPlay("D3-D5");
       
        c2.iPlay("E7-E6");
        c1.otherPlay("E7-E6");
        c2.iPlay("E6-E5");
        c1.otherPlay("E6-E5");
        c2.iPlay("E5-E4");
        c1.otherPlay("E5-E4");
        
        System.out.println("pMove C2 : " + c2.getGame().possibleMoves("VERTICAL"));
        
        
        
        
        
      /*
       

       SquadroBoard board = new SquadroBoard();
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
int cpt =0;
       do{
       	cpt++;
           System.out.println("C'est au joueur " + lesJoueurs[jnum].getMy_role() + " de jouer.");
           System.out.println("\nPlateau AVANT action.");
           board.printBoard();

           // V�rifie qu'il y a bien des coups possibles

           ArrayList<String> CoupsPossibles = board.possibleMoves(lesJoueurs[jnum].getMy_role());
           System.out.println("Coups possibles pour " + lesJoueurs[jnum].getMy_role() + " : " + CoupsPossibles);

               // Lancement de l'algo de recherche du meilleur coup
               System.out.println("Recherche du meilleur coup avec l'algo Alpha Beta");
               
               long startTime = System.currentTimeMillis();
               
               meilleurCoup = (((AlphaBetaV2)lesJoueurs[jnum].getMy_algo()).bestMove(board));

               long elapsedTime = (new Date()).getTime() - startTime;
               
               //System.out.println("Dure coup en ms : " + elapsedTime );

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
   


        
        
        
        /*
        
        ChallengerSquadro c1 = new ChallengerSquadro(); 
        ChallengerSquadro c2 = new ChallengerSquadro(); 
        //c1.tie();

        c1.setRole("HORIZONTAL");
        c2.setRole("VERTICAL");
        
        c1.iPlay("A2-B2");
        c2.otherPlay("A2-B2");
        c1.iPlay("B2-C2");
        c2.otherPlay("B2-C2");
        
        c2.iPlay("C7-C6");
        c1.otherPlay("C7-C6");
        c2.iPlay("C6-C5");
        c1.otherPlay("C6-C5");
        
        System.out.println("H de c1 : "+c1.getGame().getValue("HORIZONTAL"));
        System.out.println("H de c2 : "+c2.getGame().getValue("VERTICAL"));
        
        System.out.println("C1 possibles moves : " + c1.getGame().possibleMoves("HORIZONTAL"));
        
        c1.iPlay("A5-D5");
        c2.otherPlay("A5-D5");
        
        System.out.println("H de c1 : "+c1.getGame().getValue("HORIZONTAL"));
        System.out.println("H de c2 : "+c2.getGame().getValue("VERTICAL"));
        System.out.println("TEST");
        System.out.println(c1.bestMove());
      
       // c1.getGame().my_board.reverseCoup("A5-D5", "HORIZONTAL");
      //  c1.getGame().my_board.printBoard();
        
        //System.out.println("c1 eval : " + c1.getGame().getValue("HORIZONTAL")) ; 
        //System.out.println("TEST : "+c1.getGame().getBoard().nbCoupRestantAvantVictoire("HORIZONTAL"));
        //System.out.println("TEST 2: "+c2.getGame().getBoard().nbCoupRestantAvantVictoire("VERTICALE"));
        //System.out.println("c2 eval : " + c2.getGame().getValue("VERTICALE")) ; 
        
        /*
        //System.out.println(c1.getGame().my_board.possibleMoves("HORIZONTAL")); 
        c1.iPlay("A6-B6");
        c1.getGame().my_board.printBoard();
       // System.out.println("Mon eval : " + c1.getGame().getValue("VERTICALE")) ; 
        
         
        
       // c2.otherPlay("A6-B6");
        //c2.getGame().my_board.printBoard(); 
       // System.out.println("c2 eval : " + c2.getGame().getValue("VERTICAL")) ; 
        
       
        
       // System.out.println(c2.getGame().my_board.possibleMoves("VERTICAL")); 
        c2.iPlay("F7-F4");
        c2.getGame().my_board.printBoard();
        c1.otherPlay("F7-F4");
       // System.out.println("Mon eval pour c2 : " + c2.getGame().getValue("VERTICAL")) ; 
        c1.getGame().my_board.printBoard();
       
        //c2.otherPlay("A7-B4");
        
        */
        
        //Test sur kes retour en arriere des pieces 
        
       /* c1.iPlay("A6-B6");
        c2.otherPlay("A6-B6");
        c1.iPlay("B6-C6");
        c2.otherPlay("B6-C6");
        c1.iPlay("C6-D6");
        c2.otherPlay("C6-D6");
        c1.iPlay("D6-E6");
        c2.otherPlay("D6-E6");
        c1.iPlay("E6-F6");
        c2.otherPlay("E6-F6");
        c1.iPlay("F6-G6");
        c2.otherPlay("F6-G6");
        
        c2.iPlay("E7-E6");
        c1.otherPlay("E7-E6");
        c2.iPlay("C7-C6");
        c1.otherPlay("C7-C6"); 
        
       
        System.out.println(c1.getGame().possibleMoves("HORIZONTAL"));
        
        c1.iPlay("G6-D6");
        c2.otherPlay("G6-D6");
        
        System.out.println("OK FIN TEST D'ERREUR 1 ------------------------------------");
        
        System.out.println(c2.getGame().possibleMoves("VERTICALE"));
        c2.iPlay("C6-C5");
        c1.otherPlay("C6-C5");
        
        c2.iPlay("C5-C4");
        c1.otherPlay("C5-C4");
        c2.iPlay("C4-C3");
        c1.otherPlay("C4-C3");
        c2.iPlay("C3-C2");
        c1.otherPlay("C3-C2");
        c2.iPlay("C2-C1");
        c1.otherPlay("C2-C1");
        
        c2.iPlay("C1-C2");
        c1.otherPlay("C1-C2");
        
        c1.iPlay("A2-B2");
        c2.otherPlay("A2-B2");
        
        System.out.println(c1.getGame().possibleMoves("HORIZONTAL"));
        //c1.iPlay("B2-C2");
        //c2.otherPlay("B2-C2");
        
        //System.out.println(c1.getGame().possibleMoves("HORIZONTAL"));
        //c1.iPlay("A4-C4");
        //c2.otherPlay("A4-C4");
        
    	 /*ChallengerSquadro c1 = new ChallengerSquadro(); 
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
    
		*/

        
    }
}
