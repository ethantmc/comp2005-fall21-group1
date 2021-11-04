import java.util.ArrayList;

import javax.swing.plaf.ColorUIResource;

import java.awt.Color;

public class Driver {
	private static ArrayList<Player> players = new ArrayList<Player>(); //in future iterations this will be handled in SetupAGame
	static boolean x=true;
	private static Color []colors = {ColorUIResource.GREEN, Color.red, Color.blue,Color.YELLOW};

    public static void main(String []args){
    	for(int i=0;i<4;i++) //in future iterations this will be handled in SetupAGame
    	{
    		if(i%2==0)
    			x=false;
    		players.add(new Player("Player " + String.valueOf(i + 1), PlayerType.HUMAN, x));
    		players.get(i).setColor(colors[i]);
    	}
        SettingsUI stg = new SettingsUI();
    }
    public ArrayList<Player> getPlayers() //in future iterations this will be handled in SetupAGame
    {
		return players;
    }
    
}
