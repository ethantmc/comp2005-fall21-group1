import java.util.concurrent.ThreadLocalRandom;
public class Move {

	
	public void makeAMultipleMove() {
		//TODO Method stub
	}
	public void makeASingleMove() {
		//TODO Method stub
	}
	public void makeAReserveMove() {
		//TODO Method stub
	}
	public void decideACpuMove(CPUPlayer CPU) { //Consider whether this should be somewhere else.
		DifficultyType difficulty;
		difficulty = CPU.getDifficulty();
		if (difficulty == DifficultyType.EASY) {
			if (CPU.getReserveCount() > 0) {
				//TODO: Randomize from valid reserve moves
				//makeAReserveMove(//coordinates waiting on galib)
			}
			else {
				//make any random move
				int decider;
				decider = ThreadLocalRandom.current().nextInt(0, 2);
				if (decider == 0) {
					//makeASingleMove
				}
				if (decider == 1) {
					//makeAMultipleMove
				}
				
			}
		}
		if (difficulty == DifficultyType.HARD) {
			if (CPU.getReserveCount() > 2) { //keep 2 pieces in reserve for more nuanced choices
				//TODO: Randomize from valid reserve moves
				//makeAReserveMove(//coordinates waiting on galib)
			}
			else if(CPU.getPiecesLost() < 6) {  //Consider using domination % as an indicator for strategy
				//make any random move -behavior of EASY Ai at all times
				int decider;
				decider = ThreadLocalRandom.current().nextInt(0, 3);
				if (decider == 0) {
					//makeASingleMove
				}
				if (decider == 1) {
					//makeAMultipleMove
				}
				if (decider == 2) {
					//makeAReserveMove
				}
			}
			else {
				//More nuanced decision-making
			}
		}
	}
}
