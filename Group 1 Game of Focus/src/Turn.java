import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Turn {
	private Player currentPlayer = null;
	private int currentPlayerAsNumber;
	private ArrayList<Player> listOfPlayers;

	private void generateNextPlayer() {
		currentPlayerAsNumber += 1;
		currentPlayerAsNumber = ((currentPlayerAsNumber) % 4); //If the number would be 4, it loops around to 0 instead.
		currentPlayer = listOfPlayers.get(currentPlayerAsNumber); //Retrieves the Player object in the space after the current player. 
	}

	public String getCurrentPlayer() {
		return currentPlayer.getName();
	}

	//	private void setCurrentPlayer(Player currentPlayer) { //The actual method for settings whose next. Should not be used, but may be necessary in a future iteration.
	//		this.currentPlayer = currentPlayer;
	//	}

	public void initiatePlayerTurn() { //Only to be called the very first turn for the game.
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
