import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

public class Board {
	//TODO constructor and attributes for Board

	private static ArrayList<JPanel> notPartofBoard = new ArrayList<JPanel>();
	private static Player player;
	private static boolean cond1, cond2, cond3, cond4, cond1p1, cond2p1, cond1p2, cond2p2, cond1p3, cond2p3;
	private static Stack[][] stacks;
	private static int x = 0, y = 0;

	public static boolean checkMoveValidity(Stack moveFrom, Stack clicked, int num)
	{
		cond1=((clicked.getXcoord() == moveFrom.getXcoord())
				&& (((clicked.getYcoord()>moveFrom.getYcoord()) && (clicked.getYcoord()<=(moveFrom.getYcoord()+num)))
						|| ((clicked.getYcoord()<moveFrom.getYcoord()) && (clicked.getYcoord()>=(moveFrom.getYcoord()-num)))));
		cond2=((clicked.getYcoord() == moveFrom.getYcoord())
				&& (((clicked.getXcoord()>moveFrom.getXcoord()) && (clicked.getXcoord()<=(moveFrom.getXcoord()+num)))
						|| ((clicked.getXcoord()<moveFrom.getXcoord()) && (clicked.getXcoord()>=(moveFrom.getXcoord()-num)))));
		if(cond1 || cond2) {
			return true;
		} else {
			return false;
		}

	}

	public static void generateStacksAndTokens() {
		stacks = new Stack[8][8];
		int j = 0;// to iterate notPartofBoard

		for (x = 0; x < 8; x++) {
			for (y = 0; y < 8; y++) {
				// Conditions for invalid token positions
				cond1 = ((x == 0) && ((y == 0) || (y == 1) || (y == 6) || (y == 7)));
				cond2 = ((x == 7) && ((y == 0) || (y == 1) || (y == 6) || (y == 7)));
				cond3 = ((y == 7) && ((x == 1) || (x == 6)));
				cond4 = ((y == 0) && ((x == 1) || (x == 6)));

				// Conditions for Player 1's token placement
				cond1p1 = ((y < 4) && ((x == 0) || (x == 2)));
				cond2p1 = ((x > 3) && ((y == 1) || (y == 3)));
				// Conditions for Player 2's token placement
				cond1p2 = ((y < 4) && ((x == 1) || (x == 3)));
				cond2p2 = ((x < 4) && ((y == 5) || (y == 7)));
				// Conditions for Player 3's token placement
				cond1p3 = ((y > 3) && ((x == 5) || (x == 7)));
				cond2p3 = ((x < 4) && ((y == 4) || (y == 6)));

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
					if (cond1p1 || cond2p1) {
						player = SetupAGame.getPlayers().get(0);
					} else if (cond1p2 || cond2p2) {
						player = SetupAGame.getPlayers().get(1);
					} else if (cond1p3 || cond2p3) {
						player = SetupAGame.getPlayers().get(2);
					} else {
						player = SetupAGame.getPlayers().get(3);
					}

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

	public static void getTokensLeftForEachPlayer() {

		//TODO Method Stub
	}

	public static Stack getValidSpace() {
		//Method for selecting and returning a valid space's stack (which may be empty)
		//only ensures a random space actually exists, NOT that it's in range.
		int x;
		int y;
		x = ThreadLocalRandom.current().nextInt(0, 8); //[0,7]
		y = ThreadLocalRandom.current().nextInt(0, 8); //[0,7]
		//Use these numbers to select a space
		//TODO: Galib, add conditionals for the invalid spaces here, please!
		return stacks[x][y]; 
	}

	public static void highlightPossibleMoves(int coordinateX, int coordinateY, int num) {

		for (int i = 1; i <= num; i++) {

			x = coordinateX + i;
			y = coordinateY;
			if ((x >= 0) && (y >= 0) && (x <= 7) && (y <= 7))// Condition for valid token positions
			{
				stacks[x][y].updateVMTokenIcon();
			}

			x = coordinateX - i;
			y = coordinateY;
			if ((x >= 0) && (y >= 0) && (x <= 7) && (y <= 7)) {
				stacks[x][y].updateVMTokenIcon();
			}

			x = coordinateX;
			y = coordinateY + i;
			if ((x >= 0) && (y >= 0) && (x <= 7) && (y <= 7)) {
				stacks[x][y].updateVMTokenIcon();
			}

			x = coordinateX;
			y = coordinateY - i;
			if ((x >= 0) && (y >= 0) && (x <= 7) && (y <= 7)) {
				stacks[x][y].updateVMTokenIcon();
			}
		}

	}

	public static void iiterateOverBoard() {
		// TODO Method Stub
	}

	public static void setStacks(Stack[][] stacks) {
		Board.stacks = stacks;
	}

	public static void updateDominationPercentageForAllPlayers() {
		double totalStacks = 0, p1Stacks = 0, p2Stacks = 0, p3Stacks = 0, p4Stacks = 0;
		for (x = 0; x < 8; x++) {
			for (y = 0; y < 8; y++) {
				if (!stacks[x][y].getStackContents().isEmpty()) {
					totalStacks++;
					if (stacks[x][y].getStackOwner() == SetupAGame.getPlayers().get(0)) {
						p1Stacks++;
					} else if (stacks[x][y].getStackOwner() == SetupAGame.getPlayers().get(1)) {
						p2Stacks++;
					} else if (stacks[x][y].getStackOwner() == SetupAGame.getPlayers().get(2)) {
						p3Stacks++;
					} else {
						p4Stacks++;
					}
				}
			}
		}
		SetupAGame.getPlayers().get(0).setDomination((int) (Math.ceil((p1Stacks / totalStacks) * 100)));
		SetupAGame.getPlayers().get(1).setDomination((int) (Math.ceil((p2Stacks / totalStacks) * 100)));
		SetupAGame.getPlayers().get(2).setDomination((int) (Math.ceil((p3Stacks / totalStacks) * 100)));
		SetupAGame.getPlayers().get(3).setDomination((int) (Math.ceil((p4Stacks / totalStacks) * 100)));

	}

	public static void updateStacksDisplay() {
		for (x = 0; x < 8; x++) {
			for (y = 0; y < 8; y++) {
				//if (!stacks[x][y].getStackContents().isEmpty()) {
				stacks[x][y].updateStackDisplay();
				//}
			}
		}

	}
}