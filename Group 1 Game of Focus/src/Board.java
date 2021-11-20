import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board {
//TODO constructor and attributes for Board

	private static ArrayList<JPanel> notPartofBoard = new ArrayList<JPanel>();
	private static Player player;
	private static boolean cond1, cond2, cond3, cond4, cond1p1, cond2p1, cond1p2, cond2p2, cond1p3, cond2p3;
	private static Stack[][] stacks;
	private static int x, y;

	public static void generateStacksAndTokens() {
		stacks = new Stack[8][8];
		int j = 0;// to iterate notPartofBoard

		for (x = 0; x < 8; x++) {
			for (y = 0; y < 8; y++) {
				// Conditions for invalid token positions
				cond1 = (x == 0 && (y == 0 || y == 1 || y == 6 || y == 7));
				cond2 = (x == 7 && (y == 0 || y == 1 || y == 6 || y == 7));
				cond3 = (y == 7 && (x == 1 || x == 6));
				cond4 = (y == 0 && (x == 1 || x == 6));

				// Conditions for Player 1's token placement
				cond1p1 = (y < 4 && (x == 0 || x == 2));
				cond2p1 = (x > 3 && (y == 1 || y == 3));
				// Conditions for Player 2's token placement
				cond1p2 = (y < 4 && (x == 1 || x == 3));
				cond2p2 = (x < 4 && (y == 5 || y == 7));
				// Conditions for Player 3's token placement
				cond1p3 = (y > 3 && (x == 5 || x == 7));
				cond2p3 = (x < 4 && (y == 4 || y == 6));

				// When position is invalid
				if (cond1 || cond2 || cond3 || cond4) {
					notPartofBoard.add(new JPanel());
					notPartofBoard.get(j).setBackground(Color.WHITE);
					stacks[x][y] = new Stack(x, y);
					stacks[x][y].setSize(20, 20);
					stacks[x][y].add(notPartofBoard.get(j), BorderLayout.CENTER);
					j++;
				}

				// When position is valid
				else {

					// Switch player to generate tokens of the right amount at the right position.
					if (cond1p1 || cond2p1)
						player = SetupAGame.getPlayers().get(0);
					else if (cond1p2 || cond2p2)
						player = SetupAGame.getPlayers().get(1);
					else if (cond1p3 || cond2p3)
						player = SetupAGame.getPlayers().get(2);
					else
						player = SetupAGame.getPlayers().get(3);

					// Create new stacks and add token
					stacks[x][y] = new Stack(x, y);
					stacks[x][y].stackToken(new Token(player));
				}

			}
		}
	}

	public static Stack[][] getStacks() {
		return stacks;
	}

	public static void setStacks(Stack[][] stacks) {
		Board.stacks = stacks;
	}

	public static void updateStacksDisplay() {
		for (x = 0; x < 8; x++) {
			for (y = 0; y < 8; y++) {
				if (!stacks[x][y].getStackContents().isEmpty()) {
					stacks[x][y].updateStackDisplay();
				}
			}
		}

	}

	public void getTokensLeftForEachPlayer() {

//TODO Method Stub
	}

	public void getDominationPercentageForEachPlayer() {
//TODO Method Stub
	}

	public void iiterateOverBoard() {
		// TODO Method Stub
	}

	public void calculatePossibleMoves(int coordinateX, int coordinateY) {

	}
//TODO - IterateOverBoard() code for duplication in the above. After discussion, code duplication deemed preferable to IterateOverBoard() being a real method.
}