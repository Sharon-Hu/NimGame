//Ying HU, 26/05/2017
//ID:811483
//This is how the game is played

import java.util.Scanner;

public class NimGame {
	private int stoneNumber, numberToRemove;
	private int upperBound;
	private NimPlayer player1;
	private NimPlayer player2;
	boolean turn;
	
	
	public void setNimGame(int stoneNumber,int upperBound, NimPlayer p1, NimPlayer p2) {
		this.stoneNumber = stoneNumber;
		this.upperBound = upperBound;
		this.player1 = p1;
		this.player2 = p2;
		turn = true;
		gameInfo();
		}
	
	private void gameInfo() {
		System.out.println();
		System.out.println("Initial stone count: " + stoneNumber);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + player1.getFullName());
		System.out.println("Player 2: " + player2.getFullName());
		System.out.println();
	}
	
	public void playGame(Scanner keyboard) {
		printStone(stoneNumber);
		while (stoneNumber>0) {
			 if (turn) {
				 numberToRemove = player1.removeStone(keyboard,stoneNumber,upperBound);
			 }
			 else {
				 numberToRemove = player2.removeStone(keyboard,stoneNumber,upperBound);
			 }
			 
			 try {  //handle exception when the input number of stone to remove is not legitimate
			 if (numberToRemove > upperBound || numberToRemove > stoneNumber || numberToRemove <=0) {
				 System.out.println();
				 if (upperBound > stoneNumber)
					 throw new Exception("Invalid move. You must remove between 1 and " + stoneNumber + " stones.");
				 else
					 throw new Exception("Invalid move. You must remove between 1 and " + upperBound + " stones."); 
			 }
			 }
			 catch (Exception e) {
				 System.out.println(e.getMessage());
				 System.out.println();
				 printStone(stoneNumber);
				 continue;
				 // print error message when input incorrect number to remove
			 }
			 
			 turn = !turn; //make two players take turns to play the game
			 stoneNumber = stoneNumber - numberToRemove;
			 System.out.println();
			 
			 if (stoneNumber > 0) {
				 printStone(stoneNumber);
			 }
			 //To avoid print 0 stones in the output
			 }
				
		System.out.println("Game Over");
		
		 if (turn){
			 if (player2 instanceof NimHumanPlayer) { //avoid reading newline character if last player is AI
				 keyboard.nextLine(); 
			 }
			 System.out.println(player1.getFullName() + " wins!");
		 }
			 
		 else {
			 if (player1 instanceof NimHumanPlayer) { //avoid reading newline character if last player is AI
				 keyboard.nextLine();
			 }
			 System.out.println(player2.getFullName() + " wins!");
		 }
			 
		player1.updateStat(turn);
		player2.updateStat(!turn); //update number of games played and won for two players
		}
	
	private void printStone(int stoneNumber) {
		//this method is to display the left stones	 
		System.out.print(stoneNumber + " stones left:");
		for (int i=0; i<stoneNumber; i++) {
			System.out.print(" *");
		}
		System.out.println();
	}

}
