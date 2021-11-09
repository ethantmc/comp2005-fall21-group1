import java.util.ArrayList;

public class Stack {
	private ArrayList<Token> stackContents; //This class assumes that stackContents is an ordered list, with 0 through 4 representing the top of the stack, in order.
	//private int stacksize = 0; //This existing will likely cause trouble. We can infer the stack size from stackContents.
	public Stack(ArrayList<Token> stackContents) {
		super();
		this.stackContents = stackContents;
	}
	public void CaptureToken(ArrayList<Token> tileContents) {
		int i = 5;
		Player stackOwner = stackContents.get(0).getOwner(); //We set this value to compare if a removed token should be returned to the reserve or not.
		while( i < this.getSize()) {
			Player tokenOwner; //whoever owns this particular token
			tokenOwner = stackContents.get(0).getOwner(); //arrayList .get() returns a Token object, we call the token method getOwner() to uniquely identify our player. Stack-Token-Player are thus coupled, but stack-token need to be and token needs getOwner().
			if (tokenOwner != stackOwner){
				stackOwner.incrementCapturedCount(1);
				tokenOwner.incrementPiecesLost(1);
			}
			else if (tokenOwner == stackOwner) {
				stackOwner.incrementReserveCount(1);
			}
			stackContents.get(i).undraw();//TODO: Verify no further steps are necessary to get rid of a token.
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
}