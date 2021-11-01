
public class Token {
	private Player owner; //TODO: Verify as desired implementation of Owner. -This should be ideal for telling a Player they've lost or gained a Token, which Stack is responsible for.
	private int xCoordinate; // May not be long-term implementation - A token knowing where it is is weird if our physical board holds them.
	private int yCoordinate;
	private boolean colorModeEnabled = false;
	public Token(Player owner, int xCoordinate, int yCoordinate, boolean colorModeEnabled) { //TODO: Verify constructor should pass in X and Y coords.
		super();
		this.owner = owner;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.colorModeEnabled = colorModeEnabled;
	}
	public void draw(double x,double y) {
		//TODO Method Stub. Here we have tokens responsible for *graphically* representing themselves.
		//Their coordinates are fetched from the board list- wherever that is - and whatever holds the board list is responsible for telling them to redraw.
		//NOTE: This is a double as our solution to knowing what's in a stack is by drawing the four other stack tokens "below" and in the corners of a tile.
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
	public boolean isColorModeEnabled() {
		return colorModeEnabled;
	}
	public void setColorModeEnabled(boolean colorModeEnabled) {
		this.colorModeEnabled = colorModeEnabled;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public void undraw() {
		//TODO Method Stub. Remove the token graphically, then remove this object from memory (does GC handle that automatically?) This method is primarily called by Stack while capturing tokens.
	}
}