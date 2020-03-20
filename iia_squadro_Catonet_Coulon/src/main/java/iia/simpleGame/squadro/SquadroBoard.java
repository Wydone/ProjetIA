package iia.simpleGame.squadro;

import java.util.ArrayList;

public class SquadroBoard implements IPartie2 {
	
	public final static int TAILLE = 7 ; //Taille de la grille
	    
    /* Le damier */
    public char board[][]; 
    
    public ArrayList<PieceSquadro> listJ1 = new ArrayList<PieceSquadro>() ; //nom à changer mais = liste des pieces appartennant au joueur 1
    public ArrayList<PieceSquadro> listJ2 = new ArrayList<PieceSquadro>() ; // idem = liste des pieces appartenant au joueur 2
    
    public final static char VIDE = '.' ; 
    
    //Modélisation des pieces du joueur1
    public final static char DROITE = '>' ; 
    public final static char GAUCHE = '<' ; 
    
    //Modelisation des pieces du joueur2 
    public final static char HAUT = '^'; 
    public final static char BAS = 'v'; 
    
    
    public SquadroBoard() { //Creation du plateau initial 
    	
    	this.board = new char[TAILLE][TAILLE]; 
    	
    	//Remplissage du board
    	for(int i=0; i < TAILLE; i++) { 
    		
    		for (int j=0; j < TAILLE ; j++) {
    			
    			if(j==0 && ( (i != 0 ) && (i !=TAILLE-1) ) ) { 
    				board[i][j] = DROITE ; 
    				
    			}else if( (i == TAILLE-1) && ((j != 0) && (j != TAILLE -1)) ) {
    				board[i][j] = HAUT ; 
    				
    			}else {
    				board[i][j] = VIDE; 
    			}
    		}
    	}
    	
    	
    	//Remplissage des listes de piece des joueurs 
    	for(int i=1; i<TAILLE-1; i++) {
    		
    		int valDepl = 0; 
    		
    		switch(i) {
	    		case 1 : valDepl = 1; break; 
	    		case 2 : valDepl = 3; break;
	    		case 3 : valDepl = 2 ; break; 
	    		case 4 : valDepl = 3; break; 
	    		case 5 : valDepl = 1 ; break; 
    		}
    	
    		PieceSquadro p1 = new PieceSquadro(0,i,valDepl,'A'); 
    		listJ1.add(p1); 
    				
    	}
    	
    	for(int j=1; j<TAILLE-1; j++) {
    		
    		int valDepl = 0; 
    		
    		switch(j) {
	    		case 1 : valDepl = 3; break; 
	    		case 2 : valDepl = 1; break;
	    		case 3 : valDepl = 2 ; break; 
	    		case 4 : valDepl = 1; break; 
	    		case 5 : valDepl = 3 ; break; 
    		}
    	
    		PieceSquadro p2 = new PieceSquadro(j,TAILLE-1,valDepl,'A'); 
    		listJ2.add(p2); 
    				
    	}
    	
    	
    }
	    
	    //Création à partir d'un plateau existant 
    public SquadroBoard(char depuis[][]) {
    	
    	this.board = new char[TAILLE][TAILLE]; 
    	
    	for(int i=0 ; i < TAILLE ; i++) {
    		for (int j=0; j < TAILLE ; j++) {
    			board[i][j] = depuis[i][j]; 
    		}
    	}
    }

	@Override
	public void setFromFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValidMove(String move, String player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] possibleMoves(String player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void play(String move, String role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void printBoard() {
    	for(int i = 0; i<7; i++) {
        	for (int j=0; j<7 ; j++) {
        		System.out.print(board[i][j]);
        	}
        	System.out.print("\n");
        }
    }
	
	
	
	
	//---------------------------------------
	// MAIN
	//---------------------------------------
	
	public static void main(String[] args) {
        System.out.println("Hello world !");
        
        SquadroBoard b = new SquadroBoard(); 
     
        b.printBoard();

        System.out.println(" ======= GAME OVER =======");

    }

}

