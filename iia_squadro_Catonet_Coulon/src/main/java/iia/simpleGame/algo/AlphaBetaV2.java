package iia.simpleGame.algo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import iia.simpleGame.base.IGame;
import iia.simpleGame.base.Player;
import iia.simpleGame.squadro.ASquadroGame;
import iia.simpleGame.squadro.SquadroBoard;
import iia.simpleGame.squadro.SquadroGameH;
import iia.simpleGame.squadro.SquadroGameV;



public class AlphaBetaV2 implements IAlgo {
	
	
    /** La profondeur de recherche par d�faut
     */
    private final static int PROFMAXDEFAUT = 0;

   
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
   private Map<String, Integer[]> hashJ1 =  new HashMap<String, Integer[]>() ;
   private Map<String, Integer[]> hashJ2 =  new HashMap<String, Integer[]>() ;

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
    
    private String idHashTable;

    private int timeMax = 1000;
    private ArrayList<String> pileCoup = new ArrayList<String>();


  // -------------------------------------------
  // Constructeurs
  // -------------------------------------------
    public AlphaBetaV2(IGame game,String Role_PlayerMax, String Role_PlayerMin) {
        this(game,Role_PlayerMax,Role_PlayerMin,PROFMAXDEFAUT);
    }

    public AlphaBetaV2(IGame game,String Role_PlayerMax, String Role_PlayerMin, int profMaxi) {

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

	   System.out.println("PLAYER BESTMOVE  : " +this.PlayerMax);
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
    	
    	long startTime = System.currentTimeMillis();
        long elapsedTime = (new Date()).getTime() - startTime;

    	int alpha = (int)Float.NEGATIVE_INFINITY;
    	int beta = (int)Float.POSITIVE_INFINITY;
    	int Max  = (int)Float.NEGATIVE_INFINITY;
    	String MeilleurCoup = null; 

    	SquadroBoard pCopy0 = p.copy(); 
    	

    	while((((int)(timeMax/1)) >= (elapsedTime - startTime) ) && (current_prof <= 40)) {
            current_prof++;

	    	for (String next_coup : pCopy0.possibleMoves(this.PlayerMax)) {
		 	 //   System.out.println("cordonneeInt2 BETA : " + this.PlayerMax + " : " + pCopy0.possibleMoves(this.PlayerMax));

	    		//pileCoup.add(next_coup);
	    		SquadroBoard pCopy = pCopy0.copy(); 
	    		pCopy.play(next_coup, this.PlayerMax);
	    		
	       		int newVal = minMax(current_prof-1, pCopy, alpha, beta); 
	    		
	        	if(newVal > Max) {
	       			MeilleurCoup = next_coup; 
	       			Max = newVal; 
	        	}
	      
	       	}
	    	
	       elapsedTime = (new Date()).getTime();
           //System.out.println("\n\n FINNNNNN FORRRRRRR current_prof \n\n " + current_prof );

    	}
    	
    	System.out.println(MeilleurCoup);
        System.out.println("Profondeur : " + current_prof);
        System.out.println("Dure coup en ms : " + (elapsedTime - startTime));

    	return MeilleurCoup; 
    }
    
    
    
    
    
    public int maxMin (int current_prof, SquadroBoard p, int alpah, int beta) {
       //System.out.println("maxMin!");
    	boolean recalcule = false;

    	if((current_prof == 0) ||  (p.possibleMoves(this.PlayerMax).size()==0)) {
    		if (this.PlayerMax.equals("HORIZONTAL")) {
        		return game.getValue(this.PlayerMax, p);
    		}else {
        		return game.getValue(this.PlayerMin , p);
    		}
    	}else {

    		idHashTable = this.PlayerMax+p.hashBoard();

            if (hashJ1.containsKey(idHashTable) ) {
                //System.out.println("LINEAIRE!MAX");

            	Integer[] tab = hashJ1.get(idHashTable);
                Integer profondeur=0;
                
                profondeur = tab[0];
               
                if( profondeur >= current_prof) {
                	
                	Integer[] t1 =  {tab[2], tab[3]};
                    Integer[] t2 = {tab[4], tab[5]}; 
                    
    				String meuilleurCoup = p.convertIntegerToString(t1) + "-" + p.convertIntegerToString(t2);
        			SquadroBoard pCopy = p.copy(); 
        			
        			//System.out.println("MaxMin play coup  de hashMap : " + meuilleurCoup);
        			pCopy.play(meuilleurCoup, this.PlayerMax);
        			
        			beta = Math.min(beta, maxMin(current_prof-1, pCopy,alpah, beta)); 
        			
                }else {
                	recalcule = true;
                }
                
            }else{
            	recalcule = true;
            }
            
            
            if(recalcule) {
	                //System.out.println("REACLCULE TABLE HASH!");
	
			    	String meuilleurCoup = "";
			    	
		    	
			    	int maxAlpha = (int)Float.NEGATIVE_INFINITY;;
	            
			    	for (String c :p.possibleMoves(this.PlayerMax)) {
		    	    	SquadroBoard pCopy = p.copy(); 
		        	//	pileCoup.add(c);
		    	    	pCopy.play(c, this.PlayerMax);
		    	        	    	
		    			alpah = Math.max(alpah, minMax(current_prof-1, pCopy, alpah, beta)); 
		
		    			
		    			if (maxAlpha <= alpah) {
		    				meuilleurCoup = c;
		    				maxAlpha=alpah;
		    			}
		
		    		//	pCopy.reverseCoup(c);
		
		    			if (alpah>=beta){
		    				return beta;
		    			}
		    							
			    	}
			    	
		 	    //System.out.println("cordonneeInt2 BETA : " + this.PlayerMax + " : " + p.possibleMoves(this.PlayerMax));
	    		String[] char1 = {meuilleurCoup.substring(0, 1), meuilleurCoup.substring(1, 2)};	  
	    		String[] char2 = {meuilleurCoup.substring(3, 4), meuilleurCoup.substring(4, 5) };	    		
	
	
	        	int[] cordonneeInt1 = p.convertStringToInt(char1);
	    		int[] cordonneeInt2 = p.convertStringToInt(char2);
	
	    		Integer[] tabhash = {current_prof,beta, cordonneeInt1[0],cordonneeInt1[1], cordonneeInt2[0],cordonneeInt2[1]};
	    		hashJ1.put(this.PlayerMax+p.hashBoard(), tabhash ) ;
	    		
	    	
            }
    	}
    	
		
		return alpah; 

    }
    
    
    
