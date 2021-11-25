import java.util.concurrent.ThreadLocalRandom;

public class GameAI {

	public void cpuDoMove(CPUPlayer CPU) {
		if (CPU.getDifficulty() == DifficultyType.EASY) {
			if (CPU.getReserveCount() > 0) { //As easy AI immediately play a reserve piece if you somehow gained one.
				int validMove = 0; //NOT redundant by getValidSpace(), a move may be pointless or out of range.
				while (validMove == 0) {
					Stack space = getValidSpace();
					//Call to see if this stack is already owned in the hard AI
					validMove= 1;
				}
			}
			else {
				//code for selecting origin space?
				Stack space = getValidSpace();
				int decision = ThreadLocalRandom.current().nextInt(0, 2); //[0,2)
				if(decision == 0) {
					//SingleMove(space)
				}
				if(decision == 1) {
					//MultipleMove(space)
				}
			}
		}

		if (CPU.getDifficulty() == DifficultyType.HARD) {
			if (CPU.getReserveCount() > 2) { //As hard AI, only play a reserve piece before considering anything if you have more than 2 (helps avoid losing)
				int validMove = 0; //NOT redundant by getValidSpace(), a move may be pointless or out of range.
				while (validMove == 0) {
					Stack space = getValidSpace();
					//Call to see if this stack is already owned by them in the hard AI
					//TODO: Time Permitting: Additional logic check/calls to find a stack at size = 5, preferably with a this AI token on bottom.
					validMove= 1;
				}
			}
			else if((CPU.getTokensLeft()-CPU.getReserveCount()) <= 4){ //Likely a poor board position, do whatever you can to have more control
				//TODO: Time Permitting: Check domination percentage and BREAK if it's actually good.
				//code for selecting origin space?
				Stack space = getValidSpace();
				int decision = ThreadLocalRandom.current().nextInt(0, 2); //[0,2)
				if(decision == 0) {
					//SingleMove(space)
				}
				if(decision == 1) {
					//MultipleMove(space)
				}
			}
		}
	}

}
}
