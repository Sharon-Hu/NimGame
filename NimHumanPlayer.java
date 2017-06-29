//Ying HU, 26/05/2017
//ID:811483
//This is the derived class of NimPlayer and is used to store human player

import java.util.InputMismatchException;
import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer {
	private int numberToRemove;

	
	public NimHumanPlayer(String userName, String familyName, String givenName) {
		super(userName, familyName, givenName);
	} //constructor to call constructor in base class 
	
	public NimHumanPlayer(String userName, String familyName, String givenName, int numberOfGames,
			int numberOfWins, double ratio) {
		super(userName, familyName, givenName, numberOfGames, numberOfWins, ratio);
	} //constructor to call constructor in base class
	
	//return all statistic and "A" to show it is human player
	public String getInfo() {
		return getStat()+ " " + "H";
	}
	
	//this is the overrided removestone method
	public int removeStone(Scanner keyboard,int stoneNumber, int upperBound) {
		System.out.println(getGivenName() + "'s turn - remove how many?");
		try {
		numberToRemove = keyboard.nextInt();
		return numberToRemove;
		} 
		catch (InputMismatchException e) {
			keyboard.nextLine();
			return -1;
		}
	} //get the number to remove from the input

}
