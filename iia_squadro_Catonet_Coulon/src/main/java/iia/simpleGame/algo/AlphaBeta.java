package iia.simpleGame.algo;



import java.util.ArrayList;

import iia.simpleGame.base.IGame;
import iia.simpleGame.base.Player;
import iia.simpleGame.squadro.ASquadroGame;
import iia.simpleGame.squadro.SquadroBoard;
import iia.simpleGame.squadro.SquadroGameH;
import iia.simpleGame.squadro.SquadroGameV;



public class AlphaBeta implements IAlgo {
	
	
    /** La profondeur de recherche par d�faut
     */
    private final static int PROFMAXDEFAUT = 8;

   
    // -------------------------------------------
    // Attributs
    // -------------------------------------------
 
    /**  La profondeur de recherche utilis�e pour l'algorithme
     */
    private int profMax = PROFMAXDEFAUT;

     /**  L'SquadroGameH utilis�e par l'algorithme
      */
   private SquadroGameH h_H;
   private SquadroGameV h_V;
   private IGame game;
   private ArrayList<String> pileCoups;

    /** Le Player Min
     *  (l'adversaire) */
    private String PlayerMin;

    /** Le Player Max
     * (celui dont l'algorithme de recherche adopte le point de vue) */
    private String PlayerMax;

    /**  Le nombre de noeuds d�velopp� par l'algorithme
     * (int�ressant pour se faire une id�e du nombre de noeuds d�velopp�s) */
       private int nbnoeuds;

    /** Le nombre de feuilles �valu�es par l'algorithme
     */
    private int nbfeuilles;
    private ArrayList<String> pileCoup = new ArrayList<String>();


  // -------------------------------------------
  // Constructeurs
  // -------------------------------------------
    public AlphaBeta(IGame game,String Role_PlayerMax, String Role_PlayerMin) {
        this(game,Role_PlayerMax,Role_PlayerMin,PROFMAXDEFAUT);
    }

    public AlphaBeta(IGame game,String Role_PlayerMax, String Role_PlayerMin, int profMaxi) {

    	this.game = game;
        this.PlayerMax = Role_PlayerMax;
        this.PlayerMin = Role_PlayerMin;
        profMax = profMaxi;
//		System.out.println("Initialisation d'un MiniMax de profondeur " + profMax);
    }
    
    
    
    

   // -------------------------------------------
  // M�thodes de l'interface AlgoJeu
  // -------------------------------------------
   public String bestMove(SquadroBoard p) {

	   System.out.println("PLAYER BEST MOVE  : " +this.PlayerMax);
	   return alphaBeta(PROFMAXDEFAUT, p); 

    }
   
   
	@Override
	public String bestMove(IGame game, String role) {
		
		SquadroBoard myBoard = ((ASquadroGame) game).getBoard();
		
		return this.bestMove(myBoard); 
	}
	
	
  // -------------------------------------------
  // M�thodes publiques
  // -------------------------------------------
    public String toString() {
        return "MiniMax(ProfMax="+profMax+")";
    }



  // -------------------------------------------
  // M�thodes internes
  // -------------------------------------------

    //A vous de jouer pour implanter Minimax
    
    public String alphaBeta(int current_prof, SquadroBoard p) {
    	
    	
    	int alpha = (int)Float.NEGATIVE_INFINITY;
    	int beta = (int)Float.POSITIVE_INFINITY;
    	int Max  = (int)Float.NEGATIVE_INFINITY;
    	String MeilleurCoup = null; 

    	SquadroBoard pCopy0 = p.copy(); 
    	
    	System.out.println("Les coups possibles pour le joueur : " +pCopy0.possibleMoves(this.PlayerMax) );
    	for (String next_coup : pCopy0.possibleMoves(this.PlayerMax)) {
    		

    		//pileCoup.add(next_coup);
    		SquadroBoard pCopy = pCopy0.copy(); 
    		pCopy.play(next_coup, this.PlayerMax);
    		
       

       		int newVal = minMax(current_prof-1, pCopy, alpha, beta); 
    		

        	if(newVal > Max) {
       			MeilleurCoup = next_coup; 
       			Max = newVal; 
       			//System.out.println("new max-----------------------------------------------");
        	}
       	}
    	
    	System.out.println("Best Coup trouvé par AlphaBeta :" +MeilleurCoup);
    	return MeilleurCoup; 
    }
    
    
    
    
    
    public int maxMin (int current_prof, SquadroBoard p, int alpah, int beta) {
       // System.out.println("DANS MAX!");
    
    	if((current_prof == 0) ||  (p.possibleMoves(this.PlayerMax).size()==0)) {
    		if (this.PlayerMax.equals("HORIZONTAL")) {
        		//return game.getValue(this.PlayerMax);
        		return game.getValue(this.PlayerMax, p);
    		
    		}else {
        		//return game.getValue(this.PlayerMin);
        		return game.getValue(this.PlayerMin, p);
    		
    		}
    	}else {

    		for (String c :p.possibleMoves(this.PlayerMax)) {
    	    	SquadroBoard pCopy = p.copy(); 
        	//	pileCoup.add(c);
    	    	pCopy.play(c, this.PlayerMax);
    	    	
    			alpah = Math.max(alpah, minMax(current_prof-1, pCopy, alpah, beta)); 
    		//	pCopy.reverseCoup(c);

    			if (alpah>=beta){
    				return beta;
    			}
    		}
    	}
		return alpah; 

    }
    
    
    
    public int minMax (int current_prof, SquadroBoard p, int alpah, int beta) {
       // System.out.println("DANS MIN!");
        
  //      p.possibleMoves(this.PlayerMax).size();
        
    	if((current_prof == 0) || ( p.possibleMoves(this.PlayerMax).size()==0)) {
            //System.out.println("DANS IF MIN!");

    		if (this.PlayerMin.equals("HORIZONTAL")) {
        		//return game.getValue(this.PlayerMin);
        		return game.getValue(this.PlayerMin, p);
    		}else {
        		//return game.getValue(this.PlayerMax);
    			return game.getValue(this.PlayerMax, p);
    		}
    	}else {
            //System.out.println("DANS IF MIN!");

    		for (String c : p.possibleMoves(this.PlayerMin)) {
    			SquadroBoard pCopy = p.copy(); 
        	//	pileCoup.add(c);
    			pCopy.play(c, this.PlayerMin);
    			
    			beta = Math.min(beta, maxMin(current_prof-1, pCopy,alpah, beta)); 
    			//pCopy.reverseCoup(c);
    			
    			
    			if (alpah>=beta){
    				return alpah;
    			}
    		}
    	}
		return beta; 

    	
    }
    


	public ArrayList<String> getPileCoups() {
		return pileCoups;
	}

	public void setPileCoups(ArrayList<String> pileCoups) {
		this.pileCoups = pileCoups;
	}



    
	    


	
	
	

}
