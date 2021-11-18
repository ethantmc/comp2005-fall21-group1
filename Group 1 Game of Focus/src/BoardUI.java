import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class BoardUI extends JPanel {

	// private JFrame frame;
	private ArrayList<Token> testReserves = new ArrayList<Token>();
	private boolean cond1, cond2, cond3, cond4;
	private Stack[][] stacks;
	private int x, y;
	private static Stack clicked = null;

	/**
	 * Create the application.
	 */
	public BoardUI() {
		setBackground(Color.WHITE);

		Turn.initiatePlayerTurn();

		setLayout(new GridLayout(8, 8, 5, 5));

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// this.setBounds(100, 100, 900, 600);
		// this.setLayout(new GridLayout(8, 8, 5, 5));

		Board.generateStacksAndTokens();
		stacks = Board.getStacks();

		for (x = 0; x < 8; x++) {
			for (y = 0; y < 8; y++) {
				// Conditions for invalid token positions
				cond1 = (x == 0 && (y == 0 || y == 1 || y == 6 || y == 7));
				cond2 = (x == 7 && (y == 0 || y == 1 || y == 6 || y == 7));
				cond3 = (y == 7 && (x == 1 || x == 6));
				cond4 = (y == 0 && (x == 1 || x == 6));

				// When position is invalid
				if (cond1 || cond2 || cond3 || cond4) {
					this.add(stacks[x][y]);
				}

				// When position is valid
				else {
					stacks[x][y].addMouseListener(MouseListener);
					// add stack to frame
					this.add(stacks[x][y]);
				}

			}
		}

		// Adding reserve tokens for test purpose
		int k = 0;
		for (int p = 0; p < 4; p++) {
			for (int q = 0; q < 8; q++) {
				testReserves.add(new Token(SetupAGame.getPlayers().get(p)));
				SetupAGame.getPlayers().get(p).reserveAToken(testReserves.get(k));
				k++;
			}

		}

	}

	public static Stack getClicked() {
		return clicked;
	};

	MouseAdapter MouseListener = new MouseAdapter() {
		@SuppressWarnings("deprecation")
		@Override
		public void mouseClicked(MouseEvent e) {

			clicked = (Stack) e.getSource();
			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem playfromReserves = new JMenuItem("Play from reserves");
			playfromReserves.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Move.makeAReserveMove(clicked);
				}
			});
			if (Turn.getCurrentPlayer().getReserveCount() == 0) {
				playfromReserves.hide();
			}
			popupMenu.add(playfromReserves);

			JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
			popupMenu.add(mntmNewMenuItem_2);

			JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
			popupMenu.add(mntmNewMenuItem_3);

			JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
			popupMenu.add(mntmNewMenuItem);
			popupMenu.show(e.getComponent(), e.getX(), e.getY());

		}
	};

}
