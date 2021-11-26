
public class GameState {
	private static boolean isNewGame = true;

	public static void saveGame() {
		//TODO method stub
	}
	
	public static void loadGame() {
		//TODO method stub
		isNewGame=false;
	}

	public static boolean isNewGame() {
		return isNewGame;
	}

	public static void setNewGame(boolean isNewGame) {
		GameState.isNewGame = isNewGame;
	}
	
	
}
