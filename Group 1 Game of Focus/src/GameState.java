import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class GameState {
	private static boolean isNewGame = true;

	public static void saveGame() {
		//Save Stacks
		try{
		    FileOutputStream writeData = new FileOutputStream("Stacks.data");
		    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

		    writeStream.writeObject(Board.getStacks());
		    writeStream.flush();
		    writeStream.close();

		}catch (IOException e) {
		    e.printStackTrace();
		}
		
		//Save Palyers
		try{
		    FileOutputStream writeData = new FileOutputStream("Players.data");
		    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

		    writeStream.writeObject(SetupAGame.getPlayers());
		    writeStream.flush();
		    writeStream.close();

		}catch (IOException e) {
		    e.printStackTrace();
		}
		
		//Save Turn.getCurrentPlayer()
		try{
		    FileOutputStream writeData = new FileOutputStream("currentPlayer.data");
		    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

		    writeStream.writeObject(Turn.getCurrentPlayer());
		    writeStream.flush();
		    writeStream.close();

		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void loadGame() {

		isNewGame=false;
		
		//Load Players
		try{
		    FileInputStream readData = new FileInputStream("Players.data");
		    ObjectInputStream readStream = new ObjectInputStream(readData);

		    ArrayList<Player> players = (ArrayList<Player>) readStream.readObject();
		    readStream.close();
		    SetupAGame.setPlayers(players);
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
		//Load currentPlayer
		try{
		    FileInputStream readData = new FileInputStream("currentPlayer.data");
		    ObjectInputStream readStream = new ObjectInputStream(readData);

		    Player player = (Player) readStream.readObject();
		    readStream.close();
		    Turn.setCurrentPlayer(player);
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
		//Load Stacks
		try{
		    FileInputStream readData = new FileInputStream("Stacks.data");
		    ObjectInputStream readStream = new ObjectInputStream(readData);

		    Stack[][] stacks = (Stack[][]) readStream.readObject();
		    readStream.close();
		    Board.setStacks(stacks);
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
		Board.updateDominationPercentageForAllPlayers();
		Board.updateStacksDisplay();
		SetupAGame.getGameUIInstance().updateStats();
		SetupAGame.getGameUIInstance().initializeBoardUI();
		SwingUtilities.updateComponentTreeUI(SetupAGame.getGameUIInstance());
	}

	public static boolean isNewGame() {
		return isNewGame;
	}

	public static void setNewGame(boolean isNewGame) {
		GameState.isNewGame = isNewGame;
	}
	
	
}
