import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Stack extends JPanel {

	private ArrayList<Token> stackContents = new ArrayList<Token>(); // This class assumes that stackContents is an
																		// ordered list, with 0 through 4 representing
																		// the top of the stack, in order.
	private JPanel center = new JPanel(), north = new JPanel(), west = new JPanel(), east = new JPanel(),
			south = new JPanel();

	private ArrayList<JLabel> notPartofStack = new ArrayList<JLabel>();
	private int xcoord, ycoord; // location in the grid
	ImageIcon emptyStackIcon = new ImageIcon(getClass().getResource("/EmptyStack.png"));

	public Stack(int xcoord, int ycoord) {
		super();
		this.setSize(50, 50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;

		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);
		center.setLayout(new BorderLayout(0, 0));
		center.setPreferredSize(new Dimension(50, 50));
		center.setBackground(Color.WHITE);

		for (int i = 0; i < 4; i++) {
			notPartofStack.add(new JLabel());
			notPartofStack.get(i).setBackground(Color.WHITE);
			notPartofStack.get(i).setFocusable(false);
			notPartofStack.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			notPartofStack.get(i).setIcon(emptyStackIcon);
		}

		north.setLayout(new BorderLayout(0, 0));
		north.setBackground(Color.WHITE);
		north.add(notPartofStack.get(0));
		west.setLayout(new BorderLayout(0, 0));
		west.setBackground(Color.WHITE);
		west.add(notPartofStack.get(1));
		east.setLayout(new BorderLayout(0, 0));
		east.setBackground(Color.WHITE);
		east.add(notPartofStack.get(2));
		south.setLayout(new BorderLayout(0, 0));
		south.setBackground(Color.WHITE);
		south.add(notPartofStack.get(3));
		add(center, BorderLayout.CENTER);
		add(north, BorderLayout.NORTH);
		add(east, BorderLayout.EAST);
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

	public void updateStackDisplay() {
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
				north.add(stackContents.get(i), BorderLayout.CENTER);
			} else if (i == stackContents.size() - 3) {
				east.removeAll();
				stackContents.get(i).setStacked(true);
				stackContents.get(i).setTokenIcon();
				east.add(stackContents.get(i), BorderLayout.CENTER);
			} else if (i == stackContents.size() - 4) {
				south.removeAll();
				stackContents.get(i).setStacked(true);
				stackContents.get(i).setTokenIcon();
				south.add(stackContents.get(i), BorderLayout.CENTER);
			} else if (i == stackContents.size() - 5) {
				west.removeAll();
				stackContents.get(i).setStacked(true);
				stackContents.get(i).setTokenIcon();
				west.add(stackContents.get(i), BorderLayout.CENTER);
			} else
				break;
		}
	}

	public void stackToken(Token token) {
		token.setxCoordinate(xcoord);
		token.setyCoordinate(ycoord);
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