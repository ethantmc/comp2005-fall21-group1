import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.plaf.ColorUIResource;

public class SetupAGame {
	private static ArrayList<Player> players = new ArrayList<Player>();
	static boolean x = true;
	private static Color[] colors = { Color.decode("#4ECB71"), Color.decode("#FF5757"), Color.decode("#4590FF"), Color.decode("#FFCD1C") };
	//private static JFrame frame;
	private static GameUI window;

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++)
		{
			if (i % 2 == 0)
				x = false;//Colorblind false
			else
				x = true;//Colorblind true
			players.add(new Player("Player " + String.valueOf(i + 1), PlayerType.HUMAN, x));
			players.get(i).setColor(colors[i]);
		}
		SettingsUI stg = new SettingsUI();

		window = new GameUI();
		window.setVisible(true);
//		frame = new JFrame();
//		frame.getContentPane().setBackground(Color.WHITE);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(window, BorderLayout.CENTER);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
//		frame.setVisible(true);
	}

	public static GameUI getGameUIInstance() {
		return window;
	}
	
	public static void createPlayers(ArrayList<Player> players) {
		SetupAGame.players = players;
	}


	public static ArrayList<Player> getPlayers() 
	{
		return players;
	}
	public static void setPlayers(ArrayList<Player> players) {
		SetupAGame.players = players;
	}

}
