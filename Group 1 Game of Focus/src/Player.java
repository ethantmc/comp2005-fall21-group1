import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

public class Player {
	//MAJOR NOTE: these attributes being private is ALMOST DEFINITELY a problem. AFAIK, private attributes play poorly with subclasses. Not 100% though.
	private String name; //Not set as default player name slightly changes for each. Static to appease Turn class.
	private PlayerType type;  //This is an Enumerated Type!
	private Stack<Token> reservedTokens = new Stack<Token>();
	private Boolean isColorblindEnabled = false;
	private Color color;
	private int reserveCount = 13;
	private int capturedCount = 0;
	private int piecesLost= 0; //IMO, rename to tokensLost

	public Player(String name, PlayerType type, Boolean colorblindSetting) {
		super();
		this.name = name;
		this.type = type;
		this.isColorblindEnabled = colorblindSetting;
		reservedTokens.clear();
	}
	public int getCapturedCount() {
		return capturedCount;
	}
	public Color getColor() {
		return color;
	}
	public Boolean getColorblindSetting() {
		return isColorblindEnabled;
	}
	public String getName() {
		return name;
	}
	public int getPiecesLost() {
		return piecesLost;
	}
	public int getReserveCount() {
		return reservedTokens.size();
	}
	public Token getReserveToken()
	{
		return reservedTokens.pop();
	}
	public PlayerType getType() {
		return type;
	}
	public void incrementCapturedCount() {
		this.capturedCount++; //+= works in java AFAIK.
	}
	public void incrementPiecesLost() {
		this.piecesLost++;
	}
	public void reserveAToken(Token reserve) {
		this.reservedTokens.push(reserve);
	}
	public void setCapturedCount(int capturedCount) {
		this.capturedCount = capturedCount;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setColorblindSetting(Boolean colorblindSetting) {
		this.isColorblindEnabled = colorblindSetting;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPiecesLost(int number) {
		this.piecesLost = number;
	}
	public void setReserveCount(int reserveCount) {
		this.reserveCount = reserveCount;
	}
	public void setType(PlayerType type) {
		this.type = type;
	}
	public void toggleColorblindSetting() 
	{
		if(this.isColorblindEnabled) {
			this.isColorblindEnabled = false;
		} else {
			this.isColorblindEnabled = true;
		}
	}
}
