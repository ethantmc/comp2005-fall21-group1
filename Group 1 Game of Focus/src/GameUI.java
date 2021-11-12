import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameUI extends JFrame {

	private JPanel contentPane;
	ImageIcon p1Card = new ImageIcon(getClass().getResource("/Player 1 Card.png"));
	ImageIcon p2Card = new ImageIcon(getClass().getResource("/Player 2 Card.png"));
	ImageIcon p3Card = new ImageIcon(getClass().getResource("/Player 3 Card.png"));
	ImageIcon p4Card = new ImageIcon(getClass().getResource("/Player 4 Card.png"));
	JLabel p1Reserves;
	
	/**
	 * Create the frame.
	 */
	public GameUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//JPanel BoardUI = new JPanel();
		BoardUI window = new BoardUI();
		contentPane.add(window, BorderLayout.CENTER);
		
		JPanel eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		eastPanel.add(panel_2);
		
		JPanel Player1Panel_1_1 = new JPanel();
		Player1Panel_1_1.setBackground(Color.GREEN);
		eastPanel.add(Player1Panel_1_1);
		Player1Panel_1_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel Player1StatsPanel_1_1 = new JPanel();
		Player1Panel_1_1.add(Player1StatsPanel_1_1);
		Player1StatsPanel_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel Player1Name_1_1 = new JLabel("New label");
		Player1StatsPanel_1_1.add(Player1Name_1_1, BorderLayout.NORTH);
		
		JPanel Player1Stats_1_1 = new JPanel();
		Player1StatsPanel_1_1.add(Player1Stats_1_1, BorderLayout.SOUTH);
		Player1Stats_1_1.setLayout(new GridLayout(2, 2, 10, 5));
		
		JLabel p1Domination_1_1 = new JLabel("Domination: ");
		Player1Stats_1_1.add(p1Domination_1_1);
		
		JLabel p1Reserves_1_1 = new JLabel("Reserves: ");
		Player1Stats_1_1.add(p1Reserves_1_1);
		
		JLabel p1TokensLeft_1_1 = new JLabel("Tokens Left: ");
		Player1Stats_1_1.add(p1TokensLeft_1_1);
		
		JLabel p1Captured_1_1 = new JLabel("Captured: ");
		Player1Stats_1_1.add(p1Captured_1_1);
		
		JPanel panel_1_1 = new JPanel();
		eastPanel.add(panel_1_1);
		
		JPanel westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		westPanel.add(panel);
		
		JPanel Player1Panel_1 = new JPanel();
		Player1Panel_1.setBackground(Color.GREEN);
		Player1Panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel Player1StatsPanel_1 = new JPanel();
		Player1Panel_1.add(Player1StatsPanel_1);
		Player1StatsPanel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel Player1Name_1 = new JLabel("New label");
		Player1StatsPanel_1.add(Player1Name_1, BorderLayout.NORTH);
		
		JPanel Player1Stats_1 = new JPanel();
		Player1StatsPanel_1.add(Player1Stats_1, BorderLayout.SOUTH);
		Player1Stats_1.setLayout(new GridLayout(2, 2, 10, 5));
		
		JLabel p1Domination_1 = new JLabel("Domination: ");
		Player1Stats_1.add(p1Domination_1);
		
		JLabel p1Reserves_1 = new JLabel("Reserves: ");
		Player1Stats_1.add(p1Reserves_1);
		
		JLabel p1TokensLeft_1 = new JLabel("Tokens Left: ");
		Player1Stats_1.add(p1TokensLeft_1);
		
		JLabel p1Captured_1 = new JLabel("Captured: ");
		Player1Stats_1.add(p1Captured_1);
		westPanel.add(Player1Panel_1);
		
		JPanel panel_1 = new JPanel();
		westPanel.add(panel_1);
		
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		JPanel Player3Panel = new JPanel();
		Player3Panel.setBackground(Color.BLUE);
		FlowLayout flowLayout = (FlowLayout) Player3Panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(20);
		southPanel.add(Player3Panel);
		
		JPanel Player3StatsPanel = new JPanel();
		Player3Panel.add(Player3StatsPanel);
		
		JLabel Player3Name = new JLabel("New label");
		Player3StatsPanel.add(Player3Name);
		
		JPanel Player3Stats = new JPanel();
		Player3StatsPanel.add(Player3Stats);
		Player3Stats.setLayout(new GridLayout(2, 2, 10, 5));
		
		JLabel p3Domination = new JLabel("Domination: ");
		Player3Stats.add(p3Domination);
		
		JLabel p3Reserves = new JLabel("Reserves: ");
		Player3Stats.add(p3Reserves);
		
		JLabel p3TokensLeft = new JLabel("Tokens Left: ");
		Player3Stats.add(p3TokensLeft);
		
		JLabel p3Captured = new JLabel("Captured: ");
		Player3Stats.add(p3Captured);
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		JPanel Player1Panel = new JPanel();
		Player1Panel.setBackground(Color.GREEN);
		FlowLayout flowLayout_1 = (FlowLayout) Player1Panel.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(20);
		northPanel.add(Player1Panel);
		
		JPanel Player1StatsPanel = new JPanel();
		Player1Panel.add(Player1StatsPanel);
		
		JLabel Player1Name = new JLabel("New label");
		Player1StatsPanel.add(Player1Name);
		
		JPanel Player1Stats = new JPanel();
		Player1StatsPanel.add(Player1Stats);
		Player1Stats.setLayout(new GridLayout(2, 2, 10, 5));
		
		JLabel p1Domination = new JLabel("Domination: ");
		Player1Stats.add(p1Domination);
		
		p1Reserves = new JLabel("Reserves:" + String.valueOf(Driver.getPlayers().get(0).getReserveCount()));
		Player1Stats.add(p1Reserves);
		
		JLabel p1TokensLeft = new JLabel("Tokens Left: ");
		Player1Stats.add(p1TokensLeft);
		
		JLabel p1Captured = new JLabel("Captured: ");
		Player1Stats.add(p1Captured);
	}
	
	public void updateStats()
	{
		p1Reserves.setText("Reserves:" + String.valueOf(Driver.getPlayers().get(0).getReserveCount()));
	}
/*	
    //... more code
     */
	
	//ActionListener to perform all the actions
//	ActionListener actionListener = new ActionListener() {
//		public void actionPerformed(ActionEvent e) 
//
//		{
//
//			if (e.getSource() == p1PlayfromReserves) 
//			{
//				Move.makeAReserveMove(BoardUI.getClicked());
//			};
//
//
//		}
//	};

}
