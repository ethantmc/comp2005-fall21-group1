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
	// ordered list, with the the Stack owner being at the end of the list.
	private JPanel center = new JPanel(), north = new JPanel(), west = new JPanel(), east = new JPanel(),
			south = new JPanel();

	private ArrayList<JLabel> notPartofStack = new ArrayList<JLabel>();
	private int xcoord, ycoord; // location in the grid
	private boolean cond1, cond2, cond3, cond4;
	ImageIcon emptyStackIcon = new ImageIcon(getClass().getResource("/EmptyStack.png"));
	ImageIcon emptyStackBlackIcon = new ImageIcon(getClass().getResource("/EmptyStackBlack.png"));
	ImageIcon emptyStackVMIcon = new ImageIcon(getClass().getResource("/EmptyStackValidMove.png"));
	ImageIcon emptyStackVMCBIcon = new ImageIcon(getClass().getResource("/EmptyStackValidMoveCB.png"));
	ImageIcon emptyStackClickedCBIcon = new ImageIcon(getClass().getResource("/EmptyStackCBClickedToken.png"));
	ImageIcon emptyStackClickedIcon = new ImageIcon(getClass().getResource("/EmptyStackClickedToken.png"));

	public Stack(int xcoord, int ycoord) {
		super();
		this.setSize(50, 50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;

		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);

		center.setBackground(Color.WHITE);
		//
		for (int i = 0; i < 9; i++) {
			notPartofStack.add(new JLabel());
			notPartofStack.get(i).setBackground(Color.WHITE);
			notPartofStack.get(i).setFocusable(false);
			notPartofStack.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			notPartofStack.get(i).setIcon(emptyStackIcon);
		}
		notPartofStack.get(4).setIcon(emptyStackVMIcon);
		notPartofStack.get(5).setIcon(emptyStackVMCBIcon);
		notPartofStack.get(6).setIcon(emptyStackBlackIcon);
		notPartofStack.get(7).setIcon(emptyStackClickedCBIcon);
		notPartofStack.get(8).setIcon(emptyStackClickedIcon);

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

	public int containsTokenofOwner() {
		int i=0;
		while((stackContents.get(i) != null) && (stackContents.get(i) != stackContents.get(stackContents.size() - 1) )) { //Search until list is at the top token, or given max, but do not search a null stack.
			if(stackContents.get(i).getOwner()==this.getStackOwner()) { //if a tokens owner matches the owner of this stack
				int returnme = (this.getStackSize()-i)-1; //Invert the number. Examples below:
				return (returnme); //break at the first(lowest) find. Maximizes captures.	//	 0, 1, 2, [token], 4.
				//																			i = 3
				//																	returnme gives: 5-3-1 = 1.
				//
				//																	[token], 1, 2, 3, 4
				//																			i = 0
				//																	returnme gives: 4.  //pass into MakeAMove, this should move the correct number of tokens, leaving behind an owned stack.
			}
		}
		//TODO: Time Permitting: further sophistication by not just return the first result, but all results.
		//Currently, if you get here, there must only be one owner in this stack, so return (-1) to indicate anything left behind won't be owned.
		return -1;
	}

	public int containsTokenofOwnerInRange(int max) {
		int i=0;
		while((stackContents.get(i) != null) && (stackContents.get(i) != stackContents.get(stackContents.size() - 1) ) && ( i !=  max )) { //Search until list is at the top token, or given max, but do not search a null stack.
			if(stackContents.get(i).getOwner()==this.getStackOwner()) { //if a tokens owner matches the owner of this stack
				int returnme = (this.getStackSize()-i)-1; //Invert the number. Examples below:
				return (returnme); //break at the first(lowest) find. Maximizes captures.	//	 0, 1, 2, [token], 4.
				//																			i = 3
				//																	returnme gives: 5-3-1 = 1.
				//
				//																	[token], 1, 2, 3, 4
				//																			i = 0
				//																	returnme gives: 4.  //pass into MakeAMove, this should move the correct number of tokens, leaving behind an owned stack.
			}
		}
		//TODO: Time Permitting: further sophistication by not just return the first result, but all results.
		//Currently, if you get here, there must only be one owner in this stack, so return (-1) to indicate anything left behind won't be owned.
		return -1;
	}

	public ArrayList<Token> getStackContents() {
		return stackContents;
	}

	public Player getStackOwner() {
		if (stackContents.isEmpty()) {
			return null;
		}
		return stackContents.get(stackContents.size() - 1).getOwner();
	}

	public int getStackSize() {
		return stackContents.size();
	}
	public int getXcoord() {
		return xcoord;
	}
	public int getYcoord() {
		return ycoord;
	}
	public ArrayList<Token> moveTokens(int numberofTokens) {
		ArrayList<Token> movingList;
		ArrayList<Token> remainingList;
		if ((numberofTokens == 1) && (this.getStackSize() == 1)) {
			System.out.println("Special condition hit in moveTokens, single move of a size-1 stack.");
			movingList = stackContents;
			remainingList = stackContents;
			remainingList.remove(0);
			return movingList;
		}
		else {
			movingList = new ArrayList<Token>(stackContents.subList(numberofTokens - 1, (stackContents.size() - 1)));
			remainingList = new ArrayList<Token>(stackContents.subList(0, numberofTokens - 1)); // remainingList will be the
		}
		// new
		// stack in the old place.
		// Size-1 since indexed at
		// zero.
		// TODO: Test for a null
		// list,
		// might need special logic.
		stackContents = remainingList; // This stack is reduced to whatever wasn't moved.
		updateStackDisplay();
		return movingList;

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

	public void setStackContents(ArrayList<Token> stackContents) {
		this.stackContents = stackContents;
	}

	public void stackToken(Token token) {
		stackContents.add(token);

		if (stackContents.size() == 6) {
			removeToken();
		}

		updateStackDisplay();
	}

	public void updateClickedTokenIcon() {
		if (!stackContents.isEmpty()) {
			stackContents.get(stackContents.size() - 1).setClickedTokenIcon();
		} else if (Turn.getCurrentPlayer().getColorblindSetting()) {
			center.add(notPartofStack.get(7));
		} else {
			center.add(notPartofStack.get(8));
		}

	}

	public void updateStackDisplay() {

		if (stackContents.isEmpty()) {
			center.removeAll();
			center.add(notPartofStack.get(6));
		} else {
			for (int i = 0; i <= 5; i++) {
				if (i == (stackContents.size() - 1)) {
					center.removeAll();
					stackContents.get(i).setStacked(false);
					stackContents.get(i).setTokenIcon();
					center.add(stackContents.get(i), BorderLayout.CENTER);
				} else if (i == (stackContents.size() - 2)) {
					north.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.BOTTOM);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.CENTER);
					north.add(stackContents.get(i), BorderLayout.CENTER);
				} else if (i == (stackContents.size() - 3)) {
					east.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.CENTER);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.LEFT);
					east.add(stackContents.get(i), BorderLayout.WEST);
				} else if (i == (stackContents.size() - 4)) {
					south.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.TOP);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.CENTER);
					south.add(stackContents.get(i), BorderLayout.CENTER);
				} else if (i == (stackContents.size() - 5)) {
					west.removeAll();
					stackContents.get(i).setStacked(true);
					stackContents.get(i).setTokenIcon();
					stackContents.get(i).setVerticalAlignment(SwingConstants.CENTER);
					stackContents.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
					west.add(stackContents.get(i), BorderLayout.EAST);
				} else {
					break;
				}
			}
		}

	}

	public void updateTokenIcon() {
		stackContents.forEach(Token::setTokenIcon);
	}

	public void updateVMTokenIcon() {
		// Conditions for invalid token positions
		cond1 = ((xcoord == 0) && ((ycoord == 0) || (ycoord == 1) || (ycoord == 6) || (ycoord == 7)));
		cond2 = ((xcoord == 7) && ((ycoord == 0) || (ycoord == 1) || (ycoord == 6) || (ycoord == 7)));
		cond3 = ((ycoord == 7) && ((xcoord == 1) || (xcoord == 6)));
		cond4 = ((ycoord == 0) && ((xcoord == 1) || (xcoord == 6)));
		if (!(cond1 || cond2 || cond3 || cond4)) {
			if (!stackContents.isEmpty()) {
				stackContents.get(stackContents.size() - 1).setValidMoveTokenIcon();
			} else if (Turn.getCurrentPlayer().getColorblindSetting()) {
				center.add(notPartofStack.get(5));
			} else {
				center.add(notPartofStack.get(4));
			}
		}

	}
}