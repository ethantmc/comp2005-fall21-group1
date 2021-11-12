import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Turn {
	private static Player currentPlayer = null;
	private static int currentPlayerAsNumber;
	private static ArrayList<Player> listOfPlayers = Driver.getPlayers();
	
	
//	public Turn()
//	{
//		initiatePlayerTurn();
//	}

	private void generateNextPlayer() {
		currentPlayerAsNumber += 1;
		currentPlayerAsNumber = ((currentPlayerAsNumber) % 4); //If the number would be 4, it loops around to 0 instead.
		currentPlayer = listOfPlayers.get(currentPlayerAsNumber); //Retrieves the Player object in the space after the current player. 
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	//	private void setCurrentPlayer(Player currentPlayer) { //The actual method for settings whose next. Should not be used, but may be necessary in a future iteration.
	//		this.currentPlayer = currentPlayer;
	//	}

	public static void initiatePlayerTurn() { //Only to be called the very first turn for the game.
		int playerTurn;
		playerTurn = ThreadLocalRandom.current().nextInt(0, 4); //Selects from [0,4)
		currentPlayer = listOfPlayers.get(playerTurn);
		currentPlayerAsNumber = playerTurn;
	}
	public void nextPlayersTurn() {
		if (currentPlayer== null) {
			initiatePlayerTurn();
		}
		else {
			generateNextPlayer();
		}
	}
}
