
public class Token {
private Player owner; //TODO: Verify as desired implementation of Owner.
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
public Player getOwner() {
	return owner;
}
public void setOwner(Player owner) {
	this.owner = owner;
}
public int getxCoordinate() {
	return xCoordinate;
}
public void setxCoordinate(int xCoordinate) {
	this.xCoordinate = xCoordinate;
}
public int getyCoordinate() {
	return yCoordinate;
}
public void setyCoordinate(int yCoordinate) {
	this.yCoordinate = yCoordinate;
}
public boolean isColorModeEnabled() {
	return colorModeEnabled;
}
public void setColorModeEnabled(boolean colorModeEnabled) {
	this.colorModeEnabled = colorModeEnabled;
}
}
