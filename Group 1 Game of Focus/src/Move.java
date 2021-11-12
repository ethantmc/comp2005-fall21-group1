
public class Move {

	//private move
	public static void makeAMultipleMove() 
	{
		//TODO Method stub
	}
	public static void makeASingleMove() 
	{
		//TODO Method stub
	}
	public static void makeAReserveMove(Stack moveToStack) 
	{
		
		moveToStack.stackToken(Turn.getCurrentPlayer().getReserveToken()); //The final version will (Turn.getCurrentPlayer().getReserveToken()) as a parameter. Which is the current player. Driver.getPlayers().get(0).getReserveToken()
		Driver.getGameUIInstance().updateStats();
		System.out.println();
	}
}
