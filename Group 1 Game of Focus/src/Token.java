import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Token extends JLabel {
	private Player owner = null; // TODO: Verify as desired implementation of Owner. -This should be ideal for
									// telling a Player they've lost or gained a Token, which Stack is responsible
									// for.
	private boolean isStacked = false;
	ImageIcon p1Icon = new ImageIcon(getClass().getResource("/Player 1 - Token.png"));
	ImageIcon p2Icon = new ImageIcon(getClass().getResource("/Player 2 - Token.png"));
	ImageIcon p3Icon = new ImageIcon(getClass().getResource("/Player 3 - Token.png"));
	ImageIcon p4Icon = new ImageIcon(getClass().getResource("/Player 4 - Token.png"));
	ImageIcon p1CBIcon = new ImageIcon(getClass().getResource("/Player 1 - CBToken.png"));
	ImageIcon p2CBIcon = new ImageIcon(getClass().getResource("/Player 2 - CBToken.png"));
	ImageIcon p3CBIcon = new ImageIcon(getClass().getResource("/Player 3 - CBToken.png"));
	ImageIcon p4CBIcon = new ImageIcon(getClass().getResource("/Player 4 - CBToken.png"));
	ImageIcon p1StackIcon = new ImageIcon(getClass().getResource("/Player 1 - StackToken.png"));
	ImageIcon p2StackIcon = new ImageIcon(getClass().getResource("/Player 2 - StackToken.png"));
	ImageIcon p3StackIcon = new ImageIcon(getClass().getResource("/Player 3 - StackToken.png"));
	ImageIcon p4StackIcon = new ImageIcon(getClass().getResource("/Player 4 - StackToken.png"));
	ImageIcon p1CBStackIcon = new ImageIcon(getClass().getResource("/Player 1 - CBStackToken.png"));
	ImageIcon p2CBStackIcon = new ImageIcon(getClass().getResource("/Player 2 - CBStackToken.png"));
	ImageIcon p3CBStackIcon = new ImageIcon(getClass().getResource("/Player 3 - CBStackToken.png"));
	ImageIcon p4CBStackIcon = new ImageIcon(getClass().getResource("/Player 4 - CBStackToken.png"));
	ImageIcon emptyStackIcon = new ImageIcon(getClass().getResource("/EmptyStack.png"));

	public Token(Player owner) { // TODO: Verify constructor should pass in X and Y
																	// coords.
		super();
		this.owner = owner;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(Color.WHITE);
		this.setMinimumSize(new Dimension(50, 50));

		setTokenIcon();

	}

	public void setTokenIcon() {
		if (owner.getColorblindSetting())
		{
			if (isStacked) {
				if (owner == SetupAGame.getPlayers().get(0)) {
					this.setIcon(p1CBStackIcon);
				} else if (owner == SetupAGame.getPlayers().get(1)) {
					this.setIcon(p2CBStackIcon);
				} else if (owner == SetupAGame.getPlayers().get(2)) {
					this.setIcon(p3CBStackIcon);
				} else if (owner == SetupAGame.getPlayers().get(3)) {
					this.setIcon(p4CBStackIcon);
				} else if (owner == null) {
					this.setIcon(emptyStackIcon);
				}
			} else {
				if (owner == SetupAGame.getPlayers().get(0)) {
					this.setIcon(p1CBIcon);
				} else if (owner == SetupAGame.getPlayers().get(1)) {
					this.setIcon(p2CBIcon);
				} else if (owner == SetupAGame.getPlayers().get(2)) {
					this.setIcon(p3CBIcon);
				} else if (owner == SetupAGame.getPlayers().get(3)) {
					this.setIcon(p4CBIcon);
				} else if (owner == null) {
					this.setIcon(emptyStackIcon);
				}
			}
		}
		else
		{
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

	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		setTokenIcon();
	}

	public boolean isStacked() {
		return isStacked;
	}

	public void setStacked(boolean isStacked) {
		this.isStacked = isStacked;
	}
}