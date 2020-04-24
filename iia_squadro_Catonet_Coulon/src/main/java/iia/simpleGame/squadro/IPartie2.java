package iia.simpleGame.squadro;

import java.util.ArrayList;

public interface IPartie2 {

	/** initialise un plateau à partir d’un fichier texte
	* @param fileName le nom du fichier à lire
	*/
	public void setFromFile(String fileName);
	/** sauve la configuration de l’état courant (plateau et pièces restantes)
	* dans un fichier
	* @param fileName le nom du fichier à sauvegarder
	* Le format doit ^
	etre compatible avec celui utilisé pour la lecture.
	*/
	public void saveToFile(String fileName);
	/** indique si le coup <move> est valide pour le joueur <player>
	* sur le plateau courant
	* @param move le coup à jouer, sous la forme "A4-C4"
	* @param player le joueur qui joue, représenté par "vertical" ou "horizontal"
	*/
	public boolean isValidMove(String move, String player);
	/** calcule les coups possibles pour le joueur <player> sur le plateau courant
	* @param player le joueur qui joue, représenté par "vertical" ou "horizontal"
	*/
	public ArrayList<String> possibleMoves(String player);
	/** modifie le plateau en jouant le coup move avec la pièce choose
	* @param move le coup à jouer, sous la forme "A4-C4"
	* @param player le joueur qui joue, représenté par "vertical" ou "horizontal"
	*/
	public void play(String move, String role);
	/** vrai lorsque le plateau correspond à une fin de partie
	*/
	public boolean gameOver();
	
}
