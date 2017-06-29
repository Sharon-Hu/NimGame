/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.3, 2.4 and 2.5 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
	
	Coded by: Jin Huang
	Modified by: Jianzhong Qi, 29/04/2015
*/
//Ying HU, 26/05/2017
//ID:811483
//This is the derived class of NimPlayer and is used to store AI player

import java.util.Scanner;

public class NimAIPlayer extends NimPlayer implements Testable {
		
    private int numberToRemove;
    
	public NimAIPlayer(String userName, String familyName, String givenName) {
			super(userName, familyName, givenName);	
	}//constructor to call constructor in base class
	
	public NimAIPlayer(String userName, String familyName, String givenName, int numberOfGames,
			int numberOfWins, double ratio) {
		super(userName, familyName, givenName, numberOfGames, numberOfWins, ratio);
	}//constructor to call constructor in base class
	
	//this is the overrided removestone method and it shows victory guaranteed strategy
	//if rival player is left with k(M+1)+1, then AI will win
	public int removeStone(Scanner keyboard, int stoneNumber, int upperBound) {
		System.out.println(getGivenName() + "'s turn - remove how many?");
		numberToRemove = (stoneNumber-1) % (upperBound+1); 
		if (numberToRemove==0) //in case of the reminder is 0, the AI player will remove 1 stone
			return 1;
		
		return numberToRemove;
		
	}
	
	//return all statistic and "A" to show it is AI player
	public String getInfo() {
		return getStat()+ " " + "A";
	}
	
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}
