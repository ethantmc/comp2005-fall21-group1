
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
		
		moveToStack.stackToken(Turn.getCurrentPlayer().getReserveToken()); //Driver.getPlayers().get(0).getReserveToken()
		Turn.nextPlayersTurn();
		SetupAGame.getGameUIInstance().updateStats();
		//System.out.println();
	}
}
