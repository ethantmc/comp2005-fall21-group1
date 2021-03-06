import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Turn {
	private Player currentPlayer;
	private ArrayList<Player> listOfPlayers;

	public String getCurrentPlayer() {
		return currentPlayer.getName();
	}

	private void initiatePlayerTurn() {
		int playerTurn;
		playerTurn = ThreadLocalRandom.current().nextInt(1, 5); //Selects from [1,5)
		currentPlayer = listOfPlayers.get(playerTurn);
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
