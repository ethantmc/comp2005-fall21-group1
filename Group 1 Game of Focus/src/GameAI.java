import java.util.concurrent.ThreadLocalRandom;
public class GameAI {
	Stack spaceFrom;
	Stack spaceTo;

	public int checkMove(Stack stackFrom, Stack stackTo, CPUPlayer CPU) { //returns zero if invalid, otherwise returns minimum token count to move 
		if((stackFrom.getStackOwner() == CPU) && (stackFrom != stackTo)) {
			int xDifference = Math.abs(stackFrom.getXcoord()-stackTo.getXcoord());
			int yDifference = Math.abs(stackFrom.getYcoord()-stackTo.getYcoord());
			if( (xDifference <= stackFrom.getStackSize()) && (stackFrom.getYcoord() == stackTo.getYcoord()) ) {
				//Above means - "If the two stacks lie in the same row, and the original stack could reach"
				return xDifference;
			}
			else if ((yDifference <= stackFrom.getStackSize()) && (stackFrom.getXcoord() == stackTo.getXcoord())) {
				//Above means - "If the two stacks lie in the same column, and the original stack could reach"
				return yDifference; //Any space is valid for easyAI (getValidSpace won't pass back illegal tiles when completed)
			}
			else {
				return 0;
			}
		}
	}
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
				spaceFrom = Board.getValidSpace();
				while(spaceFrom.getStackOwner() != CPU) {
					spaceFrom = Board.getValidSpace();
				}
				//spaceFrom is a random space owned by the CPU. determine a valid move from here.
				int validMove = 0;
				while (validMove == 0) { //Get a Stack the CPUPlayer can actually use, since we don't save per-player references to know this beforehand.
					spaceTo = Board.getValidSpace();
					validMove = checkMove(spaceFrom, spaceTo, CPU);


					//				int decision = ThreadLocalRandom.current().nextInt(0, 2); //[0,2)
					//				if(decision == 0) {
					//					Move.makeAMove(spaceFrom, spaceTo, 1);//SingleMove(space)
					//				}
					//				if(decision == 1) {
					//					int sfSize = spaceFrom.getStackSize();
					//					int sizeDecision = ThreadLocalRandom.current().nextInt(0,sfSize); //[0,sfSize)
					//					Move.makeAMove(spaceFrom, spaceTo, sizeDecision); //MultipleMove(space)
					//				}
				}
				Move.makeAMove(spaceFrom, spaceTo, validMove);
			}

			//NOTE: NONE OF THE HARD AI CODE IS FUNCTIONAL IN ANY COMMIT WHERE THIS COMMENT APPEARS

			if (CPU.getDifficulty() == DifficultyType.HARD) {
				if (((CPU.getReserveCount() > 0) && (CPU.getDomination() < 25)) || ((CPU.getDomination() > 90) && (CPU.getReserveCount() >0))) { //As hard AI, HardAI considers if it's in danger of losing, or able to make a possibly winning move
					int validMove = 0; //NOT redundant by getValidSpace(), a move may be pointless.
					Stack bestSpaceFrom;
					Stack bestSpaceTo;
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

				else { //Section that decides between a single or multiple move
					//TODO: Time Permitting: Get a method that checks all spaces in range of specified stack, and check all spaces for size.
					int decision = ThreadLocalRandom.current().nextInt(0, 2); //[0,2)
					int validMove = 0;
					while (validMove == 0) { //Get a Stack the CPUPlayer can actually use, since we don't save per-player references to know this beforehand.
						spaceFrom = Board.getValidSpace();
						spaceTo = Board.getValidSpace(); //any space is valid for moving to
						if((spaceFrom.getStackOwner() == CPU) && (spaceFrom != spaceTo)) {
							validMove= 1; //Any space is valid for easyAI (getValidSpace won't pass back illegal tiles when completed)
						}
					}
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