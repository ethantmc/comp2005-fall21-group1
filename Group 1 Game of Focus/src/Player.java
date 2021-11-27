import java.awt.Color;
import java.io.Serializable;
import java.util.Stack;

public class Player implements Serializable{
	//MAJOR NOTE: these attributes being private is ALMOST DEFINITELY a problem. AFAIK, private attributes play poorly with subclasses. Not 100% though.
	private String name; //Not set as default player name slightly changes for each. Static to appease Turn class.
	private PlayerType type;  //This is an Enumerated Type!
	private DifficultyType difficulty;
	private Stack<Token> reservedTokens = new Stack<Token>();
	//private Stack<Token> Tokens = new Stack<Token>();
	private Boolean isColorblindEnabled = false;
	private Color color;
	private int capturedCount = 0;
	private int domination = 0;
	private int piecesLost = 0; //IMO, rename to tokensLost

	public Player(String name, PlayerType type, Boolean colorblindSetting, DifficultyType difficulty) {
		super();
		this.name = name;
		this.type = type;
		if(this.type == PlayerType.HUMAN) {
			this.difficulty = DifficultyType.HUMAN;
		}
		else {
			this.difficulty = difficulty;
		}
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
	public DifficultyType getDifficulty() {
		return difficulty;
	}
	public int getDomination() {
		return domination;
	}
	public String getName() {
		return name;
	}
	public int getReserveCount() {
		return reservedTokens.size();
	}

	public Token getReserveToken()
	{
		return reservedTokens.pop();
	}
	public int getTokensLeft() {
		return 13-piecesLost;
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
	public void setDifficulty(DifficultyType difficulty) {
		this.difficulty = difficulty;
	}
	public void setDomination(int domination) {
		this.domination = domination;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPiecesLost(int number) {
		this.piecesLost = number;
	}
	public void setType(PlayerType type) {
		this.type = type;
	}
	public void resetStats() {
		this.piecesLost = 0;
		this.capturedCount = 0;
		this.domination = 0;
		this.reservedTokens.clear();
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
