import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Settings {
	private static ArrayList<Integer> settingsBuffer = new ArrayList<Integer>();
	private static ArrayList<String> customNamesBuffer = new ArrayList<String>();
	private static boolean isFullscreenBuffer = false;

	public static void addBuffer(int i) {
		settingsBuffer.add(i);
	}
	public static void addNameBuffer(String name) {
		customNamesBuffer.add(name);
	}
	public static void setFullscreenBuffer(boolean isFullscreen) {
		Settings.isFullscreenBuffer = isFullscreen;
	}
	public static boolean getFullscreenBuffer() {
		return Settings.isFullscreenBuffer;
	}
	public static void applySettings() {
		
		//apply toggleColorblind
		settingsBuffer
		.forEach( i -> 
		{
			System.out.println(i);
			if (i == 1)
			{
				SetupAGame.getPlayers().get(0).toggleColorblindSetting();
			}
			else if (i == 2)
			{
				SetupAGame.getPlayers().get(1).toggleColorblindSetting();
			}
			else if (i == 3)
			{
				SetupAGame.getPlayers().get(2).toggleColorblindSetting();
			}
			else if (i == 4)
			{
				SetupAGame.getPlayers().get(3).toggleColorblindSetting();
			};
			
		});
		
		//apply player names
		for(int j = 0; j<4; j++)
		{
			SetupAGame.getPlayers().get(j).setName(customNamesBuffer.get(j));
		};
		//FullScreen
		if(isFullscreenBuffer)
		{
			SetupAGame.getGameUIInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
			SetupAGame.getGameUIInstance().setVisible(true);
		}
		else
		{
			SetupAGame.getGameUIInstance().setBounds(100, 100, 1500, 1050);
			SetupAGame.getGameUIInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			SetupAGame.getGameUIInstance().setVisible(true);
		}
		Board.updateStacksDisplay();
		SetupAGame.getGameUIInstance().updateStats();
		SwingUtilities.updateComponentTreeUI(SetupAGame.getGameUIInstance());
		
	}
	public static void declineSettings() {
		settingsBuffer.clear();
		customNamesBuffer.clear();
	}


}