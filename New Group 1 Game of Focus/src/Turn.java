import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.SwingUtilities;

public class Turn {
	private static Player currentPlayer = null;
	private static int currentPlayerAsNumber;
	private static ArrayList<Player> listOfPlayers = SetupAGame.getPlayers();

	private static void generateNextPlayer() {
		currentPlayerAsNumber += 1;
		currentPlayerAsNumber = ((currentPlayerAsNumber) % 4); // If the number would be 4, it loops around to 0
																// instead.
		currentPlayer = listOfPlayers.get(currentPlayerAsNumber); // Retrieves the Player object in the space after the
		if (currentPlayer.getDomination() <= 0) {
		generateNextPlayer();
		}															// current player.
		else if((currentPlayer.getDomination() == 100) && (checkForReservePieces() == 0)) {
		//Winner is currentPlayer, call a method to change the current turn text to say currentPlayer is the winner.
		GameUI.setWinner(currentPlayer);
		}
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static int checkForReservePieces() {
	int i;
	for(i=0; i < 4; i++) {
		if(listOfPlayers.get(i) == currentPlayer) {
			//do nothing
			}
	
		else if(listOfPlayers.get(i).getReserveCount() >0) {

		return 1;
	}
	
	}
	return 0;
	}
	
	public static void initiatePlayerTurn() { // Only to be called the very first turn for the game.
		int playerTurn;
		playerTurn = ThreadLocalRandom.current().nextInt(0, 4); // Selects from [0,4)
		currentPlayer = listOfPlayers.get(playerTurn);
		currentPlayerAsNumber = playerTurn;
	}

	public static void nextPlayersTurn() {
		if (currentPlayer == null) {
			initiatePlayerTurn();
		} else {
			generateNextPlayer();
		}

		Board.updateDominationPercentageForAllPlayers();
		Board.updateStacksDisplay();
		SwingUtilities.updateComponentTreeUI(SetupAGame.getGameUIInstance());
	}
}
