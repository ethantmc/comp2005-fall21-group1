import java.util.ArrayList;

public class Stack {
private ArrayList<Token> stackContents;
private int Size = 0; //This existing will likely cause trouble. We can infer the stack size from stackContents.
public Stack(ArrayList<Token> stackContents) {
	super();
	this.stackContents = stackContents;
}
public ArrayList<Token> getStackContents() {
	return stackContents;
}
public void setStackContents(ArrayList<Token> stackContents) {
	this.stackContents = stackContents;
}
public int getSize() {
	return Size;
}
public void setSize(int size) {
	Size = size;
}
public void CaptureToken(ArrayList<Token> tileContents) {
//TODO method stub, don't forget to update stack size, as well as stackContents.
}
}