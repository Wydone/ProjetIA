package iia.simpleGame.squadro;

public class PlaySquadro {
    public static void main(String[] args) {
        System.out.println("Hello world !");
        
        
        ChallengerSquadro c1 = new ChallengerSquadro(); 
        ChallengerSquadro c2 = new ChallengerSquadro(); 

        c1.setRole("HORIZONTAL");
        c2.setRole("VERTICAL");
        
        c1.iPlay("A2-B2");
        c2.otherPlay("A2-B2");
        
        System.out.println(c2.bestMove()); 
        
        /*
        ASquadroGame g = new SquadroGameH(); 
        g.play("A2-B2", "HORIZONTAL");
        g.play("A5-D5", "HORIZONTAL");
        
        g.my_board.printBoard();*/
        
        
        
        
        
    }
}
