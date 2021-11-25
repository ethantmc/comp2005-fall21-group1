import java.util.ArrayList;

public class Settings {
	private static ArrayList<String> settingsBuffer = new ArrayList<String>();
	private static ArrayList<String> customNamesBuffer = new ArrayList<String>();
	private static ArrayList<Player> playersBuffer = new ArrayList<Player>();

	public Settings()
	{
		playersBuffer = SetupAGame.getPlayers();
		
	}
	public static void addBuffer(String setting) {
		settingsBuffer.add(setting);
	}
	public static void addNameBuffer(String name) {
		customNamesBuffer.add(name);
	}
	public static void applySettings() {
		
		//apply toggleColorblind
		settingsBuffer.stream()
		.forEach( i -> 
		{
			if (i == "p1.toggleColorblind")
			{
				playersBuffer.get(0).toggleColorblindSetting();
			}
			if (i == "p2.toggleColorblind")
			{
				playersBuffer.get(1).toggleColorblindSetting();
			}
			if (i == "p3.toggleColorblind")
			{
				playersBuffer.get(2).toggleColorblindSetting();
			}
			if (i == "p4.toggleColorblind")
			{
				playersBuffer.get(3).toggleColorblindSetting();
			}
			
		});
		
		//apply player names
		for(int j = 0; j<4; j++)
		{
			playersBuffer.get(j).setName(customNamesBuffer.get(j));
		};
		
	}
	public static void declineSettings() {
		settingsBuffer.clear();
		customNamesBuffer.clear();
	}

}