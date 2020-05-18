
public class Board {
 
	private char[][] board;
	private char playerMark;
	private char opponentMark;
	private boolean isYourTurn;
	
	public Board() 
	{
		board = new char [3][3];
		initializeBoard();
		isYourTurn = false;
	}
	
	public void setPlayerMark(char mark)
	{
		playerMark = mark;
	}
	
	public void setOpponentMark(char mark)
	{
		opponentMark = mark;
	}
	
	public char getOpponentMark()
	{
		return opponentMark;
	}
	
	public void setTurnState(boolean turn)
	{
		isYourTurn = turn;
	}
	
	public boolean getTurnState()
	{
		return isYourTurn;
	}
	
	public char getPlayerMark()
	{
		return playerMark;
	}
	
	private void initializeBoard() 
	{
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				board[i][j] = ' ';
	}
	
	public void printBoard()
	{
		for(int i=0; i<3; i++)
		{System.out.print('|');
			for(int j=0; j<3; j++)
				System.out.print(board[i][j] + "|");
			System.out.print("\n");
		}
	}
	
	public Boolean changeBoard(int row, int col, char mark)
	{
		if(row >= 0 && row <= 3 &&
		   col >= 0 && row <= 3 &&
		   board[row][col] == ' ')
		{
			board[row][col] = mark;
			return true;
		}
		return false;			
	}

	private boolean checkThreeChars(char pos1, char pos2, char pos3)
	{
		return ((pos1 == pos2 && pos2 == pos3 && pos1 != ' '));
	}
	
	private char checkForWinChar()
	{
		for(int i=0; i<3; i++)
		{
			if( checkThreeChars(board[i][0], board[i][1], board[i][2]) )
				return board[i][0];
			if( checkThreeChars(board[0][i], board[1][i], board[2][i]) )
				return board[0][i];
		}
		if( checkThreeChars(board[0][0], board[1][1], board[2][2]) )
			return board[0][0];
		if( checkThreeChars(board[2][0], board[1][1], board[0][2]) )
			return board[1][1];
		
		return ' ';
	}
	
   public int checkForWin()
   {
	   /*
	    * 0 - nie koniec
	    * 1 - wygrana
	    * 2 - przegrana
	    * 3 - remis
	    */
	   
	   char aux = checkForWinChar();
	   if (aux == playerMark)
		   return 1;
	   if (aux != playerMark &&  aux != ' ')
		   return 2;
	   for(int i=0; i<3; i++)
		   for(int j=0; j<3; j++)
			   if(board[i][j] == ' ')
				   return 0;
	   return 3;	   
   }
   
   public void printResult()
   {
	   switch(checkForWin()) {
	   case 1: System.out.println("Wygrana"); 	break;
	   case 2: System.out.println("Przegrana"); break;
	   case 3: System.out.println("Remis");		break;
	   }
   }
   
   
   

}
