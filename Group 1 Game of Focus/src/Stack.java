import java.util.ArrayList;

public class Stack {
	private ArrayList<Token> stackContents; //This class assumes that stackContents is an ordered list, with 0 through 4 representing the top of the stack, in order.
	//private int stacksize = 0; //This existing will likely cause trouble. We can infer the stack size from stackContents.
	public Stack(ArrayList<Token> stackContents) {
		super();
		this.stackContents = stackContents;
	}
	public void captureToken(ArrayList<Token> tileContents) {
		//pass in the targeted tile as Tile Contents,
		Player stackOwner = stackContents.get(0).getOwner(); //We set this value to compare if a removed token should be returned to the reserve or not.
		stackContents.addAll(tileContents); //All tokens in the to-be-captured tile are added to the stack at the bottom.
		int i = 5; //Only tokens beyond the 5 that remain no matter what are targeted. Captured count and PiecesLost don't update til they move off the board, so no special logic is needed to handle the fact a player just lost control of a stack.
		while( i < this.getSize()) { 
			Player tokenOwner; //whoever owns this particular token
			tokenOwner = tileContents.get(0).getOwner(); //arrayList .get() returns a Token object, we call the token method getOwner() to uniquely identify our player. Stack-Token-Player are thus coupled, but stack-token need to be and token needs getOwner().
			if (tokenOwner != stackOwner){ //If one of the captured tokens is not owned by the stack controller, stack controller captures it and token owner loses it.
				stackOwner.incrementCapturedCount(1);
				tokenOwner.incrementPiecesLost(1);
			}
			else if (tokenOwner == stackOwner) {
				stackOwner.incrementReserveCount(1);
			}
			tileContents.get(i).undraw();//TODO: Verify no further steps are necessary to get rid of a token.
			i++;
		}

		while (stackContents.size() > 5)	{
			stackContents.remove(5); //I'm actually not 100% sure this will work, but it should. Lists are indexed from 0, so 5 is our 6th element and should no longer be on the board.
		}

	}
	public void drawStack() { 
		int i;
		for(i=0; i < 5; i++) {

			switch(i) {
			case 0: stackContents.get(i).draw(i, i); //TODO: Input the double-type coordinates so that each token can successfully draw itself. These should be fixed position- 1(which is 0) is central, 2 is top-left, 3 top-right, 4 bottom-left, and 5 bottom-right.
			break;
			case 1: stackContents.get(i).draw(i, i); //Until we know what coordinate responds to where, this code can't be worked on further.
			break;
			case 2: stackContents.get(i).draw(i, i);
			break;
			case 3: stackContents.get(i).draw(i, i);
			break;
			case 4: stackContents.get(i).draw(i, i);
			break;
			default: break;
			}
		}
	}

	public int getSize() {
		return stackContents.size();
	}
	public ArrayList<Token> getStackContents() {
		return stackContents;
	}
	public void setStackContents(ArrayList<Token> stackContents) {
		this.stackContents = stackContents;
	}
	public ArrayList<Token> splitStackContentsForMove(int i) { 
		ArrayList<Token> movingList;
		movingList = (ArrayList<Token>) stackContents.subList(0, i);
		ArrayList<Token> remainingList;
		remainingList = (ArrayList<Token>) stackContents.subList(i, (stackContents.size()-1)); //remainingList will be the new stack in the old place. Size-1 since indexed at zero. TODO: Test for a null list, might need special logic.
		stackContents = remainingList; //This stack is reduced to whatever wasn't moved. 
		return movingList;

	}
}