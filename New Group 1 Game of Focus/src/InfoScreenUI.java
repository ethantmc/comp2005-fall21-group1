import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InfoScreenUI extends JFrame {

	private JPanel contentPane;
	ImageIcon infoImage = new ImageIcon(getClass().getResource("/InfoUI.png"));

	public InfoScreenUI() {
		setBounds(100, 100, 603, 622);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 603, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 622, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel InfoImage = new JLabel("");
		InfoImage.setIcon(infoImage);
		GridBagConstraints gbc_InfoImage = new GridBagConstraints();
		gbc_InfoImage.insets = new Insets(0, 0, 5, 5);
		gbc_InfoImage.gridx = 1;
		gbc_InfoImage.gridy = 1;
		contentPane.add(InfoImage, gbc_InfoImage);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
