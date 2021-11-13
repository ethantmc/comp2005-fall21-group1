import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import java.awt.EventQueue;

public class SettingsUI {
	private JPanel headingPanel, allplayerSettingsPanel, fullscreenPanel, cancelApplyPanel;
	private JFrame frame = new JFrame();
	private JLabel heading;
	private JButton cancel, apply;
	private JToggleButton toggleFullscreen;
	private ArrayList<Player> playersBuffer = new ArrayList<Player>();
	private ArrayList<JToggleButton> toggleColorblind = new ArrayList<JToggleButton>();
	private ArrayList<JPanel> playerSettingsPanel = new ArrayList<JPanel>();
	private ArrayList<JTextField> playerNameField = new ArrayList<JTextField>();
	private Settings settings = new Settings();
	private SetupAGame driver = new SetupAGame();

	public SettingsUI() {
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		// For cross platform performance.
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		CreateHeadingPanel();
		CreatePlayerSettingsPanel();
		CreateBottomPanel();
		frame.setVisible(true);

	}

	//creates the bottom panel which includes Cancel, Apply and Fullscreen toggle
	public void CreateBottomPanel() {
		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(4, 1));

		// FULSCREEN
		Component horizontalStrut = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut);
		fullscreenPanel = new JPanel();
		fullscreenPanel.setLayout(new BorderLayout());
		JLabel fullScreen = new JLabel("Toggle Fullscreen Mode:");
		fullscreenPanel.add(fullScreen, BorderLayout.CENTER);
		toggleFullscreen = new JToggleButton("OFF");
		toggleFullscreen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		toggleFullscreen.addActionListener(actionListener);
		fullscreenPanel.add(toggleFullscreen, BorderLayout.SOUTH); 
		bottomPanel.add(fullscreenPanel);

		Component horizontalStrut1 = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut1);
		
		//CANCEL APPLY Buttons
		cancelApplyPanel = new JPanel();
		cancelApplyPanel.setLayout(new GridLayout(1, 2));
		cancel = new JButton("Cancel");
		cancel.addActionListener(actionListener);
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelApplyPanel.add(cancel);
		apply = new JButton("Apply");
		apply.addActionListener(actionListener);
		apply.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelApplyPanel.add(apply);
		bottomPanel.add(cancelApplyPanel);

	}
	
	//fetches player specific attributes and allows the user to change those attributes
	private void CreatePlayerSettingsPanel() {
		allplayerSettingsPanel = new JPanel();
		allplayerSettingsPanel.setLayout(new GridLayout(2, 2));
		
		//creates 4 panels for 4 players
		for (int i = 0; i < 4; i++) {

			JLabel changePlayerName = new JLabel("Change Player Name:");
			changePlayerName.setVerticalAlignment(SwingConstants.TOP);
			JLabel colourDeficiencyMode = new JLabel("Colour Deficiency Mode:");
			colourDeficiencyMode.setVerticalAlignment(SwingConstants.TOP);
			
			//fetches player objects from Driver **in future iterations this will be fetched from SetupAGame
			playersBuffer = driver.getPlayers();
			playerSettingsPanel.add(new JPanel());
			playerSettingsPanel.get(i).setLayout(new GridLayout(6,1));
			toggleColorblind.add(new JToggleButton("Disabled"));
			toggleColorblind.get(i).addActionListener(actionListener);
			if (playersBuffer.get(i).getColorblindSetting()) {
				toggleColorblind.get(i).doClick();
				toggleColorblind.get(i).setText("Enabled");
			}
			playerNameField.add(new JTextField());
			playerNameField.get(i).setColumns(10);
			playerNameField.get(i).setText(playersBuffer.get(i).getName());
			playerSettingsPanel.get(i).setBackground(playersBuffer.get(i).getColor());
			playerSettingsPanel.get(i).add(changePlayerName);
			playerSettingsPanel.get(i).add(playerNameField.get(i));
			Component horizontalStrut1 = Box.createHorizontalStrut(20);
			playerSettingsPanel.get(i).add(horizontalStrut1);
			playerSettingsPanel.get(i).add(colourDeficiencyMode);
			playerSettingsPanel.get(i).add(toggleColorblind.get(i));
			Component horizontalStrut2 = Box.createHorizontalStrut(20);
			playerSettingsPanel.get(i).add(horizontalStrut2);
			allplayerSettingsPanel.add(playerSettingsPanel.get(i));
			if (i % 2 == 0) {
				Component verticalStrut = Box.createVerticalStrut(5);
				allplayerSettingsPanel.add(verticalStrut);
			}
		}

		frame.getContentPane().add(allplayerSettingsPanel, BorderLayout.CENTER);
	}

	
	//creates the heading
	public void CreateHeadingPanel() {
		headingPanel = new JPanel();
		// headingPanel.setLayout(new FlowLayout());

		heading = new JLabel("Settings");
		// heading.setPreferredSize((new Dimension(350,100)));
		heading.setFont(new Font("Tahoma", Font.BOLD, 35));
		heading.setHorizontalAlignment(JLabel.CENTER);
		heading.setVerticalAlignment(JLabel.CENTER);

		headingPanel.add(heading);

		frame.getContentPane().add(headingPanel, BorderLayout.NORTH);

	}


	//ActionListener to perform all the actions
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e)

		{

			if (e.getSource() == toggleColorblind.get(0)) {
				if (toggleColorblind.get(0).getText() == "Disabled") {
					toggleColorblind.get(0).setText("Enabled");
					settings.addBuffer("p1.toggleColorblind");
				} else
					toggleColorblind.get(0).setText("Disabled");
			}

			if (e.getSource() == toggleColorblind.get(1)) {
				if (toggleColorblind.get(1).getText() == "Disabled") {
					toggleColorblind.get(1).setText("Enabled");
					settings.addBuffer("p2.toggleColorblind");

				} else
					toggleColorblind.get(1).setText("Disabled");
			}

			if (e.getSource() == toggleColorblind.get(2)) {
				if (toggleColorblind.get(2).getText() == "Disabled") {
					toggleColorblind.get(2).setText("Enabled");
					settings.addBuffer("p3.toggleColorblind");

				} else
					toggleColorblind.get(2).setText("Disabled");
			}

			if (e.getSource() == toggleColorblind.get(3)) {
				if (toggleColorblind.get(3).getText() == "Disabled") {
					toggleColorblind.get(3).setText("Enabled");
					settings.addBuffer("p4.toggleColorblind");

				} else
					toggleColorblind.get(3).setText("Disabled");
			}

			if (e.getSource() == toggleFullscreen) {
				if (toggleFullscreen.getText() == "OFF") {
					toggleFullscreen.setText("ON");
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setUndecorated(true);
					frame.setVisible(true);
				} else {
					toggleFullscreen.setText("OFF");
					frame.setBounds(0, 0, 800, 800);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}

			}
			
			if (e.getSource() == apply) {
				
				int input = JOptionPane.showConfirmDialog(null, 
		                "Do you want to apply changes?", "Select an Option...",JOptionPane.YES_NO_OPTION);
				if (input == 0) 
				{
					
					playerNameField.forEach(i->settings.addNameBuffer(i.getText()));
					settings.applySettings();
					frame.dispose();
				}

			}
			
			if (e.getSource() == cancel) 
			{
				int input = JOptionPane.showConfirmDialog(null, 
		                "Do you want to revert changes?", "Select an Option...",JOptionPane.YES_NO_OPTION);
				if (input == 0) 
				{
					playerNameField.clear();
					settings.declineSettings();
					frame.dispose();
				}

			}
			;


		}
	};

}
