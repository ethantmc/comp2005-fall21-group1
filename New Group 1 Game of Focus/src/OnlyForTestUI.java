import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OnlyForTestUI {

	private JFrame frame;
	private JTextField namefield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlyForTestUI window = new OnlyForTestUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OnlyForTestUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 833, 697);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel name = new JLabel("New label");
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.anchor = GridBagConstraints.WEST;
		gbc_name.insets = new Insets(0, 0, 5, 5);
		gbc_name.gridx = 1;
		gbc_name.gridy = 1;
		panel.add(name, gbc_name);

		namefield = new JTextField();
		GridBagConstraints gbc_namefield = new GridBagConstraints();
		gbc_namefield.insets = new Insets(0, 0, 5, 5);
		gbc_namefield.fill = GridBagConstraints.HORIZONTAL;
		gbc_namefield.gridx = 1;
		gbc_namefield.gridy = 2;
		panel.add(namefield, gbc_namefield);
		namefield.setColumns(10);

		JLabel difficulty = new JLabel("Choose Difficulty:");
		difficulty.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_difficulty = new GridBagConstraints();
		gbc_difficulty.anchor = GridBagConstraints.WEST;
		gbc_difficulty.insets = new Insets(0, 0, 5, 5);
		gbc_difficulty.gridx = 1;
		gbc_difficulty.gridy = 4;
		panel.add(difficulty, gbc_difficulty);

		JPanel difficultyButtonPanel = new JPanel();
		GridBagConstraints gbc_difficultyButtonPanel = new GridBagConstraints();
		gbc_difficultyButtonPanel.insets = new Insets(0, 0, 5, 5);
		gbc_difficultyButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_difficultyButtonPanel.gridx = 1;
		gbc_difficultyButtonPanel.gridy = 5;
		panel.add(difficultyButtonPanel, gbc_difficultyButtonPanel);
		difficultyButtonPanel.setLayout(new GridLayout(0, 2, 20, 0));

		JButton easyButton = new JButton("Easy");
		easyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		difficultyButtonPanel.add(easyButton);

		JButton hardButton = new JButton("Hard");
		difficultyButtonPanel.add(hardButton);

		JButton removePlayer = new JButton("Remove Player");
		GridBagConstraints gbc_removePlayer = new GridBagConstraints();
		gbc_removePlayer.insets = new Insets(0, 0, 5, 5);
		gbc_removePlayer.gridx = 1;
		gbc_removePlayer.gridy = 7;
		panel.add(removePlayer, gbc_removePlayer);

		JLabel color = new JLabel("New label");
		GridBagConstraints gbc_color = new GridBagConstraints();
		gbc_color.insets = new Insets(0, 0, 5, 5);
		gbc_color.gridx = 1;
		gbc_color.gridy = 9;
		panel.add(color, gbc_color);

		JButton colorToggle = new JButton("New button");
		GridBagConstraints gbc_colorToggle = new GridBagConstraints();
		gbc_colorToggle.insets = new Insets(0, 0, 5, 5);
		gbc_colorToggle.gridx = 1;
		gbc_colorToggle.gridy = 10;
		panel.add(colorToggle, gbc_colorToggle);
	}

}
