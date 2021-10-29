import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class SettingsUI extends JFrame implements MouseListener {
	private JPanel topPanel,centerPanel,bottomPanel;
	private GridSquare [][]gridsquares;
	private final Container contentPane;
	private JLabel topLabel;
	private JButton cancel, apply;
	private Component rigidArea;
	private Color []colors = {ColorUIResource.GREEN, Color.red, Color.blue, Color.YELLOW};

	public SettingsUI() {
		this.setLayout(new BorderLayout());

		// For cross platform performance.
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		}catch (Exception e) {
			e.printStackTrace();
		}

		this.setBounds(100, 100, 607, 607);
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());


		createButtons();
		createTop();
		createBottom();

		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



	public void createBottom(){
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));

		contentPane.add(bottomPanel,BorderLayout.SOUTH);
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bottomPanel.add(cancel);
		apply = new JButton("Apply");
		apply.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bottomPanel.add(apply);





	}

	private void createButtons(){
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2,2));

		for (int i = 0; i < 4; i++){
			PlayerLabel lbl = new PlayerLabel();
			lbl.setBackground(colors[i]);
			lbl.setBorder(BorderFactory.createLineBorder(Color.WHITE,4));
			lbl.addChangePlayerText("Player " + String.valueOf(i + 1));
			centerPanel.add(lbl);
			//TODO: missing a catch event for lbl.toggleColorBlindMode()
		}
		this.add(centerPanel, BorderLayout.CENTER);
	}

	public void createTop(){
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());

		contentPane.add(topPanel,BorderLayout.NORTH);
		topLabel =  new JLabel("           Settings");
		topLabel.setPreferredSize((new Dimension(350,100)));
		topLabel.setFont(new Font("Tahoma",Font.BOLD,35));
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		topLabel.setBackground(Color.cyan);
		topLabel.setVerticalAlignment(JLabel.CENTER);

		topPanel.add(topLabel);

		rigidArea = Box.createRigidArea(new Dimension(125, 20));
		topPanel.add(rigidArea);

	}



	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
