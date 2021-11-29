public class Move {


	public static void makeAMove(Stack moveFrom, Stack moveTo, int num)
	{
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