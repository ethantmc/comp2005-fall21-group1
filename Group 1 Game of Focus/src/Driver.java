import java.util.ArrayList;

import javax.swing.plaf.ColorUIResource;

import java.awt.*;

import javax.swing.JFrame;

public class Driver {
	private static ArrayList<Player> players = new ArrayList<Player>(); //in future iterations this will be handled in SetupAGame
	static boolean x=true;
	private static Color []colors = {ColorUIResource.GREEN, Color.red, Color.blue,Color.YELLOW};
	private static JFrame frame;
	private static GameUI window;

    public static void main(String []args){
    	for(int i=0;i<4;i++) //in future iterations this will be handled in SetupAGame
    	{
    		if(i%2==0)
    			x=false;
    		players.add(new Player("Player " + String.valueOf(i + 1), PlayerType.HUMAN, x));
    		players.get(i).setColor(colors[i]);
    	}
        //SettingsUI stg = new SettingsUI();
       // BoardUI window = new BoardUI();
    	GameUI window1 = new GameUI();
    	window = window1;
        window.setVisible(true);
        frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		//frame.setBounds(100, 100, 900, 700);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(window, BorderLayout.CENTER);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
    }
    public static GameUI getGameUIInstance()
    {
    	return window;
    }
    public static ArrayList<Player> getPlayers() //in future iterations this will be handled in SetupAGame
    {
		return players;
    }
    
}
