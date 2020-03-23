package iia.simpleGame.squadro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import iia.simpleGame.base.Player;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
    
    
    public String p1 ;
    public String p2 ; 
    
    public String currentPlayer = "test"; 
    
    
    
    public SquadroBoard() { //Creation du plateau initial 
    	
    	this.board = new char[TAILLE][TAILLE]; 
    	
    	//Remplissage du board et des listes de piece des joueurs 
    	for(int i=0; i < TAILLE; i++) { 
    		
    		for (int j=0; j < TAILLE ; j++) {
    			
    			if(j==0 && ( (i != 0 ) && (i !=TAILLE-1) ) ) { 
    				board[i][j] = DROITE ; 
    				
    				PieceSquadro p1 = new PieceSquadro(0,i,valDenInitJ1(i),'A'); 
    	    		listJ1.add(p1);

    			}else if( (i == TAILLE-1) && ((j != 0) && (j != TAILLE -1)) ) {
    				board[i][j] = HAUT ; 
    				
    				PieceSquadro p2 = new PieceSquadro(j,TAILLE-1,valDenInitJ2(j),'A'); 
    	    		listJ2.add(p2); 
    				
    			}else {
    				board[i][j] = VIDE; 
    			}
    		}
    	}
	
    }
    
    
    public int valDenInitJ1(int i) {
		int valDepl = 0; 

		switch(i) {
		case 1 : valDepl = 1; break; 
		case 2 : valDepl = 3; break;
		case 3 : valDepl = 2 ; break; 
		case 4 : valDepl = 3; break; 
		case 5 : valDepl = 1 ; break; 
		}
	
    	return valDepl;
    }
	    
    
    public int valDenInitJ2(int i) {
		int valDepl = 0; 

		switch(i) {
		case 1 : valDepl = 3; break; 
		case 2 : valDepl = 1; break;
		case 3 : valDepl = 2 ; break; 
		case 4 : valDepl = 1; break; 
		case 5 : valDepl = 3 ; break; 
		}
	
    	return valDepl;
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
		
		try {
			
			BufferedReader in = new BufferedReader(new FileReader(fileName+".txt"));
			String line; 
			
			int cptLine = 0; 
			
			while((line = in.readLine()) != null) {
				
				
				if(line.charAt(0) !='%') {
					
					if(line.charAt(0) =='0') {
						
						for(int i=0 ; i<TAILLE; i++) {
							
							//System.out.println(cptLine);
							//System.out.println(line.charAt(3+i));
							board[cptLine][i] = line.charAt(3 + i); 
							
						}
						cptLine ++; 
						
					}	
				}
				
				
				
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	@Override
	public void saveToFile(String fileName) {
		System.out.println("New file de sauvegarde");
		
		File fichier = new File(fileName + ".txt");
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fichier));
			out.write("%"+fileName);
			out.println();
			
			out.write("%  ABCDEFG");
			out.println(); 
			
			for(int i=0 ; i<TAILLE ; i++) {
				
				out.write("0"+(i+1)+" ");
				
				for(int j=0 ; j < TAILLE ; j++) {
					
					out.write(board[i][j]);
					
				}
				out.write(" 0"+(i+1));
				out.println();
			}
			
			out.write("%  ABCDEFG");
			out.println();
			
			//Undiquer le joueur dont c'est le tour : 
			out.write(currentPlayer);
			
			out.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	@Override
	public boolean isValidMove(String move, String player) {
		// TODO Auto-generated method stub
		return false;
	}

	
	//Retourne la liste des mouvement possible pour un joueur
	@Override
	public String[] possibleMoves(String player) {
		
		String possibleMoves[] = {"", "", "", "", ""};
		String possibleMove ="";
		
		if(player == "horizontal") {
			
			for (int i = 0; i < listJ1.size(); i++) {
				
				PieceSquadro pieceCourrante =listJ1.get(i);
				int deplacementCourant = pieceCourrante.getValeurDeplacement();
				int valDeplacement = 0;

				
			
			        
			        
					//Test si la pi�ce est � l'all�
					if(pieceCourrante.getX() < 6 && pieceCourrante.getStatut() =='A') {

						//Tant que la pi�ce doit avancer
						while(deplacementCourant > 0) {

								//Elle avance d'une case
								deplacementCourant --;
								valDeplacement ++;
								
								int colonneCourante = pieceCourrante. getX() + valDeplacement;
								char contenueCaseSuivane = this.board[ pieceCourrante.getY()][colonneCourante];


								//Si cette case contient un .
								if (contenueCaseSuivane == '.') {

									//Si la pi�ce est sortie du plateau 
									if (colonneCourante == 6) {

										//Elle arr�te d'avancer
										deplacementCourant = -1;
									}
									
								//Si la case contient une pi�ce adverse	
								}else if(contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') {
									
									//Elle avance d'une case en plus 
									valDeplacement ++;
									
									//La pi�ce avance d'une case en plus tant qu'elle saute par dessus une pi�ce adverce
									 do{
										 	//Si la pi�ce est sortie du plateau
											if (colonneCourante == 6) {
												
												deplacementCourant = -1;
												
											}
											
									 }while((contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') && deplacementCourant>=0);
										
									//La pi�ce arr�te son d�placement
									deplacementCourant = -1;

								}
							
							
							
						}
					
						
					}else if( pieceCourrante. getX() > 0 && pieceCourrante.getStatut() =='R') {
						
						


						//Tant que la pi�ce doit avancer
						while(deplacementCourant > 0) {
							
								//Elle avance d'une case
								deplacementCourant --;
								valDeplacement ++;

								int colonneCourante = pieceCourrante. getX() - valDeplacement;

								char contenueCaseSuivane = this.board[pieceCourrante.getY()][colonneCourante];
								
						
								if (contenueCaseSuivane == '.') {

									//Si la pi�ce est sortie du plateau 
									if (colonneCourante == 0) {

										//Elle arr�te d'avancer
										deplacementCourant = -1;
									}
									
								//Si la case contient une pi�ce adverse	
								}else if(contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') {
									
									//Elle avance d'une case en plus 
									valDeplacement ++;
									
									//La pi�ce avance d'une case en plus tant qu'elle saute par dessus une pi�ce adverce
									 do{
										 	//Si la pi�ce est sortie du plateau
											if (colonneCourante == 0) {
												
												deplacementCourant = -1;
												
											}
											
									 }while((contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') && deplacementCourant>=0);
										
									//La pi�ce arr�te son d�placement
									deplacementCourant = -1;

								}
							
							
							
						}
						
		
						
						
					}
					
					
					int tab1[] = {pieceCourrante.getX(), pieceCourrante.getY()};
					int tab2[] = {pieceCourrante.getX() + valDeplacement, pieceCourrante.getY()};

					possibleMove = convertIntToString(tab1) +"-"+ convertIntToString(tab2);
					possibleMoves[i] = possibleMove;
					
					
					
				
				
			}
			
			
		}else {


			
			
			
			for (int i = 0; i < listJ2.size(); i++) {

				PieceSquadro pieceCourrante =listJ2.get(i);
				int deplacementCourant = pieceCourrante.getValeurDeplacement();
				int valDeplacement = 0;


					//Test si la pi�ce est � l'all�
					if(pieceCourrante. getY() < 6 && pieceCourrante.getStatut() =='R') {

						//Tant que la pi�ce doit avancer
						while(deplacementCourant > 0) {
							
								//Elle avance d'une case
								deplacementCourant --;
								valDeplacement ++;
								
								int ligneCourante = pieceCourrante. getY() + valDeplacement;
								char contenueCaseSuivane = this.board[ligneCourante][pieceCourrante.getX()];
								
								//Si cette case contient un .
								if (contenueCaseSuivane == '.') {
									
									//Si la pi�ce est sortie du plateau 
									if (ligneCourante == 6) {
										
										//Elle arr�te d'avancer
										deplacementCourant = -1;
									}
									
								//Si la case contient une pi�ce adverse	
								}else if(contenueCaseSuivane == '>' || contenueCaseSuivane == '<') {
									
									//Elle avance d'une case en plus 
									valDeplacement ++;
									
									//La pi�ce avance d'une case en plus tant qu'elle saute par dessus une pi�ce adverce
									 do{
										 	//Si la pi�ce est sortie du plateau
											if (ligneCourante == 6) {
												
												deplacementCourant = -1;
												
											}
											
									 }while((contenueCaseSuivane == '>' || contenueCaseSuivane == '<') && deplacementCourant>=0);
										
									//La pi�ce arr�te son d�placement
									deplacementCourant = -1;

								}
							
							
							
						}
					
						
					}else if( pieceCourrante. getY() > 0 && pieceCourrante.getStatut() =='A') {

						

				        System.out.println(" WAGGG " + deplacementCourant);

						//Tant que la pi�ce doit avancer
						while(deplacementCourant > 0) {

								//Elle avance d'une case
								deplacementCourant --;
								valDeplacement --;
								
								int ligneCourante = pieceCourrante. getY() + valDeplacement;
								char contenueCaseSuivane = this.board[ligneCourante][pieceCourrante.getX()];
								
								//Si cette case contient un .
								if (contenueCaseSuivane == '.') {
									
									//Si la pi�ce est sortie du plateau 
									if (ligneCourante == 0) {
										
										//Elle arr�te d'avancer
										deplacementCourant = -1;
									}
									
								//Si la case contient une pi�ce adverse	
								}else if(contenueCaseSuivane == '>' || contenueCaseSuivane == '<') {
									
									//Elle avance d'une case en plus 
									valDeplacement --;
									
									//La pi�ce avance d'une case en plus tant qu'elle saute par dessus une pi�ce adverce
									 do{
										 	//Si la pi�ce est sortie du plateau
											if (ligneCourante == 0) {
												
												deplacementCourant = -1;
												
											}
											
									 }while((contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') && deplacementCourant>=0);
										
									//La pi�ce arr�te son d�placement
									deplacementCourant = -1;

								}
							
							
							
						}
						
		
						
						
					}
					

					int tab1[] = {pieceCourrante.getX(), pieceCourrante.getY()};
					int tab2[] = {pieceCourrante.getX(), pieceCourrante.getY() + valDeplacement};

					possibleMove = convertIntToString(tab1) +"-"+ convertIntToString(tab2);
					possibleMoves[i] = possibleMove;
				
			}
					
					
			
		}
			
		
		
		return possibleMoves;
	}
	
	
	//Fait la correspondance entre les coordonn�es string type "A" en coordonn�e enti�re type 0 exploitable par les fonctions
	public int[] convertStringToInt(String coordonee) {
		
		int coordonnneeInt[] = {0,0};
		
		switch(coordonee) {
			
			//Si la coordonn�e est une lettre
			case "A" : coordonnneeInt[0]=0; break; 
			case "B" : coordonnneeInt[0]=1; break;
			case "C" : coordonnneeInt[0]=2; break; 
			case "D" : coordonnneeInt[0]=3; break; 
			case "E" : coordonnneeInt[0]=4; break;
			case "F" : coordonnneeInt[0]=5; break; 
			case "G" : coordonnneeInt[0]=6; break; 
			default : coordonnneeInt[1]=Integer.parseInt(coordonee);break;
	}
		
	return 	coordonnneeInt;
		
	}
	
	//Fait la correspondance entre les coordonn�e enti�re type [0][4] et les coordonn�es string type "A4-C4"
	public String convertIntToString(int coordonee[]) {
		String coordonnneeString = "";
		
		switch(coordonee[0]) {
			
			//Si la coordonn�e est une lettre
			case 0 : coordonnneeString="A"; break; 
			case 1 : coordonnneeString="B"; break;
			case 2 : coordonnneeString="C"; break; 
			case 3 : coordonnneeString="D"; break; 
			case 4 : coordonnneeString="E"; break;
			case 5 : coordonnneeString="F"; break; 
			case 6 : coordonnneeString="G"; break; 
	}
		
		coordonnneeString= coordonnneeString + Integer.toString(coordonee[1]);
		
	return 	coordonnneeString;
	
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
        
        String tab[]= b.possibleMoves("horizontal");
        
        System.out.println(Arrays.toString(tab));
        
        System.out.println("2 : ");
       
        String tab2[]= b.possibleMoves("verticale");
        System.out.println(Arrays.toString(tab2));
        
        
        System.out.println("Test fichier");
        
        b.saveToFile("TEST1"); 
        b.setFromFile("TEST1");
        
        b.printBoard();
        b.setFromFile("TEST1");
        b.printBoard(); 
        
        
        
        System.out.println(" ======= GAME OVER =======");

    }

}

