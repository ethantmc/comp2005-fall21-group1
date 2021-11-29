public class GameAI {
	static Stack spaceFrom;
	static Stack spaceTo;

	public static int checkMove(Stack stackFrom, Stack stackTo, Player CPU, int offset) { //returns zero if invalid, otherwise returns minimum token count to move 
		int to_return = 0;
		System.out.println("Checking move from "+stackFrom.getXcoord()+","+stackFrom.getYcoord()+" to "+stackTo.getXcoord()+stackTo.getYcoord());
		if ((stackFrom.getStackOwner() == CPU) && (stackFrom != stackTo)) {
			int xDifference = Math.abs(stackFrom.getXcoord() - stackTo.getXcoord());
			int yDifference = Math.abs(stackFrom.getYcoord() - stackTo.getYcoord());
			if ((xDifference <= ((stackFrom.getStackSize())-offset)) && (stackFrom.getYcoord() == stackTo.getYcoord())) {
				//Above means - "If the two stacks lie in the same row, and the original stack could reach"
				to_return = xDifference;
			} 
			else if ((yDifference <= ((stackFrom.getStackSize())-offset)) && (stackFrom.getXcoord() == stackTo.getXcoord())) {
				//Above means - "If the two stacks lie in the same column, and the original stack could reach"
				to_return =  yDifference;
			} 
			else {
				to_return = 0;
			}
		}
		System.out.println("Checking completed, the value was:"+to_return);
		return to_return;
	}

	public static void cpuDoMove(Player CPU) {
		System.out.println("cpuDoMove reached! CPU Difficulty: "+CPU.getDifficulty()+" Cpu is PlayerType: "+CPU.getType());
		if (CPU.getDifficulty() == DifficultyType.EASY) {
			if (CPU.getReserveCount() > 0) { //As easy AI immediately play a reserve piece if you somehow gained one.
				int validMove = 0; //NOT redundant by getValidSpace(), a move may be pointless or out of range.
				while (validMove == 0) {
					System.out.println("Calling on getValidSpace() for a reserve move location...");
					spaceTo = Board.getValidSpace();
					validMove = 1; //Any space is valid for easyAI (getValidSpace won't pass back illegal tiles when completed)
				}
				Move.makeAReserveMove(spaceTo);
			} 
			else {
				System.out.println("Easy AI could not make a reserve move."+"It had: "+CPU.getReserveCount()+" pieces in the reserve.");
				spaceFrom = Board.getValidSpace();
				System.out.println("Board.getValidSpace() returned and we're still running!");
				while (spaceFrom.getStackOwner() != CPU) {
					spaceFrom = Board.getValidSpace();
				}
				System.out.println("We now have the stack the CPU will move from...");
				//spaceFrom is a random space owned by the CPU. determine a valid move from here.
				int validMove = 0;
				while (validMove == 0) { //Get a Stack the CPUPlayer can actually use, since we don't save per-player references to know this beforehand.
					spaceTo = Board.getValidSpace();
					validMove = checkMove(spaceFrom, spaceTo, CPU, 0);
				}
				System.out.println("We're now telling move to do the move we've decided on.");
				Move.makeAMove(spaceFrom, spaceTo, validMove);
			}
		}

		if (CPU.getDifficulty() == DifficultyType.HARD) {
			if (((CPU.getReserveCount() > 0) && (CPU.getDomination() < 25)) || ((CPU.getDomination() > 80) && (CPU.getReserveCount() > 0))) { //As hard AI, HardAI considers if it's in danger of losing, or able to make a possibly winning move
				int validMove = 0; //NOT redundant by getValidSpace(), a move may be pointless.
				Stack bestSpaceFrom;
				Stack bestSpaceTo;
				while (validMove == 0) {
					spaceTo = Board.getValidSpace();
					if (spaceTo.getStackOwner() == CPU) { //Call to see if this stack is already owned in the hard AI
						//do nothing, and thus try for another space
						//TODO: Time Permitting: Additional logic check/calls to find a stack at size = 5, preferably with a this AI token on bottom.
					} else {
						validMove = 1;
					}
				}
				Move.makeAReserveMove(spaceTo);
				validMove = 1;
			} 
			else {
				System.out.println("Hard CPU evaluated a reserve move as a bad option");
				spaceFrom = Board.getValidSpace();
				while (spaceFrom.getStackOwner() != CPU) {
					spaceFrom = Board.getValidSpace();
				}
				int i;
				boolean jobDone = false;
				for(i=0; i<14; i++) { //arbitrary number of non-exhaustive attempts, to prevent a neverending stall.
					System.out.println(i+"th attempt to make a smarter move!");
					spaceTo = Board.getValidSpace();
					int validMove = 0;
					int requiredSize = checkMove(spaceFrom,spaceTo, CPU, 0); //Check move returns the minimum number of tokens to reach spaceTo from spaceFrom.
					int goodMoveSize = spaceFrom.containsTokenofOwner(); //goodMoveSize is the number to give makeAMove to leave behind an owned stack.
					if((goodMoveSize > -1) && (requiredSize <= goodMoveSize)) { //"If the stack you'd move to leave behind an owned stack is legal"
						Move.makeAMove(spaceFrom, spaceTo, goodMoveSize);
						jobDone = true;
						break; //leave the for loop early.
					}

				}
				//Enter this only if the more intelligent search above was unsuccessful.
				if(jobDone == false) {
					int validMove = 0;
					while (validMove == 0) { //Get a Stack the CPUPlayer can actually use, since we don't save per-player references to know this beforehand.
						spaceTo = Board.getValidSpace();
						validMove = checkMove(spaceFrom, spaceTo, CPU, 0); //this is set to an integer representing the NUMBER OF PIECES to move, if it's zero the move isn't valid.
					}
					Move.makeAMove(spaceFrom, spaceTo, validMove);
				}
			}
		}
	}
}