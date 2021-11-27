import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class GameUI extends JFrame {

	private JPanel contentPane;
	private ArrayList<JPanel> playerCardPanel = new ArrayList<JPanel>();
	private ArrayList<JPanel> playerInfoPanel = new ArrayList<JPanel>();
	private ArrayList<JPanel> playerStatsPanel = new ArrayList<JPanel>();
	private ArrayList<JPanel> paddingPanel = new ArrayList<JPanel>();
	private ArrayList<JLabel> playerNameLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerDominationLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerReservesLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerTokensLeftLabel = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerCapturedLabel = new ArrayList<JLabel>();
	private static BoardUI boardUI;
	ImageIcon infoIcon = new ImageIcon(getClass().getResource("/InfoIcon.png"));
	JButton newGameButton, settingsButton, saveButton, loadButton;
	// private ArrayList<FlowLayout> panelFlowLayout = new ArrayList<FlowLayout>();
	private static JLabel whosTurn = new JLabel();

	public GameUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 1050);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		boardUI = new BoardUI();
		contentPane.add(boardUI, BorderLayout.CENTER);

		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(Color.WHITE);
		contentPane.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		contentPane.add(northPanel, BorderLayout.NORTH);

		// create player labels
		for (int i = 0; i < 4; i++) {
			playerCardPanel.add(new JPanel());
			if (Turn.getCurrentPlayer().getColorblindSetting())
				playerCardPanel.get(i).setBackground(Color.BLACK);
			else
				playerCardPanel.get(i).setBackground(SetupAGame.getPlayers().get(i).getColor());
			playerInfoPanel.add(new JPanel());
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 40, 100, 40, 0 };
			gbl_panel.rowHeights = new int[] { 20, 17, 20, 0 };
			gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			// panel.setLayout(gbl_panel);
			playerCardPanel.get(i).setLayout(gbl_panel);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 1;
			// panel.add(btnNewButton, gbc_btnNewButton);
			playerCardPanel.get(i).add(playerInfoPanel.get(i), gbc_btnNewButton);
			playerNameLabel.add(new JLabel(SetupAGame.getPlayers().get(i).getName()));
			playerInfoPanel.get(i).add(playerNameLabel.get(i));
			playerStatsPanel.add(new JPanel());
			playerStatsPanel.get(i).setLayout(new GridLayout(2, 2, 10, 5));
			playerInfoPanel.get(i).add(playerStatsPanel.get(i));
			playerDominationLabel.add(
					new JLabel("Domination: " + String.valueOf(SetupAGame.getPlayers().get(i).getDomination()) + "%"));
			playerStatsPanel.get(i).add(playerDominationLabel.get(i));
			playerReservesLabel
					.add(new JLabel("Reserves: " + String.valueOf(SetupAGame.getPlayers().get(i).getReserveCount())));
			playerStatsPanel.get(i).add(playerReservesLabel.get(i));
			playerTokensLeftLabel
					.add(new JLabel("Tokens Left: " + String.valueOf(SetupAGame.getPlayers().get(i).getTokensLeft())));
			playerStatsPanel.get(i).add(playerTokensLeftLabel.get(i));
			playerCapturedLabel
					.add(new JLabel("Captured: " + String.valueOf(SetupAGame.getPlayers().get(i).getCapturedCount())));
			playerStatsPanel.get(i).add(playerCapturedLabel.get(i));
			paddingPanel.add(new JPanel());
			paddingPanel.get(i).setBackground(Color.WHITE);
		}

		JPanel topBar = new JPanel();
		topBar.setBackground(Color.DARK_GRAY);
		northPanel.add(topBar);
		GridBagLayout gbl_topBar = new GridBagLayout();
		gbl_topBar.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_topBar.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_topBar.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_topBar.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		topBar.setLayout(gbl_topBar);

		JPanel saveLoadpanel = new JPanel();
		saveLoadpanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_saveLoadpanel = new GridBagConstraints();
		gbc_saveLoadpanel.insets = new Insets(0, 0, 5, 5);
		gbc_saveLoadpanel.fill = GridBagConstraints.BOTH;
		gbc_saveLoadpanel.gridx = 1;
		gbc_saveLoadpanel.gridy = 1;
		topBar.add(saveLoadpanel, gbc_saveLoadpanel);
		saveLoadpanel.setLayout(new GridLayout(2, 1, 20, 10));

		saveButton = new JButton("Save");
		saveButton.addActionListener(actionListener);
		saveLoadpanel.add(saveButton);

		loadButton = new JButton("Load");
		loadButton.addActionListener(actionListener);
		saveLoadpanel.add(loadButton);
		whosTurn.setHorizontalAlignment(SwingConstants.CENTER);
		whosTurn.setFont(new Font("Roboto Black", Font.PLAIN, 40));
		whosTurn.setForeground(Color.WHITE);
		whosTurn.setText(Turn.getCurrentPlayer().getName() + "'s Turn");
		GridBagConstraints gbc_whosTurn = new GridBagConstraints();
		gbc_whosTurn.insets = new Insets(0, 0, 5, 5);
		gbc_whosTurn.gridx = 3;
		gbc_whosTurn.gridy = 1;
		topBar.add(whosTurn, gbc_whosTurn);

		JPanel newGameSettingsPanel = new JPanel();
		newGameSettingsPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_newGameSettingsPanel = new GridBagConstraints();
		gbc_newGameSettingsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_newGameSettingsPanel.fill = GridBagConstraints.BOTH;
		gbc_newGameSettingsPanel.gridx = 5;
		gbc_newGameSettingsPanel.gridy = 1;
		topBar.add(newGameSettingsPanel, gbc_newGameSettingsPanel);
		newGameSettingsPanel.setLayout(new GridLayout(2, 1, 20, 10));

		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(actionListener);
		newGameSettingsPanel.add(newGameButton);

		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(actionListener);
		newGameSettingsPanel.add(settingsButton);

		JButton infoButton = new JButton("");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoScreenUI infoScreen = new InfoScreenUI();
			}
		});
		infoButton.setIcon(infoIcon);
		GridBagConstraints gbc_infoButton = new GridBagConstraints();
		gbc_infoButton.insets = new Insets(0, 0, 5, 5);
		gbc_infoButton.gridx = 6;
		gbc_infoButton.gridy = 1;
		topBar.add(infoButton, gbc_infoButton);

		// Add player labels to UI
		playerInfoPanel.get(1).setLayout(new GridLayout(2, 1, 0, 0));
		playerInfoPanel.get(3).setLayout(new GridLayout(2, 1, 0, 0));
		westPanel.add(paddingPanel.get(0));
		eastPanel.add(paddingPanel.get(1));
		northPanel.setLayout(new GridLayout(2, 2, 0, 0));
		JPanel forPlayerCard = new JPanel();
		forPlayerCard.add(playerCardPanel.get(0));
		forPlayerCard.setBackground(Color.WHITE);
		northPanel.add(forPlayerCard);
		eastPanel.add(playerCardPanel.get(1));
		southPanel.add(playerCardPanel.get(2));
		westPanel.add(playerCardPanel.get(3));
		eastPanel.add(paddingPanel.get(2));
		westPanel.add(paddingPanel.get(3));
	}

	public void updateStats() {
		whosTurn.setText(Turn.getCurrentPlayer().getName() + "'s Turn");
		for (int i = 0; i < 4; i++) {
			playerDominationLabel.get(i)
					.setText("Domination: " + String.valueOf(SetupAGame.getPlayers().get(i).getDomination()) + "%");
			playerReservesLabel.get(i)
					.setText("Reserves: " + String.valueOf(SetupAGame.getPlayers().get(i).getReserveCount()));
			playerCapturedLabel.get(i)
					.setText("Captured: " + String.valueOf(SetupAGame.getPlayers().get(i).getCapturedCount()));
			playerTokensLeftLabel.get(i)
					.setText("Tokens Left: " + String.valueOf(SetupAGame.getPlayers().get(i).getTokensLeft()));
			playerNameLabel.get(i).setText(SetupAGame.getPlayers().get(i).getName());
			if (Turn.getCurrentPlayer().getColorblindSetting())
				playerCardPanel.get(i).setBackground(Color.BLACK);
			else
				playerCardPanel.get(i).setBackground(SetupAGame.getPlayers().get(i).getColor());
		}

	}

	public void initializeBoardUI() {
		boardUI.initialize();
	}

	// public void
	public static void setWinner(Player player) {
		whosTurn.setText(player.getName() + "'s the Winner!");
	}

	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e)

		{
			if (e.getSource() == newGameButton) {
				int input = JOptionPane.showConfirmDialog(null, "Do you want to start a new game?",
						"Your game progress will be lost.", JOptionPane.YES_NO_OPTION);
				if (input == 0) {

					GameState.setNewGame(true);
					dispose();
					SetupAGame.startGame();
				}

			}
			if (e.getSource() == settingsButton) {
				SettingsUI settings = new SettingsUI();
			}
			if (e.getSource() == saveButton) {
				GameState.saveGame();
			}
			if (e.getSource() == loadButton) {
				GameState.loadGame();
				dispose();
				SetupAGame.startGame();
			}
		}
	};
}
