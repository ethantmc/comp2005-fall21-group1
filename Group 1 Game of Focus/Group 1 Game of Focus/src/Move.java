
public class Move {

	//private move
	public static void makeAMultipleMove(Stack moveFrom, Stack moveTo, int num)
	{
		//TODO Method stub
		Turn.nextPlayersTurn();
		SetupAGame.getGameUIInstance().updateStats();
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
