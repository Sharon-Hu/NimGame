
//Ying HU, 26/05/2017
//ID:811483
//This is the base class to represent one player in the game

import java.util.Scanner;

public abstract class NimPlayer {
	private String userName, givenName, familyName;
	private int numberOfGames, numberOfWins;
	private double ratio;
	
	public NimPlayer(String userName, String familyName, String givenName) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
		numberOfGames = 0;
		numberOfWins = 0;
		ratio = 0;
	}
	
	public NimPlayer(String userName, String familyName, String givenName, int numberOfGames,
			int numberOfWins, double ratio) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
		this.numberOfGames = numberOfGames;
		this.numberOfWins = numberOfWins;
		this.ratio = ratio;
	}
	
	public boolean equals(String userName) {
		if (userName.equals(this.userName))
			return true;
		else
			return false;
	} //determine whether two player is the same bu their username
	
	public void editPlayer(String familyName, String givenName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}// edit player's givenName and familyName
	
	public void resetStats() {
		numberOfGames = 0;
		numberOfWins = 0;
		ratio = 0;
	}// reset statistics of the player
	
	public void dispPlayer() {
		System.out.println(userName + "," + givenName + "," + familyName + 
				"," + numberOfGames + " games," + numberOfWins + " wins");
	} // display information of the player
	
	public void updateStat(boolean win) {
		numberOfGames = numberOfGames + 1;
		if (win) {
			numberOfWins = numberOfWins + 1;
		}
		ratio = (double)numberOfWins/numberOfGames;
	} //update states of the player after he/she has played one game
	
	//abstract method to hold a place for removestone method in the base class
	public abstract int removeStone(Scanner keyboard,int stoneNumber,int upperboard); 
	
	public String getUserName() {
		return userName;
	} //get userName of the player
	
	public String getFullName() {
		return givenName + " " + familyName;
	} //get full name of the player
	
	public double getRatio() {
		return ratio;
	} //get ratio of the player
	
	 public String getGivenName() {
		 return givenName;
	 } //get given name of the player
	 
	 public String getStat() {
		 return userName + " " + familyName + " " + givenName + " " + numberOfGames +
				 " " + numberOfWins + " " + ratio;
	 } //get all the statistic of the user in one string
	 
	 //abstract method to hold a place for removestone in the base class
	 //this method will return all the statistic of the player in one string plus "H" or "A" to show it is human or AI
	 public abstract String getInfo();
	
	//display the information in rank
	public void dispRank() {
		int percent = (int)Math.round(ratio*100);
		System.out.printf("%-4s | %02d games | "+ givenName + " " + familyName, percent+"%", numberOfGames);
		System.out.println();
	}
	

}
