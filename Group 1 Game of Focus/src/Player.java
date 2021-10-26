
public class Player {
	//MAJOR NOTE: these attributes being private is ALMOST DEFINITELY a problem. AFAIK, private attributes play poorly with subclasses. Not 100% though.
	private static String name; //Not set as default player name slightly changes for each. Static to appease Turn class.
	private PlayerType type;  //This is an Enumerated Type!
	private Boolean colorblindSetting = false;
	private int reserveCount = 13;
	private int capturedCount = 0;
	private int ownTokensCaptured= 0; //IMO, rename to tokensLost
	public Player(String name, PlayerType type, Boolean colorblindSetting) {
		super();
		this.name = name;
		this.type = type;
		this.colorblindSetting = colorblindSetting;
	}
	public static String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PlayerType getType() {
		return type;
	}
	public void setType(PlayerType type) {
		this.type = type;
	}
	public Boolean getColorblindSetting() {
		return colorblindSetting;
	}
	public void setColorblindSetting(Boolean colorblindSetting) {
		this.colorblindSetting = colorblindSetting;
	}
	public int getReserveCount() {
		return reserveCount;
	}
	public void setReserveCount(int reserveCount) {
		this.reserveCount = reserveCount;
	}
	public int getCapturedCount() {
		return capturedCount;
	}
	public void setCapturedCount(int capturedCount) {
		this.capturedCount = capturedCount;
	}
	public int getOwnTokensCaptured() {
		return ownTokensCaptured;
	}
	public void setOwnTokensCaptured(int ownTokensCaptured) {
		this.ownTokensCaptured = ownTokensCaptured;
	}
}
