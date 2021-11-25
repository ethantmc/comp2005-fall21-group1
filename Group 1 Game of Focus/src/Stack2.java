import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Stack2 extends JPanel {

	private ArrayList<Token> stackContents = new ArrayList<Token>(); // This class assumes that stackContents is an
																		// ordered list, with 0 through 4 representing
																		// the top of the stack, in order.
	private JPanel center = new JPanel(), north = new JPanel(), west = new JPanel(), east = new JPanel(),
			south = new JPanel();

	private ArrayList<JLabel> notPartofStack = new ArrayList<JLabel>();
	private int xcoord, ycoord; // location in the grid
	private boolean cond1, cond2, cond3, cond4;
	ImageIcon emptyStackIcon = new ImageIcon(getClass().getResource("/EmptyStack.png"));
	ImageIcon emptyStackBlackIcon = new ImageIcon(getClass().getResource("/EmptyStackBlack.png"));
	ImageIcon emptyStackVMIcon = new ImageIcon(getClass().getResource("/EmptyStackValidMove.png"));
	ImageIcon emptyStackVMCBIcon = new ImageIcon(getClass().getResource("/EmptyStackValidMoveCB.png"));

	public Stack2(int xcoord, int ycoord) {
		super();
		this.setSize(50, 50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;

		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);
		center.setPreferredSize(new Dimension(50, 50));
		// center.vgap(0);
		// center.setPreferredSize(new Dimension(50, 50));
		center.setBackground(Color.WHITE);
		//
		for (int i = 0; i < 7; i++) {
			notPartofStack.add(new JLabel());
			notPartofStack.get(i).setBackground(Color.WHITE);
			notPartofStack.get(i).setFocusable(false);
			notPartofStack.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			notPartofStack.get(i).setIcon(emptyStackIcon);
		}
		notPartofStack.get(4).setIcon(emptyStackVMIcon);
		notPartofStack.get(5).setIcon(emptyStackVMCBIcon);
		notPartofStack.get(6).setIcon(emptyStackBlackIcon);

		north.setLayout(new BorderLayout(0, 0));
		north.setBackground(Color.WHITE);
		north.add(notPartofStack.get(0));
		west.setPreferredSize(new Dimension(30, 10));
		west.setLayout(new BorderLayout(0, 0));
		west.setBackground(Color.WHITE);
		west.add(notPartofStack.get(1));
		east.setPreferredSize(new Dimension(30, 10));
		east.setBackground(Color.WHITE);
		east.setLayout(new BorderLayout(0, 0));
		east.add(notPartofStack.get(2));
		south.setLayout(new BorderLayout(0, 0));
		south.setBackground(Color.WHITE);
		south.add(notPartofStack.get(3));
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));
		// center.setLayout(new BorderLayout(0, 0));
		add(north, BorderLayout.NORTH);
		add(east, BorderLayout.EAST);

		// east.setLayout(null);
		add(south, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);

	}

	public ArrayList<Token> moveTokens(int numberofTokens) {
		ArrayList<Token> movingList;
		movingList = (ArrayList<Token>) stackContents.subList(numberofTokens, (stackContents.size() - 1));
		ArrayList<Token> remainingList;
		remainingList = (ArrayList<Token>) stackContents.subList(0, numberofTokens); // remainingList will be the new
																						// stack in the old place.
																						// Size-1 since indexed at zero.
																						// TODO: Test for a null list,
																						// might need special logic.
		stackContents = remainingList; // This stack is reduced to whatever wasn't moved.
		updateStackDisplay();
		return movingList;

	}

	public void updateTokenIcon() {
		stackContents.forEach(x -> x.setTokenIcon());
	}

	public void updateVMTokenIcon() {
		// Conditions for invalid token positions
		cond1 = (xcoord == 0 && (ycoord == 0 || ycoord == 1 || ycoord == 6 || ycoord == 7));
		cond2 = (xcoord == 7 && (ycoord == 0 || ycoord == 1 || ycoord == 6 || ycoord == 7));
		cond3 = (ycoord == 7 && (xcoord == 1 || xcoord == 6));
		cond4 = (ycoord == 0 && (xcoord == 1 || xcoord == 6));
		if (!(cond1 || cond2 || cond3 || cond4)) {
			if (!stackContents.isEmpty())
				stackContents.get(stackContents.size() - 1).setValidMoveTokenIcon();
			else {
				if (Turn.getCurrentPlayer().getColorblindSetting())
					center.add(notPartofStack.get(5));
				else
					center.add(notPartofStack.get(4));
			}
		}

	}

	public void updateStackDisplay() {

		if (stackContents.isEmpty()) {
			center.removeAll();
			center.add(notPartofStack.get(6));
		} else {
			for (int i = 0; i <= 5; i++) {
				if (i == stackContents.size() - 1) {
					center.removeAll();
					stackContents.get(i).setStacked(false);
					stackContents.get(i).setTokenIcon();
					center.add(stackContents.get(i), BorderLayout.CENTER);
				} else if (i == stackContents.size() - 2) {
					north.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.CENTER);
					north.add(stackContents.get(i), BorderLayout.CENTER);
				} else if (i == stackContents.size() - 3) {
					east.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.CENTER);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.LEFT);
					east.add(stackContents.get(i), BorderLayout.WEST);
				} else if (i == stackContents.size() - 4) {
					south.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.TOP);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.CENTER);
					south.add(stackContents.get(i), BorderLayout.CENTER);
				} else if (i == stackContents.size() - 5) {
					west.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.CENTER);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
					west.add(stackContents.get(i), BorderLayout.EAST);
				} else
					break;
			}
		}

	}

	public void stackToken(Token token) {
		stackContents.add(token);

		if (stackContents.size() == 6) {
			removeToken();
		}

		updateStackDisplay();
	}

	// Handles Capture Token and Reserve Token
	public void removeToken() {
		Token temp;
		temp = stackContents.remove(0);

		if (temp.getOwner() == Turn.getCurrentPlayer()) {
			Turn.getCurrentPlayer().reserveAToken(temp);// Add to reserve
		} else {
			temp.getOwner().incrementPiecesLost();
			temp.setOwner(null);
			temp.setStacked(false);
			temp.setTokenIcon();
			Turn.getCurrentPlayer().incrementCapturedCount();
		}

	}

	public Player getStackOwner() {
		if (stackContents.isEmpty())
			return null;
		return stackContents.get(stackContents.size() - 1).getOwner();
	}

	public int getStackSize() {
		return stackContents.size();
	}

	public ArrayList<Token> getStackContents() {
		return stackContents;
	}

	public void setStackContents(ArrayList<Token> stackContents) {
		this.stackContents = stackContents;
	}

	public int getXcoord() {
		return xcoord;
	}

	public int getYcoord() {
		return ycoord;
	}
}