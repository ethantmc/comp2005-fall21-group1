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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BoardUI extends JPanel {

	// private JFrame frame;
	private ArrayList<Token> testReserves = new ArrayList<Token>();
	private boolean cond1, cond2, cond3, cond4;
	private Stack[][] stacks;
	private int x, y, num;
	private static Stack clicked = null, moveFrom = null, moveTo = null;
	private JPanel boardUIInstance = this;

	/**
	 * Create the application.
	 */
	public BoardUI() {
		setBackground(Color.WHITE);

		Turn.initiatePlayerTurn();

		setLayout(new GridLayout(8, 8, 20, 15));

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		if (GameState.isNewGame())
			Board.generateStacksAndTokens();
		Board.updateDominationPercentageForAllPlayers();
		stacks = Board.getStacks();
		// add stacks to the board
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
					// add stack to board panel
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
		// removing stack tokens for test purpose
		stacks[4][5].removeToken();
		stacks[4][2].removeToken();
		stacks[4][3].removeToken();
		stacks[2][5].removeToken();
		stacks[2][3].removeToken();
		Board.updateStacksDisplay();
	}

	public static Stack getClicked() {
		return clicked;
	};

	MouseAdapter MouseListener = new MouseAdapter() {
		@SuppressWarnings("deprecation")
		@Override
		public void mouseClicked(MouseEvent e) {

			Board.updateStacksDisplay();
			SwingUtilities.updateComponentTreeUI(boardUIInstance);
			clicked = (Stack) e.getSource();
			if (moveFrom == null) // does not execute if the player is about to make a move
			{
				Board.highlightClicked(clicked);
				// create popupMenu
				JPopupMenu popupMenu = new JPopupMenu();
				JMenuItem playfromReserves = new JMenuItem("Play from reserves");
				playfromReserves.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Move.makeAReserveMove(clicked);
					}
				});
				// hide playfromReserves item if player has zero reserved tokens
				if (Turn.getCurrentPlayer().getReserveCount() == 0) {
					playfromReserves.hide();
				}
				popupMenu.add(playfromReserves);

				ArrayList<JMenuItem> move = new ArrayList<JMenuItem>();
				// do not add more menu items if the stack is empty
				if (!(clicked.getStackOwner() == null)) {
					for (int i = 0; i < clicked.getStackSize(); i++) {
						move.add(new JMenuItem(String.valueOf(i + 1)));
						move.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
						move.get(i).addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								num = move.indexOf(e.getSource()) + 1;
								Board.highlightPossibleMoves(clicked.getXcoord(), clicked.getYcoord(), num);
								moveFrom = clicked;
							}
						});
						popupMenu.add(move.get(i));

					}
				}
				// show popupMenu
				popupMenu.show(e.getComponent(), e.getX(), e.getY());

			} else {
				if (Board.checkMoveValidity(moveFrom, clicked, num))// checks if the clicked stack is a valid stack to
																	// make the move
				{
					moveTo = clicked;
					Move.makeAMove(moveFrom, moveTo, num);
					// Move.makeAReserveMove(clicked);
					moveFrom = null;
					moveTo = null;
				}
			}

		}
	};

}
