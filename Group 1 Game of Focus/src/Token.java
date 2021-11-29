import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Token extends JLabel {
	private Player owner = null; // This should be ideal for // telling a Player they've lost or gained a Token, which Stack is responsible for.
	private boolean isStacked = false;
	ImageIcon p1Icon = new ImageIcon(getClass().getResource("/Player 1 - Token.png"));
	ImageIcon p2Icon = new ImageIcon(getClass().getResource("/Player 2 - Token.png"));
	ImageIcon p3Icon = new ImageIcon(getClass().getResource("/Player 3 - Token.png"));
	ImageIcon p4Icon = new ImageIcon(getClass().getResource("/Player 4 - Token.png"));
	ImageIcon p1VMIcon = new ImageIcon(getClass().getResource("/Player 1 - ValidMoveToken.png"));
	ImageIcon p2VMIcon = new ImageIcon(getClass().getResource("/Player 2 - ValidMoveToken.png"));
	ImageIcon p3VMIcon = new ImageIcon(getClass().getResource("/Player 3 - ValidMoveToken.png"));
	ImageIcon p4VMIcon = new ImageIcon(getClass().getResource("/Player 4 - ValidMoveToken.png"));
	ImageIcon p1CBIcon = new ImageIcon(getClass().getResource("/Player 1 - CBToken.png"));
	ImageIcon p2CBIcon = new ImageIcon(getClass().getResource("/Player 2 - CBToken.png"));
	ImageIcon p3CBIcon = new ImageIcon(getClass().getResource("/Player 3 - CBToken.png"));
	ImageIcon p4CBIcon = new ImageIcon(getClass().getResource("/Player 4 - CBToken.png"));
	ImageIcon p1CBClickedIcon = new ImageIcon(getClass().getResource("/Player 1 - CBClickedToken.png"));
	ImageIcon p2CBClickedIcon = new ImageIcon(getClass().getResource("/Player 2 - CBClickedToken.png"));
	ImageIcon p3CBClickedIcon = new ImageIcon(getClass().getResource("/Player 3 - CBClickedToken.png"));
	ImageIcon p4CBClickedIcon = new ImageIcon(getClass().getResource("/Player 4 - CBClickedToken.png"));
	ImageIcon p1ClickedIcon = new ImageIcon(getClass().getResource("/Player 1 - ClickedToken.png"));
	ImageIcon p2ClickedIcon = new ImageIcon(getClass().getResource("/Player 2 - ClickedToken.png"));
	ImageIcon p3ClickedIcon = new ImageIcon(getClass().getResource("/Player 3 - ClickedToken.png"));
	ImageIcon p4ClickedIcon = new ImageIcon(getClass().getResource("/Player 4 - ClickedToken.png"));
	ImageIcon p1VMCBIcon = new ImageIcon(getClass().getResource("/Player 1 - VMCBToken.png"));
	ImageIcon p2VMCBIcon = new ImageIcon(getClass().getResource("/Player 2 - VMCBToken.png"));
	ImageIcon p3VMCBIcon = new ImageIcon(getClass().getResource("/Player 3 - VMCBToken.png"));
	ImageIcon p4VMCBIcon = new ImageIcon(getClass().getResource("/Player 4 - VMCBToken.png"));
	ImageIcon p1StackIcon = new ImageIcon(getClass().getResource("/Player 1 - StackToken.png"));
	ImageIcon p2StackIcon = new ImageIcon(getClass().getResource("/Player 2 - StackToken.png"));
	ImageIcon p3StackIcon = new ImageIcon(getClass().getResource("/Player 3 - StackToken.png"));
	ImageIcon p4StackIcon = new ImageIcon(getClass().getResource("/Player 4 - StackToken.png"));
	ImageIcon p1CBStackIcon = new ImageIcon(getClass().getResource("/Player 1 - CBStackToken.png"));
	ImageIcon p2CBStackIcon = new ImageIcon(getClass().getResource("/Player 2 - CBStackToken.png"));
	ImageIcon p3CBStackIcon = new ImageIcon(getClass().getResource("/Player 3 - CBStackToken.png"));
	ImageIcon p4CBStackIcon = new ImageIcon(getClass().getResource("/Player 4 - CBStackToken.png"));
	ImageIcon emptyStackIcon = new ImageIcon(getClass().getResource("/EmptyStack.png"));
	ImageIcon emptyStackVMIcon = new ImageIcon(getClass().getResource("/EmptyStackValidMove.png"));

	public Token(Player owner) {
		super();
		this.owner = owner;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(Color.WHITE);
		this.setMinimumSize(new Dimension(50, 50));

		setTokenIcon();

	}

	public Player getOwner() {
		return owner;
	}

	public boolean isStacked() {
		return isStacked;
	}

	public void setClickedTokenIcon() {
		if (Turn.getCurrentPlayer().getColorblindSetting()) {
			if (owner == SetupAGame.getPlayers().get(0)) {
				this.setIcon(p1CBClickedIcon);
			} else if (owner == SetupAGame.getPlayers().get(1)) {
				this.setIcon(p2CBClickedIcon);
			} else if (owner == SetupAGame.getPlayers().get(2)) {
				this.setIcon(p3CBClickedIcon);
			} else if (owner == SetupAGame.getPlayers().get(3)) {
				this.setIcon(p4CBClickedIcon);
			}
		} else if (owner == SetupAGame.getPlayers().get(0)) {
			this.setIcon(p1ClickedIcon);
		} else if (owner == SetupAGame.getPlayers().get(1)) {
			this.setIcon(p2ClickedIcon);
		} else if (owner == SetupAGame.getPlayers().get(2)) {
			this.setIcon(p3ClickedIcon);
		} else if (owner == SetupAGame.getPlayers().get(3)) {
			this.setIcon(p4ClickedIcon);
		}
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		setTokenIcon();
	}

	public void setStacked(boolean isStacked) {
		this.isStacked = isStacked;
	}

	public void setTokenIcon() {
		if (Turn.getCurrentPlayer().getColorblindSetting()) {
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
			} else if (owner == SetupAGame.getPlayers().get(0)) {
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
		} else if (isStacked) {
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
		} else if (owner == SetupAGame.getPlayers().get(0)) {
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

	public void setValidMoveTokenIcon() {
		if (Turn.getCurrentPlayer().getColorblindSetting()) {
			if (owner == SetupAGame.getPlayers().get(0)) {
				this.setIcon(p1VMCBIcon);
			} else if (owner == SetupAGame.getPlayers().get(1)) {
				this.setIcon(p2VMCBIcon);
			} else if (owner == SetupAGame.getPlayers().get(2)) {
				this.setIcon(p3VMCBIcon);
			} else if (owner == SetupAGame.getPlayers().get(3)) {
				this.setIcon(p4VMCBIcon);
			}
		}
		else if (owner == SetupAGame.getPlayers().get(0)) {
			this.setIcon(p1VMIcon);
		} else if (owner == SetupAGame.getPlayers().get(1)) {
			this.setIcon(p2VMIcon);
		} else if (owner == SetupAGame.getPlayers().get(2)) {
			this.setIcon(p3VMIcon);
		} else if (owner == SetupAGame.getPlayers().get(3)) {
			this.setIcon(p4VMIcon);
		}
	}
}