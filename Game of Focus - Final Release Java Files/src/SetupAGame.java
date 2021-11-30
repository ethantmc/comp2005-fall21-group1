import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.plaf.ColorUIResource;

public class SetupAGame {
	private static ArrayList<Player> players = new ArrayList<Player>();
	static boolean x = true;
	private static Color[] colors = { Color.decode("#4ECB71"), Color.decode("#FF5757"), Color.decode("#4590FF"), Color.decode("#FFCD1C") };
	private static GameUI window;


	public static GameUI getGameUIInstance() {
		if(window==null)
			window = new GameUI();
		return window;
	}
	
	public static void createAPlayer(String name, PlayerType type, Boolean colorblindSetting, DifficultyType difficulty) {
		players.add(new Player(name,type,colorblindSetting,difficulty));
	}
	
	public static void startGame()
	{
//		Collections.shuffle(players);
		for (int i = 0; i < 4; i++)
		{
			players.get(i).setColor(colors[i]);
		}
		if(window==null || GameState.isNewGame())
		{
			window = new GameUI();
		}
//		if(GameState.isNewGame())
//		{
//			window = new GameUI();
//			//players.forEach(i -> i.resetStats());
//		}
		window.setVisible(true);
		
		if(Turn.getCurrentPlayer().getType() == PlayerType.CPU) 
		{
			GameAI.cpuDoMove(Turn.getCurrentPlayer());
		}
		
	}

	public static ArrayList<Player> getPlayers() 
	{
		return players;
	}
	public static void setPlayers(ArrayList<Player> players) {
		SetupAGame.players = players;
	}

}
