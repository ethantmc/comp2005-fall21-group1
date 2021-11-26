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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Dimension;

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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel fullscreenPanel = new JPanel();
		GridBagConstraints gbc_fullscreenPanel = new GridBagConstraints();
		gbc_fullscreenPanel.insets = new Insets(0, 0, 5, 0);
		gbc_fullscreenPanel.fill = GridBagConstraints.BOTH;
		gbc_fullscreenPanel.gridx = 0;
		gbc_fullscreenPanel.gridy = 1;
		frame.getContentPane().add(fullscreenPanel, gbc_fullscreenPanel);

		JButton btnNewButton = new JButton("New button");
		fullscreenPanel.add(btnNewButton);

		JPanel cancelApplyPanel = new JPanel();
		GridBagConstraints gbc_cancelApplyPanel = new GridBagConstraints();
		gbc_cancelApplyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cancelApplyPanel.fill = GridBagConstraints.BOTH;
		gbc_cancelApplyPanel.gridx = 0;
		gbc_cancelApplyPanel.gridy = 3;
		frame.getContentPane().add(cancelApplyPanel, gbc_cancelApplyPanel);

		JButton btnNewButton_1 = new JButton("New button");
		cancelApplyPanel.add(btnNewButton_1);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