    public int minMax (int current_prof, SquadroBoard p, int alpah, int beta) {
       // System.out.println("minMax");
  //      p.possibleMoves(this.PlayerMax).size();
    	boolean recalcule = false;

    	if((current_prof == 0) || ( p.possibleMoves(this.PlayerMin).size()==0)) {
    		

    		if (this.PlayerMin.equals("HORIZONTAL")) {
        		return game.getValue(this.PlayerMin, p);
    		}else {
        		return game.getValue(this.PlayerMax, p);
    		}
    		
    		
    	}else {
            
    		idHashTable = this.PlayerMin+p.hashBoard();
            if (hashJ1.containsKey(idHashTable) ) {
              //  System.out.println("LINEAIRE!");

            	Integer[] tab = hashJ1.get(idHashTable);
                Integer profondeur=0;
                
                profondeur = tab[0];
               
                if( profondeur >= current_prof) {
                	
                	Integer[] t1 =  {tab[2], tab[3]};
                    Integer[] t2 = {tab[4], tab[5]}; 
                    
    				String meuilleurCoup = p.convertIntegerToString(t1) + "-" + p.convertIntegerToString(t2);
        			SquadroBoard pCopy = p.copy(); 
        			//System.out.println("MinMax play coup  de hashMap : " + meuilleurCoup);
        			pCopy.play(meuilleurCoup, this.PlayerMin);
        			beta = Math.min(beta, maxMin(current_prof-1, pCopy,alpah, beta)); 
        			
                }else {
                	recalcule = true;
                }
                
            }else{
            	recalcule = true;
            }
            
            
            
            if(recalcule) {
                //System.out.println("REACLCULE TABLE HASH!");

		    	String meuilleurCoup = "";
		    	
	    	
		    	int minBeta = (int)Float.POSITIVE_INFINITY;;

	    		for (String c : p.possibleMoves(this.PlayerMin)) {
	    			SquadroBoard pCopy = p.copy(); 
	    			
	        	//	pileCoup.add(c);
	
	
	    			pCopy.play(c, this.PlayerMin);
	    			beta = Math.min(beta, maxMin(current_prof-1, pCopy,alpah, beta)); 
	    			//pCopy.reverseCoup(c);
	    			
	    			
	    			
	    			if (minBeta >= beta) {
	    				meuilleurCoup = c;
	    				minBeta=beta;
	    			}
	    			

	    			
	    			if (alpah>=beta){ 
	    				return alpah;
	    			}
	    		}
	    		
	    		if(meuilleurCoup.equals("")) {
	    		//	   System.out.println("this.PlayerMin : " + this.PlayerMin);
	    		//	   System.out.println("cordonneeInt2 string to int : " + meuilleurCoup);
	    		//	   System.out.println("cordonneeInt2 BETA : " + p.possibleMoves(this.PlayerMin));

	    		}
	    		
 			   //System.out.println("cordonneeInt2 BETA : " + this.PlayerMin + " : " + p.possibleMoves(this.PlayerMin));

	    		String[] char1 = {meuilleurCoup.substring(0, 1), meuilleurCoup.substring(1, 2)};	  
	    		String[] char2 = {meuilleurCoup.substring(3, 4), meuilleurCoup.substring(4, 5) };	    		


	        	int[] cordonneeInt1 = p.convertStringToInt(char1);
	    		int[] cordonneeInt2 = p.convertStringToInt(char2);
	    	   // System.out.println("cordonneeInt2 string to int : " + meuilleurCoup);

	    		Integer[] tabhash = {current_prof,beta, cordonneeInt1[0],cordonneeInt1[1], cordonneeInt2[0],cordonneeInt2[1]};
	    		hashJ1.put(this.PlayerMin+p.hashBoard(), tabhash ) ;
	    		

	    		
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
