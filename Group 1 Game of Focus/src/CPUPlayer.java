
public class CPUPlayer extends Player {
private DifficultyType difficulty;

public CPUPlayer(String name, PlayerType type, Boolean colorblindSetting, DifficultyType difficulty) {
	super(name, type, colorblindSetting);
	this.difficulty = difficulty;
}

}
