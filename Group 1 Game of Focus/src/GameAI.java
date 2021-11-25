import java.util.concurrent.ThreadLocalRandom;

public class GameAI {
	Stack spaceFrom;
	Stack spaceTo;
	public void cpuDoMove(CPUPlayer CPU) {
		if (CPU.getDifficulty() == DifficultyType.EASY) {
			if (CPU.getReserveCount() > 0) { //As easy AI immediately play a reserve piece if you somehow gained one.
				int validMove = 0; //NOT redundant by getValidSpace(), a move may be pointless or out of range.
				while (validMove == 0) {
					spaceTo = Board.getValidSpace();
					validMove= 1; //Any space is valid for easyAI (getValidSpace won't pass back illegal tiles when completed)
				}
				Move.makeAReserveMove(spaceTo);
			}
			else {
				//code for selecting origin space?
				spaceTo = Board.getValidSpace(); //any space is valid for moving to
				int validMove = 0;
				while (validMove == 0) { //Get a Stack the CPUPlayer can actually use, since we don't save per-player references to know this beforehand.
					spaceFrom = Board.getValidSpace();
					if((spaceFrom.getStackOwner() == CPU) && (spaceFrom != spaceTo)) {
						validMove= 1; //Any space is valid for easyAI (getValidSpace won't pass back illegal tiles when completed)
					}
					else {
						//do nothing and thus check again
					}
				}

				int decision = ThreadLocalRandom.current().nextInt(0, 2); //[0,2)
				if(decision == 0) {
					Move.makeAMove(spaceFrom, spaceTo, 1);//SingleMove(space)
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
					spaceTo = Board.getValidSpace();
					if(spaceTo.getStackOwner() == CPU) { //Call to see if this stack is already owned in the hard AI
						//do nothing, and thus try for another space
						//TODO: Time Permitting: Additional logic check/calls to find a stack at size = 5, preferably with a this AI token on bottom.
					} else {
						validMove= 1;
					}
				}
				Move.makeAReserveMove(spaceTo);

				validMove= 1;
			}
		}
		else if((CPU.getTokensLeft()-CPU.getReserveCount()) <= 4){ //Likely a poor board position, do whatever you can to have more control
			//TODO: Time Permitting: Check domination percentage and BREAK if it's actually good.
			//code for selecting origin space?
			Stack space = Board.getValidSpace();
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
