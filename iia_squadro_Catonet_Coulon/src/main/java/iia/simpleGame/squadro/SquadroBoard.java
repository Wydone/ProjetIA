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
		
			coordonnneeInt[1]=Integer.parseInt(coordonee[1]);
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
			
			coordonnneeString= coordonnneeString + Integer.toString(coordonee[1]);
			
		return 	coordonnneeString;
		
		}


	@Override
	public boolean isValidMove(String move, String player) {
		
		boolean isValidMove = false;

		if(move != "None") {
			
			String positionCourante[] = {Character.toString(move.charAt(0)),Character.toString(move.charAt(1))};
			String newPosition[] = {Character.toString(move.charAt(3)),Character.toString(move.charAt(4))};
			ArrayList<PieceSquadro> listPieceCourante = new ArrayList<PieceSquadro>() ;
			
			int tabInt_posCourante[] = convertStringToInt(positionCourante); 
			int tabInt_newPos[] = convertStringToInt(newPosition); 
			int i = 0;		
			
			boolean nonTrouve = true;
			
			if(player == "horizontal") {
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
	
					if(player == "horizontal") {
							
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
							
						}else if(player == "verticale") {
	
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
	public String[] possibleMoves(String player) {
		
		String possibleMoves[] = {"", "", "", "", ""};
		String possibleMove ="";
		
		if(player == "horizontal") {
			
			for (int i = 0; i < listJ1.size(); i++) {
				
				PieceSquadro pieceCourrante =listJ1.get(i);
				int deplacementCourant = pieceCourrante.getValeurDeplacement();
				int valDeplacement = 0;
			        
				//Test si la piece est a l'allé
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
										
							//La piece arrete son deplacement
							deplacementCourant = -1;

						}
					}
					
				}else if( pieceCourrante. getX() > 0 && pieceCourrante.getStatut() =='R') {
						

					//Tant que la piece doit avancer
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
	
	@Override
	public void play(String move, String role) {
		
		String positionCourante[] = {Character.toString(move.charAt(0)),Character.toString(move.charAt(1))};
		String newPosition[] = {Character.toString(move.charAt(3)),Character.toString(move.charAt(4))};
		ArrayList<PieceSquadro> listPieceCourante = new ArrayList<PieceSquadro>() ;
		
		int tabInt_posCourante[] = convertStringToInt(positionCourante);
		int tabInt_newPos[] = convertStringToInt(newPosition);
		int i = 0;
		char symbole = ' ';
		boolean nonTrouve = true;
		
		if(role == "horizontal") {
			listPieceCourante = this.listJ1;
		
		}else {
			listPieceCourante = this.listJ2;
		}

		while(nonTrouve) {
		
			//System.out.println("tabInt_posCourante[0] : " + tabInt_posCourante[0]);
	    	//System.out.println("tabInt_posCourante[1] : " + tabInt_posCourante[1]);
	    	//System.out.println("listPieceCourante.get(i).getY()] : " + listPieceCourante.get(i).getY());

			//Met a jpur la position des pièce dans la liste
			if((listPieceCourante.get(i).getX() == tabInt_posCourante[0]) && (listPieceCourante.get(i).getY() == tabInt_posCourante[1])) {

				PieceSquadro pieceCourante = listPieceCourante.get(i);
				pieceCourante.setX(tabInt_newPos[0]);
				pieceCourante.setY(tabInt_newPos[1]);
				int bornInf = 0;
				int bornSup = 0;
		
				//Determine si le symbole (sur le plateau) et le status de la piece change avec la nouvelle position
				if(role == "horizontal") {
					
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

		//Met a jour le plateau
		board[tabInt_posCourante[1]][tabInt_posCourante[0]]= '.';
		board[tabInt_newPos[1]][tabInt_newPos[0]]= symbole;		
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
       
        System.out.println("Test fichier");
        
        b.saveToFile("TEST1"); 
        b.setFromFile("TEST1");
        
        b.printBoard();
        b.setFromFile("TEST1");
        b.printBoard(); 
        
        
        
        System.out.println("Debut des test pour jouer des pieces");
        
        String tab[]= b.possibleMoves("horizontal");
        b.play(tab[4],"horizontal");
        b.play(tab[3],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[3],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[3],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[3],"horizontal");  
        tab = b.possibleMoves("horizontal");
        b.play(tab[3],"horizontal");  
        tab = b.possibleMoves("horizontal");
        b.play(tab[3],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[3],"horizontal");
        b.printBoard();

        b.play(tab[0],"horizontal");
        b.play(tab[1],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[1],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[1],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[1],"horizontal");  
        tab = b.possibleMoves("horizontal");
        b.play(tab[1],"horizontal");  
        tab = b.possibleMoves("horizontal");
        b.play(tab[1],"horizontal");
        tab = b.possibleMoves("horizontal");
        b.play(tab[1],"horizontal");
        
        
        
        
        
    	System.out.println("\nPrint board");
        b.printBoard(); 
        
        String tab2[]= b.possibleMoves("verticale");
        System.out.println(Arrays.toString(tab2));

        
        b.play(tab2[0],"verticale");
    	System.out.println("\nPrint board");
        b.printBoard();

        tab2= b.possibleMoves("verticale");
        System.out.println(Arrays.toString(tab2));

        
        b.play(tab2[0],"verticale");
    	System.out.println("\nPrint board");
        b.printBoard();
        
        tab2= b.possibleMoves("verticale");
        System.out.println(Arrays.toString(tab2));

        
        b.play(tab2[0],"verticale");
    	System.out.println("\nPrint board");
        b.printBoard();
        
        tab2= b.possibleMoves("verticale");
        System.out.println(Arrays.toString(tab2));

        
        b.play(tab2[0],"verticale");
    	System.out.println("\nPrint board");
        b.printBoard();
        
        
        
        
        
        
        System.out.println(" ======= GAME OVER =======");

    }

}

