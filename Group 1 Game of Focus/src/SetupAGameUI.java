import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SetupAGameUI {

	private static Color[] colors = { Color.decode("#4ECB71"), Color.decode("#FF5757"), Color.decode("#4590FF"),
			Color.decode("#FFCD1C") };
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupAGameUI window = new SetupAGameUI();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JFrame frame;
	private JButton startButton, loadButton;
	private ArrayList<JLabel> difficulty = new ArrayList<JLabel>();
	private ArrayList<DifficultyType> difficultyType = new ArrayList<DifficultyType>();
	private ArrayList<PlayerType> playerType = new ArrayList<PlayerType>();
	private ArrayList<JPanel> difficultyButtonPanel = new ArrayList<JPanel>();
	// private ArrayList<Player> playersBuffer = new ArrayList<Player>();
	private ArrayList<JToggleButton> toggleColorblind = new ArrayList<JToggleButton>();
	private ArrayList<Boolean> isColorblindEnabled = new ArrayList<Boolean>();
	private ArrayList<JPanel> playerSettingsPanel = new ArrayList<JPanel>();
	private ArrayList<JTextField> playerNameField = new ArrayList<JTextField>();
	private ArrayList<JButton> easyButton = new ArrayList<JButton>();
	private ArrayList<JButton> hardButton = new ArrayList<JButton>();
	private ArrayList<JButton> removePlayer = new ArrayList<JButton>();

	private ArrayList<JButton> addPlayer = new ArrayList<JButton>();

	// ActionListener to perform all the actions
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e)

		{

			if (toggleColorblind.contains(e.getSource())) {
				int i = toggleColorblind.indexOf(e.getSource());
				if (toggleColorblind.get(i).getText() == "Disabled") {
					toggleColorblind.get(i).setText("Enabled");
					isColorblindEnabled.set(i, true);
				} else {
					toggleColorblind.get(i).setText("Disabled");
					isColorblindEnabled.set(i, false);
				}

			}

			if (removePlayer.contains(e.getSource())) {

				int i = removePlayer.indexOf(e.getSource());

				playerNameField.get(i + 1).setText("CPU");

				// difficulty.get(i).setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_difficulty = new GridBagConstraints();
				gbc_difficulty.anchor = GridBagConstraints.WEST;
				gbc_difficulty.insets = new Insets(0, 0, 5, 5);
				gbc_difficulty.gridx = 1;
				gbc_difficulty.gridy = 4;
				playerSettingsPanel.get(i + 1).add(difficulty.get(i), gbc_difficulty);

				// Difficulty buttons

				GridBagConstraints gbc_difficultyButtonPanel = new GridBagConstraints();
				gbc_difficultyButtonPanel.insets = new Insets(0, 0, 5, 5);
				gbc_difficultyButtonPanel.fill = GridBagConstraints.BOTH;
				gbc_difficultyButtonPanel.gridx = 1;
				gbc_difficultyButtonPanel.gridy = 5;
				playerSettingsPanel.get(i + 1).add(difficultyButtonPanel.get(i), gbc_difficultyButtonPanel);
				difficultyButtonPanel.get(i).setLayout(new GridLayout(0, 2, 20, 0));
				difficultyButtonPanel.get(i).setBackground(colors[i + 1]);

				easyButton.get(i).addActionListener(actionListener);
				difficultyButtonPanel.get(i).add(easyButton.get(i));

				hardButton.get(i).addActionListener(actionListener);
				difficultyButtonPanel.get(i).add(hardButton.get(i));
				//////////////////////////

				addPlayer.get(i).addActionListener(actionListener);
				GridBagConstraints gbc_removePlayer = new GridBagConstraints();
				gbc_removePlayer.insets = new Insets(0, 0, 5, 5);
				gbc_removePlayer.gridx = 1;
				gbc_removePlayer.gridy = 7;
				playerSettingsPanel.get(i + 1).remove(removePlayer.get(i));
				playerSettingsPanel.get(i + 1).add(addPlayer.get(i), gbc_removePlayer);

				SwingUtilities.updateComponentTreeUI(frame);

			}

			if (addPlayer.contains(e.getSource())) {

				int i = addPlayer.indexOf(e.getSource());

				playerNameField.get(i + 1).setText("Player " + String.valueOf(i + 2));

				playerSettingsPanel.get(i + 1).remove(difficulty.get(i));

				// Difficulty buttons
				playerSettingsPanel.get(i + 1).remove(difficultyButtonPanel.get(i));
				//////////////////////////

				removePlayer.get(i).addActionListener(actionListener);
				GridBagConstraints gbc_removePlayer = new GridBagConstraints();
				gbc_removePlayer.insets = new Insets(0, 0, 5, 5);
				gbc_removePlayer.gridx = 1;
				gbc_removePlayer.gridy = 7;
				playerSettingsPanel.get(i + 1).remove(addPlayer.get(i));
				playerSettingsPanel.get(i + 1).add(removePlayer.get(i), gbc_removePlayer);

				SwingUtilities.updateComponentTreeUI(frame);

			}

			if (easyButton.contains(e.getSource())) {

				int i = easyButton.indexOf(e.getSource());

				difficultyType.set(i, DifficultyType.EASY);
				playerType.set(i, PlayerType.CPU);

			}

			if (hardButton.contains(e.getSource())) {

				int i = hardButton.indexOf(e.getSource());

				difficultyType.set(i, DifficultyType.HARD);
				playerType.set(i, PlayerType.CPU);

			}

			if (e.getSource() == startButton) {

				int input = JOptionPane.showConfirmDialog(null, "Do you want to start the game", "Select an Option...",
						JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					SetupAGame.createAPlayer(playerNameField.get(0).getText(), PlayerType.HUMAN,
							isColorblindEnabled.get(0), null);
					for (int i = 0; i < 3; i++) {
						SetupAGame.createAPlayer(playerNameField.get(i + 1).getText(), playerType.get(i),
								isColorblindEnabled.get(i + 1), difficultyType.get(i));
					}
					GameState.setNewGame(true);
					SetupAGame.startGame();
					frame.dispose();
				}

			}

			if (e.getSource() == loadButton) {
				int input = JOptionPane.showConfirmDialog(null, "Do you want to revert changes?", "Select an Option...",
						JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					GameState.loadGame();
					SetupAGame.startGame();
					frame.dispose();
				}

			}

		}
	};

	/**
	 * Create the application.
	 */
	public SetupAGameUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 400, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel headingPanel = new JPanel();
		headingPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_headingPanel = new GridBagConstraints();
		gbc_headingPanel.gridwidth = 3;
		gbc_headingPanel.insets = new Insets(0, 0, 5, 0);
		gbc_headingPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_headingPanel.gridx = 0;
		gbc_headingPanel.gridy = 1;
		frame.getContentPane().add(headingPanel, gbc_headingPanel);

		JLabel lblSetupAGame = new JLabel("Focus");
		lblSetupAGame.setVerticalAlignment(SwingConstants.CENTER);
		lblSetupAGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetupAGame.setForeground(Color.WHITE);
		lblSetupAGame.setFont(new Font("Tahoma", Font.BOLD, 35));
		headingPanel.add(lblSetupAGame);

		JPanel allplayerSettingsPanel = new JPanel();
		allplayerSettingsPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_allplayerSettingsPanel = new GridBagConstraints();
		gbc_allplayerSettingsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_allplayerSettingsPanel.fill = GridBagConstraints.BOTH;
		gbc_allplayerSettingsPanel.gridx = 1;
		gbc_allplayerSettingsPanel.gridy = 3;
		frame.getContentPane().add(allplayerSettingsPanel, gbc_allplayerSettingsPanel);
		allplayerSettingsPanel.setLayout(new GridLayout(2, 2, 20, 20));
		for (int i = 0; i < 4; i++) {
			//			JPanel panel = new JPanel();
			//			frame.getContentPane().add(panel, BorderLayout.CENTER);

			// Create new Player
			// playersBuffer.add(new Player(null, null, null));
			//			playersBuffer.add(new Player("Player " + String.valueOf(i + 1), PlayerType.HUMAN, false));
			//			playersBuffer.get(i).setColor(colors[i]);

			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
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
			playerNameField.get(i).setText("Player " + String.valueOf(i + 1));

			playerSettingsPanel.get(i).setBackground(colors[i]);

			// add playerNameField
			GridBagConstraints gbc_namefield = new GridBagConstraints();
			gbc_namefield.insets = new Insets(0, 0, 5, 5);
			gbc_namefield.gridx = 1;
			gbc_namefield.gridy = 2;
			playerSettingsPanel.get(i).add(playerNameField.get(i), gbc_namefield);
			// playerSettingsPanel.get(i).remove(playerNameField.get(i));

			//
			isColorblindEnabled.add(false);
			if (i > 0) {
				// create buttons to maintain index
				easyButton.add(new JButton("Easy"));
				hardButton.add(new JButton("Hard"));
				addPlayer.add(new JButton("Add Player"));
				difficulty.add(new JLabel("Choose Difficulty:"));
				difficultyButtonPanel.add(new JPanel());
				playerType.add(PlayerType.HUMAN);
				difficultyType.add(DifficultyType.EASY);

				removePlayer.add(new JButton("Remove Player"));
				removePlayer.get(i - 1).addActionListener(actionListener);
				GridBagConstraints gbc_removePlayer = new GridBagConstraints();
				gbc_removePlayer.insets = new Insets(0, 0, 5, 5);
				gbc_removePlayer.gridx = 1;
				gbc_removePlayer.gridy = 7;
				playerSettingsPanel.get(i).add(removePlayer.get(i - 1), gbc_removePlayer);

			}

			// add colourDeficiencyMode label
			JLabel colourDeficiencyMode = new JLabel("Colour Deficiency Mode:");
			colourDeficiencyMode.setVerticalAlignment(SwingConstants.TOP);
			// JLabel color = new JLabel("New label");
			GridBagConstraints gbc_color = new GridBagConstraints();
			gbc_color.insets = new Insets(0, 0, 5, 5);
			gbc_color.gridx = 1;
			gbc_color.gridy = 9;
			playerSettingsPanel.get(i).add(colourDeficiencyMode, gbc_color);

			// add toggleColorblind button
			toggleColorblind.add(new JToggleButton("Disabled"));
			toggleColorblind.get(i).addActionListener(actionListener);
			//			if (playersBuffer.get(i).getColorblindSetting()) {
			//				toggleColorblind.get(i).setText("Enabled");
			//			}

			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 1;
			gbc_button.gridy = 10;
			playerSettingsPanel.get(i).add(toggleColorblind.get(i), gbc_button);

			allplayerSettingsPanel.add(playerSettingsPanel.get(i));
			//			if (i % 2 == 0) {
			//				Component verticalStrut = Box.createVerticalStrut(5);
			//				allplayerSettingsPanel.add(verticalStrut);
			//			}
		}

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_buttonsPanel = new GridBagConstraints();
		gbc_buttonsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_buttonsPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonsPanel.gridx = 1;
		gbc_buttonsPanel.gridy = 5;
		frame.getContentPane().add(buttonsPanel, gbc_buttonsPanel);
		buttonsPanel.setLayout(new GridLayout(0, 2, 20, 0));

		startButton = new JButton("Start");
		startButton.addActionListener(actionListener);
		buttonsPanel.add(startButton);

		loadButton = new JButton("Load");
		loadButton.addActionListener(actionListener);
		buttonsPanel.add(loadButton);
	}
}
