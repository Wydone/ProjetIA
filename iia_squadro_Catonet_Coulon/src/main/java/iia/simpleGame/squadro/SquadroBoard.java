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
    
  
    public String nextPlayer = ""; 
    
    
    /*
     * Constructeur de classe
     */
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
    
    /*
     * Coper le plateau en parametre
     * @return SquadroBoard
     */
	public SquadroBoard copy() {
		
		return new SquadroBoard(this.board, this.copy(this.listJ1), this.copy(this.listJ2));
	}
	
	/*
	 * Copier la liste des pieces du plateau
	 * @return ArrayList<PieceSquadro>
	 * @param ArrayList<PieceSquadro>
	 */
	public ArrayList<PieceSquadro> copy(ArrayList<PieceSquadro> listeDepart) {
		ArrayList<PieceSquadro> newList = new ArrayList<PieceSquadro>();

		for(int i = 0; i < listeDepart.size(); i++) {
			PieceSquadro currentPiece = listeDepart.get(i);
			newList.add(currentPiece.copy(currentPiece.getX(), currentPiece.getY(), currentPiece.getValeurDeplacement(), currentPiece.getStatut()));
		}
		return newList;
	}
	
	/*
	 * Contructeur 2 de classe à partir d'un plateau existant
	 */
	public SquadroBoard(char depuis[][], ArrayList<PieceSquadro> listJ1, ArrayList<PieceSquadro> listJ2) {
		
		board = new char[TAILLE][TAILLE]; 

		
		ArrayList<PieceSquadro> l1 = listJ1;
		ArrayList<PieceSquadro> l2 = listJ2;

		this.listJ1 = l1;
		this.listJ2 = l2;
		
		for(int i=0 ; i < TAILLE ; i++) {
			for (int j=0; j < TAILLE ; j++) {
				board[i][j] = depuis[i][j]; 
			}
		}
	}
	    
	
	/*
	 * Connaitre la valeur de déplacement des cases Joueur Horizontal
	 */
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
	    
    /*
	 * Connaitre la valeur de déplacement des cases Joueur Vertical
	 */
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
			
			//Undiquer le joueur dont se sera le tour : 
			out.write(nextPlayer);
			
			out.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	//Fait la correspondance entre les coordonnées string type "A" en coordonnée entière type 0 exploitable par les fonctions
		public int[] convertStringToInt(String coordonee[]) {
			
			int coordonnneeInt[] = {0,0};
			
			switch(coordonee[0]) {
				
				//Si la coordonnée est une lettre
				case "A" : coordonnneeInt[0]=0; break; 
				case "B" : coordonnneeInt[0]=1; break;
				case "C" : coordonnneeInt[0]=2; break; 
				case "D" : coordonnneeInt[0]=3; break; 
				case "E" : coordonnneeInt[0]=4; break;
				case "F" : coordonnneeInt[0]=5; break; 
				case "G" : coordonnneeInt[0]=6; break; 
				default:coordonnneeInt[0]=-1; break;
		}
		
			coordonnneeInt[1]= Integer.parseInt(coordonee[1]) - 1;
		return 	coordonnneeInt;
			
		}
		
		//Fait la correspondance entre les coordonnée entière type [0][4] et les coordonnées string type "A4-C4"
		public String convertIntToString(int coordonee[]) {
			String coordonnneeString = "";
			
			switch(coordonee[0]) {
				
				//Si la coordonnée est une lettre
				case 0 : coordonnneeString="A"; break; 
				case 1 : coordonnneeString="B"; break;
				case 2 : coordonnneeString="C"; break; 
				case 3 : coordonnneeString="D"; break; 
				case 4 : coordonnneeString="E"; break;
				case 5 : coordonnneeString="F"; break; 
				case 6 : coordonnneeString="G"; break; 
				default:coordonnneeString="ERROR"; break;

		}
			
			coordonnneeString= coordonnneeString + Integer.toString(coordonee[1]+1);
			
		return 	coordonnneeString;
		
		}


	@Override
	public boolean isValidMove(String move, String player) {
		
		boolean isValidMove = false;
		
		System.out.println("TEST validité du move : " + move);

		if(move != "None") {
			
			String positionCourante[] = {Character.toString(move.charAt(0)),Character.toString(move.charAt(1))};
			String newPosition[] = {Character.toString(move.charAt(3)),Character.toString(move.charAt(4))};
			ArrayList<PieceSquadro> listPieceCourante = new ArrayList<PieceSquadro>() ;
			
			int tabInt_posCourante[] = convertStringToInt(positionCourante); 
			int tabInt_newPos[] = convertStringToInt(newPosition); 
			int i = 0;		
			
			boolean nonTrouve = true;
			
			if(player.equals("HORIZONTAL")) {
				listPieceCourante = this.listJ1;
			
			}else {
				listPieceCourante = this.listJ2;
			}
	
	
			//On parcourt toute la liste de pièce pour s'assurer que celle-ci existe bien
			while(nonTrouve && i<5) {
				
				//retrouve la pièce dans la liste
				if((listPieceCourante.get(i).getX() == tabInt_posCourante[0]) && (listPieceCourante.get(i).getY() == tabInt_posCourante[1])) {
					
					PieceSquadro pieceCourante = listPieceCourante.get(i);
					int deplacementCourant = pieceCourante.getValeurDeplacement();
					int valDeplacement = 0;
					int colonneCourante = pieceCourante. getX() + valDeplacement;
					int ligneCourante = pieceCourante. getY() + valDeplacement;
	
					if(player.equals("HORIZONTAL")) {
							
							//Verifie si la piece bouge sur la meme ligne
						if(tabInt_posCourante[1] == tabInt_newPos[1]) {
								
								//On s'assure que la position final d'une piece est bien sur le plateau
							if (tabInt_newPos[0] >= 0 && tabInt_newPos[0] <= 6 && tabInt_newPos[1] >=0 && tabInt_newPos[1]<=6) {
										
									//Test si la pièce est à l'allé
								if(pieceCourante.getStatut() =='A') {
	
										//Tant que la pièce doit avancer
									while(deplacementCourant > 0) {
										//Elle avance d'une case
										deplacementCourant --;
										valDeplacement ++;
												
										colonneCourante = pieceCourante. getX() + valDeplacement;
										char contenueCaseSuivane = this.board[ pieceCourante.getY()][colonneCourante];
	
										//Si cette case contient un .
										if (contenueCaseSuivane == '.') {
	
											//Si la pièce est arrivé au bord  du plateau
											if (colonneCourante == 6) {
	
												//Elle arrète d'avancer
												deplacementCourant = -1;
											}
													
											//Si la case contient une pièce adverse	
											}else if(contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') {
													
												//Elle avance d'une case en plus 
												valDeplacement ++;
													
												//La pièce avance d'une case en plus tant qu'elle saute par dessus une pièce adverce
												 do{
													 	//Si la pièce est sortie du plateau
														if (colonneCourante == 6 ) {
															
															deplacementCourant = -1;
	
														}
															
												 }while((contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') && deplacementCourant>=0);
														
												 //La pièce arrète son déplacement
												 deplacementCourant = -1;
	
											}
									
										
										if (colonneCourante == tabInt_newPos[0]) {
											isValidMove = true;
										}
										
									}
										
								}else if( pieceCourante. getX() > 0 && pieceCourante.getStatut() =='R') {
										
									//Tant que la pièce doit avancer
									while(deplacementCourant > 0) {
											
										//Elle avance d'une case
										deplacementCourant --;
										valDeplacement ++;
	
										colonneCourante = pieceCourante. getX() - valDeplacement;
										char contenueCaseSuivane = this.board[pieceCourante.getY()][colonneCourante];
												
										
										if (contenueCaseSuivane == '.') {
	
											//Si la pièce est arrivé au bord  du plateau
											if (colonneCourante == 0) {
	
												//Elle arrète d'avancer
												deplacementCourant = -1;
	
											}
													
											//Si la case contient une pièce adverse	
											}else if(contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') {
													
												//Elle avance d'une case en plus 
												valDeplacement ++;
													
												//La pièce avance d'une case en plus tant qu'elle saute par dessus une pièce adverce
												do{
													//Si la pièce est arrivé au bord  du plateau
													if (colonneCourante == 0) {
																
														deplacementCourant = -1;
													}
															
												}while((contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') && deplacementCourant>=0);
														
												//La pièce arrète son déplacement
												deplacementCourant = -1;
													
											}
										}
										
										if (colonneCourante == tabInt_newPos[0]) {
											isValidMove = true;
										}
											
									}
								
								}
								
							}
							
						}else if(player.equals("VERTICAL")) {
	
							//Verifie si la piece bouge sur la meme colonne
							if(tabInt_posCourante[0] == tabInt_newPos[0]) {
								
								//On s'assure que la position final d'une piece est bien sur le plateau
								if (tabInt_newPos[0] >= 0 && tabInt_newPos[0] <= 6 && tabInt_newPos[1] >=0 && tabInt_newPos[1]<=6) {
									
									//Test si la pièce est à l'allé
									if(pieceCourante. getY() < 6 && pieceCourante.getStatut() =='R') {
	
										//Tant que la pièce doit avancer
										while(deplacementCourant > 0) {
											
											//Elle avance d'une case
											deplacementCourant --;
											valDeplacement ++;
												
											ligneCourante = pieceCourante. getY() + valDeplacement;
											char contenueCaseSuivane = this.board[ligneCourante][pieceCourante.getX()];
												
											//Si cette case contient un .
											if (contenueCaseSuivane == '.') {
													
												//Si la pièce est sortie du plateau 
												if (ligneCourante == 6) {
																											
													//Elle arrète d'avancer
													deplacementCourant = -1;
												}
													
											//Si la case contient une pièce adverse	
											}else if(contenueCaseSuivane == '>' || contenueCaseSuivane == '<') {
													
												//Elle avance d'une case en plus 
												valDeplacement ++;
													
												//La pièce avance d'une case en plus tant qu'elle saute par dessus une pièce adverce
												do{
													//Si la pièce est sortie du plateau
													if (ligneCourante == 6) {
																
														deplacementCourant = -1;
	
													}
															
												}while((contenueCaseSuivane == '>' || contenueCaseSuivane == '<') && deplacementCourant>=0);
														
												//La pièce arrète son déplacement
												deplacementCourant = -1;
	
											}
											
										}
									
										if (ligneCourante == tabInt_newPos[1]) {
											isValidMove = true;
										}
										
									}else if( pieceCourante. getY() > 0 && pieceCourante.getStatut() =='A') {
	
										//Tant que la pièce doit avancer
										while(deplacementCourant > 0) {
	
											//Elle avance d'une case
											deplacementCourant --;
											valDeplacement --;
												
											ligneCourante = pieceCourante. getY() + valDeplacement;
											char contenueCaseSuivane = this.board[ligneCourante][pieceCourante.getX()];
												
											//Si cette case contient un .
											if (contenueCaseSuivane == '.') {
													
												//Si la pièce est sortie du plateau 
												if (ligneCourante == 0) {
														
													//Elle arrète d'avancer
													deplacementCourant = -1;
												}
													
											//Si la case contient une pièce adverse	
											}else if(contenueCaseSuivane == '>' || contenueCaseSuivane == '<') {
													
												//Elle avance d'une case en plus 
												valDeplacement --;
													
												//La pièce avance d'une case en plus tant qu'elle saute par dessus une pièce adverce
												do{
												 	//Si la pièce est sortie du plateau
													if (ligneCourante == 0) {
																
														deplacementCourant = -1;				
													}
															
												}while((contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') && deplacementCourant>=0);
														
												//La pièce arrète son déplacement
												deplacementCourant = -1;
	
											}
											
										}
										
										
										if (ligneCourante == tabInt_newPos[1]) {
											isValidMove = true;
										}
						
									}
												
								}
								
							}
							
						}
						
						nonTrouve = false;
				
					}
				
					i++;
	
				}

			}else {
				isValidMove = false;
			}
		
		return isValidMove;
		
	}

	
	//Retourne la liste des mouvement possible pour un joueur
	@Override
	public ArrayList<String> possibleMoves(String player) {
		//System.out.println("dans possibleMoves. Player : " + player);

		ArrayList<String> possibleMoves = new ArrayList<String>();
		String possibleMove ="";
		
		if(player.equals("HORIZONTAL")) {
			
			for (int i = 0; i < listJ1.size(); i++) {
				
				PieceSquadro pieceCourrante =listJ1.get(i);
				int deplacementCourant = pieceCourrante.getValeurDeplacement();
				//System.out.println("DEPLACEMENT INIT : " + deplacementCourant);
				int colonneCourante = pieceCourrante.getX();

				int valDeplacement = 0;
 
				//Test si la pi�ce est � l'all�e
				if(pieceCourrante.getX() < 6 && pieceCourrante.getStatut() =='A') {
					//System.out.println("Deplacment COURANT : " + deplacementCourant);
					//Tant que la pi�ce doit avancer
					while(deplacementCourant > 0) {

						//Elle avance d'une case
						deplacementCourant --;
						//System.out.println("TEST__________________________");
						valDeplacement ++;
								
						colonneCourante = pieceCourrante.getX() + valDeplacement;
						
						char contenueCourante = this.board[ pieceCourrante.getY()][colonneCourante];
						
						//Si cette case contient un .
						if (contenueCourante == '.') {

							//Si la pi�ce est sortie du plateau 
							if (colonneCourante == 6) {

								//Elle arr�te d'avancer
								deplacementCourant = -1;
							}
									
							//Si la case contient une pi�ce adverse	
						//System.out.println("CONTENU DE LA CASE : "+contenueCourante);
						}else if(contenueCourante =='^' || contenueCourante == 'v') {
							
							valDeplacement ++;//----------------------------------------------------------------------
							
							//La pi�ce avance d'une case en plus tant qu'elle saute par dessus une pi�ce adverce
							do{

								//Si la pi�ce est sortie du plateau
								if (colonneCourante == 6) {
												
									deplacementCourant = -1;
												
								}else {
								
									colonneCourante = pieceCourrante.getX() + valDeplacement;
									contenueCourante = this.board[pieceCourrante.getY()][colonneCourante];
									valDeplacement ++;
								}
											
							}while((contenueCourante == '^' || contenueCourante == 'v') && deplacementCourant>=0);

							//La pi�ce arr�te son d�placement
							deplacementCourant = -1;
						}
								
					}
							
				}else if( pieceCourrante. getX() > 0 && pieceCourrante.getStatut() =='R') {
					
					//Tant que la pi�ce doit avancer
					while(deplacementCourant > 0) {
						
						//Elle avance d'une case
						deplacementCourant --;
						valDeplacement --;
						
						colonneCourante = pieceCourrante.getX() + valDeplacement;
						char contenueCaseSuivane = this.board[pieceCourrante.getY()][colonneCourante];
															
						if (contenueCaseSuivane == '.') {

							//Si la pi�ce est sortie du plateau 
							if (colonneCourante == 0) {

								//Elle arr�te d'avancer
								deplacementCourant = -1;
							}
									
						//Si la case contient une pi�ce adverse	
						}else if(contenueCaseSuivane == '^' || contenueCaseSuivane == 'v') {
									
							//Elle avance d'une case en plus 
							valDeplacement --; 
				
							//La pi�ce avance d'une case en plus tant qu'elle saute par dessus une pi�ce adverce
							do{
								
								//valDeplacement --; //--------------------------------------------------------ERROR - IL FAUT LE COMMENTER!!----------
								
								colonneCourante = pieceCourrante. getX() + valDeplacement;
								//Si la pi�ce est sortie du plateau
								if (colonneCourante <= 0) {
									colonneCourante = 0;	
									deplacementCourant = -1;			
								}
							
								contenueCaseSuivane = this.board[pieceCourrante.getY()][colonneCourante];
								valDeplacement --;
											
							}while((contenueCaseSuivane == '^' || contenueCaseSuivane == 'V') && deplacementCourant>=0);
										
							//La pi�ce arr�te son d�placement
							deplacementCourant = -1;

						}
							
					}
						
				}
					
				int tab1[] = {pieceCourrante.getX(), pieceCourrante.getY()};
				int tab2[] = {colonneCourante, pieceCourrante.getY()};
					
				//Si le pion n'a pas fait d'allez retour
				if(tab1[0] != tab2[0]) {
					
					possibleMove = convertIntToString(tab1) +"-"+ convertIntToString(tab2);
					possibleMoves.add(possibleMove);
				
				}
	
			}
			
			
		}else {
			
			for (int i = 0; i < listJ2.size(); i++) {

				PieceSquadro pieceCourrante =listJ2.get(i);
				int deplacementCourant = pieceCourrante.getValeurDeplacement();
				int ligneCourante = pieceCourrante. getY();

				int valDeplacement = 0;


				//Test si la pi�ce est � l'all�
				if(pieceCourrante. getY() < 6 && pieceCourrante.getStatut() =='R') {

					//Tant que la pi�ce doit avancer
					while(deplacementCourant > 0) {
							
						//Elle avance d'une case
						deplacementCourant --;
						valDeplacement ++;
								
						ligneCourante = pieceCourrante. getY() + valDeplacement;
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
											
								ligneCourante = pieceCourrante. getY() + valDeplacement;
								contenueCaseSuivane = this.board[ligneCourante][pieceCourrante.getX()];
								valDeplacement ++;
											
							}while((contenueCaseSuivane == '>' || contenueCaseSuivane == '<') && deplacementCourant>=0);
										
							//La pi�ce arr�te son d�placement
							deplacementCourant = -1;

						}
								
					}
					
						
				}else if( pieceCourrante. getY() > 0 && pieceCourrante.getStatut() =='A') {

					//Tant que la pi�ce doit avancer
					while(deplacementCourant > 0) {

						//Elle avance d'une case
						deplacementCourant --;
						valDeplacement --;
								
						ligneCourante = pieceCourrante. getY() + valDeplacement;
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
								ligneCourante = pieceCourrante. getY() + valDeplacement;
								if (ligneCourante <= 0) {
									ligneCourante =0;		
									deplacementCourant = -1;						
								}
											
								contenueCaseSuivane = this.board[ligneCourante][pieceCourrante.getX()];
								valDeplacement --;
											
							}while((contenueCaseSuivane == '>' || contenueCaseSuivane == '<') && deplacementCourant>=0);
										
							//La pi�ce arr�te son d�placement
							deplacementCourant = -1;

						}
									
					}
						
				}
					

				int tab1[] = {pieceCourrante.getX(), pieceCourrante.getY()};
				int tab2[] = {pieceCourrante.getX(), ligneCourante};
				
				if(tab1[1] != tab2[1]) {

					possibleMove = convertIntToString(tab1) +"-"+ convertIntToString(tab2);
					possibleMoves.add(possibleMove);
				
				}
				
			}		
			
		}
		
		return possibleMoves;
	}
	
	//Fait la correspondance entre les coordonn�es string type "A" en coordonn�e enti�re type 0 exploitable par les fonctions
	
	@Override
	public void play(String move, String role) {
		//this.printBoard();
		//System.out.println("Mon move : " + move + ", role : " + role);
		//System.out.println("Mes possibles move : " + this.possibleMoves(role));
		String positionCourante[] = {Character.toString(move.charAt(0)),Character.toString(move.charAt(1))};
		String newPosition[] = {Character.toString(move.charAt(3)),Character.toString(move.charAt(4))};
		ArrayList<PieceSquadro> listPieceCourante = new ArrayList<PieceSquadro>() ;
		
		int tabInt_posCourante[] = convertStringToInt(positionCourante);
		int tabInt_newPos[] = convertStringToInt(newPosition);
		int i = 0;
		char symbole = ' ';
		boolean nonTrouve = true;
		
		//System.out.println("DANS la methode PLAY de SQuadroBoard MOVE : " + move + ", ROLE : " + role);
		
		if(role.equals("HORIZONTAL")) {//if(role.equals("HORIZONTAL") ) {
			listPieceCourante = listJ1;
			//System.out.println("HORIZONTALLLLLLLLLLLLLLLLLLLLLLLLLLLL test: " + this.possibleMoves(role));

		
		}else { 
			listPieceCourante = listJ2;
			//System.out.println("VERTICALLLLLLLLLLLLLLLLLLLLLLLLLLLLLL test: " +this.possibleMoves(role));

		}

		while(nonTrouve) {
			
			//System.out.println("Le move : " + move);
		 
			//System.out.println("Position X de la piece : " + listPieceCourante.get(i).getX());
			//System.out.println("Position X du move : " + tabInt_posCourante[0]);
			if((listPieceCourante.get(i).getX() == tabInt_posCourante[0]) && (listPieceCourante.get(i).getY() == tabInt_posCourante[1])) {

				PieceSquadro pieceCourante = listPieceCourante.get(i);
				pieceCourante.setX(tabInt_newPos[0]);
				pieceCourante.setY(tabInt_newPos[1]);
				int bornInf = 0;
				int bornSup = 0;
		
				//Determine si le symbole (sur le plateau) et le status de la piece change avec la nouvelle position
				if(role.equals("HORIZONTAL")) {
					
					
					if(pieceCourante.getStatut()=='A') {
								
						if(tabInt_newPos[0]== TAILLE-1) {
							pieceCourante.setStatut('R');
							pieceCourante.setValeurDeplacement(valDenInitJ2(tabInt_newPos[1]));
							symbole = '<';
									
						}else {
							symbole = '>';
						}

					}else if(pieceCourante.getStatut()=='R') {
					
						if(tabInt_newPos[0]== 0) {
							pieceCourante.setStatut('D');
						}
						symbole = '<';		//-----------------------------------------------------------------------------------------------------------------------

					}else {
						symbole = '.';

					}
	
					//Borne utilisées pour savoir si une pièce a été mangé quand le coup a été joué
					if(tabInt_newPos[0] > tabInt_posCourante[0]) {
						//System.out.println("La borne : " + tabInt_newPos[0]);
						bornInf = tabInt_posCourante[0];
						bornSup = tabInt_newPos[0] + 1;
					
					}else {
						bornSup = tabInt_posCourante[0];
						bornInf = tabInt_newPos[0];
					}
							
					for(i = 0; i< this.listJ2.size(); i++) {
							
						if(this.listJ2.get(i).getY() == pieceCourante.getY()) {
								
							if(this.listJ2.get(i).getX() < bornSup  && this.listJ2.get(i).getX() > bornInf) {
								
								this.board[this.listJ2.get(i).getY()][this.listJ2.get(i).getX()]= '.';
									
								if(this.listJ2.get(i).getStatut() == 'A'){
								
									this.board[TAILLE-1][this.listJ2.get(i).getX()]= '^';
									this.listJ2.get(i).setY(TAILLE-1);
								
								}else{
									board[0][this.listJ2.get(i).getX()]= 'v';
									listJ2.get(i).setY(0);
								}
									
							}
								
						}
							
					}
								
				}else {
					
					if(pieceCourante.getStatut()=='A') {
						
						if(tabInt_newPos[1]== 0) {
							pieceCourante.setStatut('R');
							pieceCourante.setValeurDeplacement(valDenInitJ1(tabInt_newPos[0]));
							symbole = 'v';
						
						}else {
							symbole ='^';

						}

					}else if(pieceCourante.getStatut()=='R') {
						
						if(tabInt_newPos[1]== TAILLE-1) {
							pieceCourante.setStatut('D');
						}
						symbole = 'v';
							

					}else {
						symbole = '.';
					}
					
					
					//Borne utilisées pour savoir si une pièce a été mangé quand le coup a été joué
					if(tabInt_newPos[1] > tabInt_posCourante[1]) {
						bornInf = tabInt_posCourante[1];
						bornSup = tabInt_newPos[1] + 1; //-------------------------------------------------ICI PB ???---------------------------
						//System.out.println("MANGEE !!!!!!!!!!!");
					
					}else {
						bornSup = tabInt_posCourante[1];
						bornInf = tabInt_newPos[1];
					}
					
					
					
					for(i = 0; i< this.listJ1.size(); i++) {
						
						if(this.listJ1.get(i).getX() == pieceCourante.getX()) {
							
							if(this.listJ1.get(i).getY() < bornSup  && this.listJ1.get(i).getY() > bornInf) {
							
								this.board[this.listJ1.get(i).getY()][this.listJ1.get(i).getX()]= '.';
								
								if(this.listJ1.get(i).getStatut() == 'A'){
								
									this.board[this.listJ1.get(i).getY()][0]= '>';
									this.listJ1.get(i).setX(0);
								
								}else{
									this.board[this.listJ1.get(i).getY()][TAILLE -1]= '<';
									this.listJ1.get(i).setX(TAILLE-1);
								}
								
							}
							
						}
						
					}
				}										
				
				nonTrouve = false;
			
			}
			
			i++;

		}
		
		//System.out.println("MOVE PLAYED : "+move);

		//Met a jour le plateau
		board[tabInt_posCourante[1]][tabInt_posCourante[0]]= '.';
		board[tabInt_newPos[1]][tabInt_newPos[0]]= symbole;		
	//	this.printBoard();

	}

	@Override
	public boolean gameOver() {
		
		ArrayList<String> tab = this.possibleMoves("HORIZONTAL");
		ArrayList<String> tab2 = this.possibleMoves("VERTICAL");
	    
	        
	    if(tab.size() >1 && tab2.size() >1) {
			System.out.println("PAS GAME OVER");

	    	return true;

	    }else {
			System.out.println("GAME OVER");

	        return false;

	    }
	        
	}
	
	/*
	 * Afficher le plateau
	 */
	public void printBoard() {
		
    	for(int i = 0; i<7; i++) {
        	for (int j=0; j<7 ; j++) {
        		System.out.print(board[i][j]);
        	}
        	System.out.print("\n");
        }
    }
	
	//Attention nextP doit être ecrit en minuscule (soit "horizontal" soit "vertical") 
	public void setNextPlayer(String nextP) {
		this.nextPlayer = nextP;
	}
	
	/*
	 * Nombre de piece sur l'aller
	 */
	public int nbPieceAller(String role) {
		
		int result = 0; 
		
		if(role.equals("HORIZONTAL")) {
			
			for(PieceSquadro p : listJ1) {
				if(p.getStatut() == 'A') {
					result+=1; 
				}
			}
			
		}else if(role.equals("VERTICAL")) {
			
			for(PieceSquadro p : listJ2) {
				if(p.getStatut() == 'A') {
					result+=1; 
				}
			}
			
		}else {
			System.out.println("ERREUR : role introuvable");
		}
		
		return result; 
	}
	
	/*
	 * Nombre de piece sur la phase retour
	 */
	public int nbPieceRetour(String role) {
		
		int result = 0; 
		
		if(role.equals("HORIZONTAL")) {
			
			for(PieceSquadro p : listJ1) {
				if(p.getStatut() == 'R') {
					result+=1; 
				}
			}
			
		}else if(role.equals("VERTICAL")) {
			
			for(PieceSquadro p : listJ2) {
				if(p.getStatut() == 'R') {
					result+=1; 
				}
			}
			
		}else {
			System.out.println("ERREUR : role introuvable");
		}
		
		return result; 
	}
	
	/*
	 * Nombre de piece en dehors du plateau
	 */
	public int nbPieceDehors(String role) {
		
		int result = 0;
		
		if(role.equals("HORIZONTAL")) {
			
			for(PieceSquadro p : listJ1) {
				if(p.getStatut() == 'D') {
					result+=1; 
				}
			}
			
		}else if(role.equals("VERTICAL")) {
			
			for(PieceSquadro p : listJ2) {
				if(p.getStatut() == 'D') {
					result+=1; 
				}
			}
			
		}else {
			System.out.println("ERREUR : role introuvable");
		}

		return result; 
		
	}
	

	
	//Permet de jouer un coup � l'envers
	public void reverseCoup(String move, String role) {
		
		String newPosition[] = {Character.toString(move.charAt(0)),Character.toString(move.charAt(1))};
		String positionCourante[] = {Character.toString(move.charAt(3)),Character.toString(move.charAt(4))};
		ArrayList<PieceSquadro> listPieceCourante = new ArrayList<PieceSquadro>() ;
		
		int tabInt_posCourante[] = convertStringToInt(positionCourante);
		int tabInt_newPos[] = convertStringToInt(newPosition);
		int i = 0;
		char symbole = ' ';
		boolean nonTrouve = true;
		
		if(role.equals("HORIZONTAL")) {
			listPieceCourante = listJ1;
		
		}else {
			listPieceCourante = listJ2;
		}

		while(nonTrouve) {
		
			
			if((listPieceCourante.get(i).getX() == tabInt_posCourante[0]) && (listPieceCourante.get(i).getY() == tabInt_posCourante[1])) {

				PieceSquadro pieceCourante = listPieceCourante.get(i);
				pieceCourante.setX(tabInt_newPos[0]);
				pieceCourante.setY(tabInt_newPos[1]);
				int bornInf = 0;
				int bornSup = 0;
		
				//Determine si le symbole (sur le plateau) et le status de la piece change avec la nouvelle position
				if(role.equals("HORIZONTAL")) {
					
					if(pieceCourante.getStatut()=='A') {
						
						symbole = '>';

					}else if(pieceCourante.getStatut()=='R') {
					
						if(tabInt_posCourante[0]== 6) {
							pieceCourante.setStatut('A');
							symbole = '>';		
						}else {
							symbole = '<';		
							pieceCourante.setStatut('R');
						}
 
					}else if(pieceCourante.getStatut()=='D') {
						pieceCourante.setStatut('R');
						symbole = '<';		

					}else {
						symbole = '.';

					}
	
					//Borne utilisées pour savoir si une pièce a été mangé quand le coup a été joué
					if(tabInt_newPos[0] > tabInt_posCourante[0]) {
						bornInf = tabInt_posCourante[0];
						bornSup = tabInt_newPos[0];
					
					}else {
						bornSup = tabInt_posCourante[0];
						bornInf = tabInt_newPos[0];
					}
							
					for(i = 0; i< this.listJ2.size(); i++) {
							
						if(this.listJ2.get(i).getY() == pieceCourante.getY()) {
								
							if(this.listJ2.get(i).getX() < bornSup  && this.listJ2.get(i).getX() > bornInf) {
								
								this.board[this.listJ2.get(i).getY()][this.listJ2.get(i).getX()]= '.';
									
								if(this.listJ2.get(i).getStatut() == 'A'){
								
									this.board[TAILLE-1][this.listJ2.get(i).getX()]= '^';
									this.listJ2.get(i).setY(TAILLE-1);
								
								}else{
									board[0][this.listJ2.get(i).getX()]= 'v';
									listJ2.get(i).setY(0);
								}
									
							}
								
						}
							
					}
								
				}else {
					
					if(pieceCourante.getStatut()=='A') {
						
						symbole ='^';

					}else if(pieceCourante.getStatut()=='R') {
						
						if(tabInt_posCourante[0]== 6) {
							pieceCourante.setStatut('A');
							symbole = '^';		
						}else {
							symbole = 'v';		
							pieceCourante.setStatut('R');
						}
						
					}else if(pieceCourante.getStatut()=='D') {
						pieceCourante.setStatut('R'); 
						symbole = 'v';
						
					}else {
						symbole = '.';
					}
					
					
					//Borne utilisées pour savoir si une pièce a été mangé quand le coup a été joué
					if(tabInt_newPos[1] > tabInt_posCourante[1]) {
						bornInf = tabInt_posCourante[1];
						bornSup = tabInt_newPos[1];
					
					}else {
						bornSup = tabInt_posCourante[1];
						bornInf = tabInt_newPos[1];
					}
					
					
					
					for(i = 0; i< this.listJ1.size(); i++) {
						
						if(this.listJ1.get(i).getX() == pieceCourante.getX()) {
							
							if(this.listJ1.get(i).getY() < bornSup  && this.listJ1.get(i).getY() > bornInf) {
							
								this.board[this.listJ1.get(i).getY()][this.listJ1.get(i).getX()]= '.';
								
								if(this.listJ1.get(i).getStatut() == 'A'){
								
									this.board[this.listJ1.get(i).getY()][0]= '>';
									this.listJ1.get(i).setX(0);
								
								}else{
									this.board[this.listJ1.get(i).getY()][TAILLE -1]= '<';
									this.listJ1.get(i).setX(TAILLE-1);
								}
								
							}
							
						}
						
					}
				}										
				
				nonTrouve = false;
			
			}
			
			i++;

		}
		
		//System.out.println("MOVE PLAYED : "+move);

		//Met a jour le plateau
		board[tabInt_posCourante[1]][tabInt_posCourante[0]]= '.';
		board[tabInt_newPos[1]][tabInt_newPos[0]]= symbole;		
	//	this.printBoard();

		
	}
	
	/*
	 * Nombre de coup du joueur avant sa victoire
	 * @return int
	 * @param String Role
	 */
	public int nbCoupRestantAvantVictoire(String role) {
			
		int res ; 
		int nbPieceDehors;
		int nbCaseRestanteAvantR; 
		int nbCaseRestanteAvantD; 
		float nbCoups = 0;
		
		if(role.equals("HORIZONTAL")) { // -> liste de coups J1

			for(PieceSquadro p : listJ1) {
				//System.out.println("BOUCLE_______________________________________");
				//System.out.println("STUATU DE LA PIECE : "+ p.getStatut());
				nbCaseRestanteAvantR = 0; 
				nbCaseRestanteAvantD = 0; 
				
				if(p.getStatut() == 'A') {
					//System.out.println("IN a");
					
					nbCaseRestanteAvantR =  (TAILLE-1) - p.getX();
					//System.out.println("case avant R : " + nbCaseRestanteAvantR);
					//System.out.println("TEST---------nbCoups : "+nbCoups);
					nbCoups += (((float)nbCaseRestanteAvantR) / p.getValeurDeplacement()); 
					
					//System.out.println("Resultat en nb de Coup : " +((float)nbCaseRestanteAvantR) / p.getValeurDeplacement());
					//System.out.println("NBCOUP : "+nbCoups);
					//Compter le nombre de déplacment sur le retour
					switch(p.getValeurDeplacement()) {
						case 1 : nbCoups+= ((TAILLE-1) / 3.0); break;
						case 2 : nbCoups+= ((TAILLE-1) / 2.0); break;
						case 3 : nbCoups+= ((TAILLE-1) / 1.0); break;
					}
					
				//System.out.println("NB COUP Final   =   " + nbCoups);	
				
				}else if(p.getStatut() == 'R') {
					
					//System.out.println("In R");
					//System.out.println("Valeur de coups : "+nbCoups);
					nbCaseRestanteAvantD = p.getX(); 
					
					//System.out.println("Nb case sur le R : " + nbCaseRestanteAvantD);
					
					nbCoups += ((float)nbCaseRestanteAvantD) / p.getValeurDeplacement();
					
					//System.out.println("Ajout sur le retour : " + ((float)nbCaseRestanteAvantD) / p.getValeurDeplacement() );
					//System.out.println("NBCOUPS F : "+nbCoups);
				} //else == 'D' alors on fait rien
			}
			
		}else { //if role == "VERTICAL" -> lites de coups J2
			
			for(PieceSquadro p : listJ2) {
				
				//System.out.println("LIST J2");
				
				nbCaseRestanteAvantR = 0; 
				nbCaseRestanteAvantD = 0; 
				
				if(p.getStatut() == 'A') {
					//System.out.println("Position Y : " + p.getY());
					//System.out.println("Value deplacmeent " + p.getValeurDeplacement());
					nbCaseRestanteAvantR = p.getY() ;
					//System.out.println("NB case restante avant retour : " + nbCaseRestanteAvantR );
					//System.out.println("Resultat division : " + ((float)nbCaseRestanteAvantR) / p.getValeurDeplacement());
					nbCoups+= ((float)nbCaseRestanteAvantR) / p.getValeurDeplacement(); 
					//System.out.println("Resultat en nb de Coup : " + ((float)nbCaseRestanteAvantR) / p.getValeurDeplacement());
					//System.out.println("NBCOUPSSS : " + nbCoups);
					//Compter le nombre de déplacment sur le retour
					switch(p.getValeurDeplacement()) {
						case 1 : nbCoups+= ((TAILLE-1) / 3.0); break;
						case 2 : nbCoups+= ((TAILLE-1) / 2.0); break;
						case 3 : nbCoups+= ((TAILLE-1) / 1.0); break;
					}
				
				}else if(p.getStatut() == 'R') {
					
					nbCaseRestanteAvantD =  (TAILLE-1) -  p.getY() ; 
					nbCoups += ((float)nbCaseRestanteAvantD) / p.getValeurDeplacement();
					//System.out.println("Ajout sur le retour : " + Math.ceil(((float)nbCaseRestanteAvantD) / p.getValeurDeplacement()) );
				} //else == 'D' alors on fait rien
				
				//System.out.println("NB COUP TOTAUX : " + nbCoups);
			}
			
			
		}
		//System.out.println("NB coups TOTAUX ====== " + nbCoups);
		res = (int)nbCoups;
		//System.out.println("Nombre de coups restant pour "+ role + " : " + res); 
		
		return res; 
	}
	
	
	/*
	 * Table de hash
	 */
	public String hashBoard() {
		
		String h = null;
    	for(int i = 0; i<7; i++) {
        	for (int j=0; j<7 ; j++) {
        		h+=board[i][j];
        	}
        }
    	
    	return h;
    }
	
	
	public String convertIntegerToString(Integer coordonee[]) {
		String coordonnneeString = "";
		
		switch(coordonee[0]) {
			
			//Si la coordonnée est une lettre
			case 0 : coordonnneeString="A"; break; 
			case 1 : coordonnneeString="B"; break;
			case 2 : coordonnneeString="C"; break; 
			case 3 : coordonnneeString="D"; break; 
			case 4 : coordonnneeString="E"; break;
			case 5 : coordonnneeString="F"; break; 
			case 6 : coordonnneeString="G"; break; 
			default:coordonnneeString="ERROR"; break;

		}
		
		coordonnneeString= coordonnneeString + Integer.toString(coordonee[1]+1);
		
		return 	coordonnneeString;
	
	}
	
	//---------------------------------------
	// MAIN
	//---------------------------------------
	
	public static void main(String[] args) {

	}


	
	
	
}

