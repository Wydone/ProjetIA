package iia.simpleGame.squadro;



import iia.simpleGame.algo.IAlgo;
import iia.simpleGame.algo.Minimax;
import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IChallenger;
import iia.simpleGame.base.Player;

public class ChallengerSquadro implements IChallenger{
	
	//Notre joueur challenger
	private Player my_player ; 
	
	//Le role de l'ennemi dans le plateau
	private String my_enemy_role; 
	
	private IAlgo my_algo ; 
	
	private ASquadroGame myGame;  
	
	
	//Comme le prof l'a dit, faire l'initialisation dans setRole
	public ChallengerSquadro() {
		
	}
	

	//Le nom de notre team
	@Override
	public String teamName() {
		
		return "COULON_CATONET";
	}

	@Override
	public void setRole(String role) {
		
		//LE CHOIX DES ALGO CE FONT DES LES CLASSES SQUADROGAMEH et SQUADROGAMEV
		switch(role) {
			case "HORIZONTAL": myGame =  new SquadroGameH(); 
			case "VERTICAL" :  myGame = new SquadroGameV();
		}
		
		
	}

	
	//Update la representation interne du notre plateau
	@Override
	public void iPlay(String move) {
		//On pas de conversion special dans la representaiton interne de notre plateau
		myGame.play(move, my_player.getRole());
	}

	@Override
	public void otherPlay(String move) {
		
		myGame.play(move, my_enemy_role);
	}

	@Override
	public String bestMove() {
		
		return my_algo.bestMove(myGame, my_player.getRole()); 
	}

	@Override
	public String victory() {
		
		return "Victory is mine !";
	}

	@Override
	public String defeat() {// TODO Auto-generated method stub
		return "I lost ... But I'll get you next time !";
	}

	@Override
	public String tie() {
	
		return "tie";
	}

}
