import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.plaf.ColorUIResource;

public class SetupAGame {
	private static ArrayList<Player> players = new ArrayList<Player>(); 
	static boolean x = false;
	private static Color[] colors = { ColorUIResource.GREEN, Color.red, Color.blue, Color.YELLOW };
	//private static JFrame frame;
	private static GameUI window;

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++)
		{
			if (i % 2 == 0)
				x = false;
			players.add(new Player("Player " + String.valueOf(i + 1), PlayerType.HUMAN, x));
			players.get(i).setColor(colors[i]);
		}
		// SettingsUI stg = new SettingsUI();

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

	public static ArrayList<Player> getPlayers() 
	{
		return players;
	}

}
