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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

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
	// private Settings settings = new Settings();

	public SettingsUI() {
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

	// creates the bottom panel which includes Cancel, Apply and Fullscreen toggle
	public void CreateBottomPanel() {

	}

	// fetches player specific attributes and allows the user to change those
	// attributes
	private void CreatePlayerSettingsPanel() {

		allplayerSettingsPanel = new JPanel();
		GridLayout gl_allplayerSettingsPanel = new GridLayout(2, 2);
		gl_allplayerSettingsPanel.setVgap(20);
		gl_allplayerSettingsPanel.setHgap(20);
		allplayerSettingsPanel.setLayout(gl_allplayerSettingsPanel);
		// fetched from SetupAGame
		playersBuffer = SetupAGame.getPlayers();
		// creates 4 panels for 4 players
		for (int i = 0; i < 4; i++) {
//			JPanel panel = new JPanel();
//			frame.getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
			playerSettingsPanel.add(new JPanel());
			playerSettingsPanel.get(i).setLayout(gbl_panel);

			JLabel changePlayerName = new JLabel("Change Player Name:");
			changePlayerName.setVerticalAlignment(SwingConstants.TOP);

			// add changePlayerName label
			GridBagConstraints gbc_name = new GridBagConstraints();
			gbc_name.insets = new Insets(0, 0, 5, 5);
			gbc_name.gridx = 1;
			gbc_name.gridy = 1;
			playerSettingsPanel.get(i).add(changePlayerName, gbc_name);
			playerNameField.add(new JTextField());
			playerNameField.get(i).setColumns(10);
			playerNameField.get(i).setText(playersBuffer.get(i).getName());

			playerSettingsPanel.get(i).setBackground(playersBuffer.get(i).getColor());

			// add playerNameField
			GridBagConstraints gbc_namefield = new GridBagConstraints();
			gbc_namefield.insets = new Insets(0, 0, 5, 5);
			gbc_namefield.gridx = 1;
			gbc_namefield.gridy = 2;
			playerSettingsPanel.get(i).add(playerNameField.get(i), gbc_namefield);

			// add colourDeficiencyMode label
			JLabel colourDeficiencyMode = new JLabel("Colour Deficiency Mode:");
			colourDeficiencyMode.setVerticalAlignment(SwingConstants.TOP);
			// JLabel color = new JLabel("New label");
			GridBagConstraints gbc_color = new GridBagConstraints();
			gbc_color.insets = new Insets(0, 0, 5, 5);
			gbc_color.gridx = 1;
			gbc_color.gridy = 4;
			playerSettingsPanel.get(i).add(colourDeficiencyMode, gbc_color);

			// add toggleColorblind button
			toggleColorblind.add(new JToggleButton("Disabled"));
			toggleColorblind.get(i).addActionListener(actionListener);
			if (playersBuffer.get(i).getColorblindSetting()) {
				toggleColorblind.get(i).setText("Enabled");
			}

			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 1;
			gbc_button.gridy = 5;
			playerSettingsPanel.get(i).add(toggleColorblind.get(i), gbc_button);

			allplayerSettingsPanel.add(playerSettingsPanel.get(i));
//			if (i % 2 == 0) {
//				Component verticalStrut = Box.createVerticalStrut(5);
//				allplayerSettingsPanel.add(verticalStrut);
//			}
		}
		headingPanel = new JPanel();
		headingPanel.setBackground(Color.DARK_GRAY);
		// headingPanel.setLayout(new FlowLayout());

		heading = new JLabel("Settings");
		heading.setForeground(Color.WHITE);
		// heading.setPreferredSize((new Dimension(350,100)));
		heading.setFont(new Font("Tahoma", Font.BOLD, 35));
		heading.setHorizontalAlignment(JLabel.CENTER);
		heading.setVerticalAlignment(JLabel.CENTER);

		headingPanel.add(heading);

		GridBagConstraints gbc_headingPanel = new GridBagConstraints();
		gbc_headingPanel.gridwidth = 3;
		gbc_headingPanel.anchor = GridBagConstraints.NORTH;
		gbc_headingPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_headingPanel.insets = new Insets(0, 0, 5, 0);
		gbc_headingPanel.gridx = 0;
		gbc_headingPanel.gridy = 1;
		frame.getContentPane().add(headingPanel, gbc_headingPanel);

		GridBagConstraints gbc_allplayerSettingsPanel = new GridBagConstraints();
		gbc_allplayerSettingsPanel.fill = GridBagConstraints.BOTH;
		gbc_allplayerSettingsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_allplayerSettingsPanel.gridx = 1;
		gbc_allplayerSettingsPanel.gridy = 3;
		frame.getContentPane().add(allplayerSettingsPanel, gbc_allplayerSettingsPanel);
		JPanel bottomPanel = new JPanel();
		GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.insets = new Insets(0, 0, 5, 5);
		gbc_bottomPanel.anchor = GridBagConstraints.NORTH;
		gbc_bottomPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_bottomPanel.gridx = 1;
		gbc_bottomPanel.gridy = 4;
		frame.getContentPane().add(bottomPanel, gbc_bottomPanel);
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

		// CANCEL APPLY Buttons
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

	// creates the heading
	public void CreateHeadingPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 524, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 53, 0, 470, 180, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

	}

	// ActionListener to perform all the actions
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e)

		{

			if (toggleColorblind.contains(e.getSource())) {
				int i = toggleColorblind.indexOf(e.getSource());

				if (toggleColorblind.get(i).getText() == "Disabled") {
					toggleColorblind.get(i).setText("Enabled");
					Settings.addBuffer("p" + String.valueOf(i + 1) + ".toggleColorblind");
				} else
					toggleColorblind.get(i).setText("Disabled");
			}
//			if (e.getSource() == toggleColorblind.get(0)) {
//				if (toggleColorblind.get(0).getText() == "Disabled") {
//					toggleColorblind.get(0).setText("Enabled");
//					Settings.addBuffer("p1.toggleColorblind");
//				} else
//					toggleColorblind.get(0).setText("Disabled");
//			}
//
//			if (e.getSource() == toggleColorblind.get(1)) {
//				if (toggleColorblind.get(1).getText() == "Disabled") {
//					toggleColorblind.get(1).setText("Enabled");
//					Settings.addBuffer("p2.toggleColorblind");
//
//				} else
//					toggleColorblind.get(1).setText("Disabled");
//			}
//
//			if (e.getSource() == toggleColorblind.get(2)) {
//				if (toggleColorblind.get(2).getText() == "Disabled") {
//					toggleColorblind.get(2).setText("Enabled");
//					Settings.addBuffer("p3.toggleColorblind");
//
//				} else
//					toggleColorblind.get(2).setText("Disabled");
//			}
//
//			if (e.getSource() == toggleColorblind.get(3)) {
//				if (toggleColorblind.get(3).getText() == "Disabled") {
//					toggleColorblind.get(3).setText("Enabled");
//					Settings.addBuffer("p4.toggleColorblind");
//
//				} else
//					toggleColorblind.get(3).setText("Disabled");
//			}

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

				int input = JOptionPane.showConfirmDialog(null, "Do you want to apply changes?", "Select an Option...",
						JOptionPane.YES_NO_OPTION);
				if (input == 0) {

					playerNameField.forEach(i -> Settings.addNameBuffer(i.getText()));
					Settings.applySettings();
					frame.dispose();
				}

			}

			if (e.getSource() == cancel) {
				int input = JOptionPane.showConfirmDialog(null, "Do you want to revert changes?", "Select an Option...",
						JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					playerNameField.clear();
					Settings.declineSettings();
					frame.dispose();
				}

			}
			;

		}
	};

}
