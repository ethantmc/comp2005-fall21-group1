import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Token extends JLabel {
	private Player owner = null; // TODO: Verify as desired implementation of Owner. -This should be ideal for
									// telling a Player they've lost or gained a Token, which Stack is responsible
									// for.
	private int xCoordinate; // May not be long-term implementation - A token knowing where it is is weird if
								// our physical board holds them.
	private int yCoordinate;
	private boolean isStacked = false;
	ImageIcon p1Icon = new ImageIcon(getClass().getResource("/Player 1 - Token.png"));
	ImageIcon p2Icon = new ImageIcon(getClass().getResource("/Player 2 - Token.png"));
	ImageIcon p3Icon = new ImageIcon(getClass().getResource("/Player 3 - Token.png"));
	ImageIcon p4Icon = new ImageIcon(getClass().getResource("/Player 4 - Token.png"));
	ImageIcon p1StackIcon = new ImageIcon(getClass().getResource("/Player 1 - StackToken.png"));
	ImageIcon p2StackIcon = new ImageIcon(getClass().getResource("/Player 2 - StackToken.png"));
	ImageIcon p3StackIcon = new ImageIcon(getClass().getResource("/Player 3 - StackToken.png"));
	ImageIcon p4StackIcon = new ImageIcon(getClass().getResource("/Player 4 - StackToken.png"));
	ImageIcon emptyStackIcon = new ImageIcon(getClass().getResource("/EmptyStack.png"));

	public Token(Player owner, int xCoordinate, int yCoordinate) { // TODO: Verify constructor should pass in X and Y
																	// coords.
		super();
		this.owner = owner;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(Color.WHITE);
		this.setMinimumSize(new Dimension(50, 50));

		setTokenIcon();

	}

	public void setTokenIcon() {
		if (isStacked) {
			if (owner == SetupAGame.getPlayers().get(0)) {
				this.setIcon(p1StackIcon);
			} else if (owner == SetupAGame.getPlayers().get(1)) {
				this.setIcon(p2StackIcon);
			} else if (owner == SetupAGame.getPlayers().get(2)) {
				this.setIcon(p3StackIcon);
			} else if (owner == SetupAGame.getPlayers().get(3)) {
				this.setIcon(p4StackIcon);
			} else if (owner == null) {
				this.setIcon(emptyStackIcon);
			}
		} else {
			if (owner == SetupAGame.getPlayers().get(0)) {
				this.setIcon(p1Icon);
			} else if (owner == SetupAGame.getPlayers().get(1)) {
				this.setIcon(p2Icon);
			} else if (owner == SetupAGame.getPlayers().get(2)) {
				this.setIcon(p3Icon);
			} else if (owner == SetupAGame.getPlayers().get(3)) {
				this.setIcon(p4Icon);
			} else if (owner == null) {
				this.setIcon(emptyStackIcon);
			}
		}

	}

	public Player getOwner() {
		return owner;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		setTokenIcon();
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public boolean isStacked() {
		return isStacked;
	}

	public void setStacked(boolean isStacked) {
		this.isStacked = isStacked;
	}
}