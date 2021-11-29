public class Move {

	//private move
	public static void makeAMove(Stack moveFrom, Stack moveTo, int num)
	{
		//TODO Method stub
		moveFrom.moveTokens(num).forEach(i -> moveTo.stackToken(i)); 
		Turn.nextPlayersTurn();
		SetupAGame.getGameUIInstance().updateStats();
	}

	public static void makeAReserveMove(Stack moveToStack) 
	{

		moveToStack.stackToken(Turn.getCurrentPlayer().getReserveToken()); //Driver.getPlayers().get(0).getReserveToken()
		Turn.nextPlayersTurn();
		SetupAGame.getGameUIInstance().updateStats();
		//System.out.println();
	}
}