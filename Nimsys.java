//Ying HU, 26/05/2017
//ID:811483
//This is the Nimsys to run the main method

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Nimsys {
	
	enum Command { ADDAIPLAYER, ADDPLAYER, REMOVEPLAYER, EDITPLAYER, RESETSTATS, 
		DISPLAYPLAYER, RANKINGS, STARTGAME, EXIT };
	public static NimPlayer[] player = new NimPlayer[100]; //set the maximum 
	public static int count = -1; // count the number of players 

	public static void main(String[] args) {
		Scanner inputStream = null;
		PrintWriter outputStream =  null;
        File statis = new File("players.dat.txt");
        try {
        	statis.createNewFile(); //create player.dat.txt file if it does not exist
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
        
        try {
        	inputStream =  new Scanner(new FileInputStream("players.dat.txt"));
        }
        catch (IOException e) {
        	System.out.println("File player.dat.txt not found or could not open");
        	System.exit(0);
        } //open the file for reading 
        
        String line=null;
        String[] playerStat = new String[7];
        while (inputStream.hasNext()) {
        	count = count +1;
        	line = inputStream.nextLine();
        	playerStat = line.split(" ");
        	if (playerStat[6].equals("H")){
        		player[count] = new NimHumanPlayer(playerStat[0], playerStat[1], playerStat[2],
            			Integer.parseInt(playerStat[3]),Integer.parseInt(playerStat[4]),Double.parseDouble(playerStat[5]));
        	}
        	else {
        		player[count] = new NimAIPlayer(playerStat[0], playerStat[1], playerStat[2],
            			Integer.parseInt(playerStat[3]),Integer.parseInt(playerStat[4]),Double.parseDouble(playerStat[5]));
        	}
        } //load players' statistics into player array and creat NimAIpayer or NimHumanPlayer objects
        inputStream.close();
        
		String[] comInput;
		String[] contentInput = new String[3]; //initialize the array
	    Command com; //to use in command switch statement 
	    
		boolean flag = true; //determine the start and termination of the system
		Scanner keyboard = new Scanner(System.in);
		Nimsys nim = new Nimsys();
		int index=0; //index corresponding to the username in the input command
		NimGame game = new NimGame(); // NimGame instance

		System.out.println("Welcome to Nim");
		
		while (flag) {
			System.out.println();
			System.out.print("$");
			
			comInput = keyboard.nextLine().split(" "); //split the input command line to array
			
			try {     //catch wrong command exception
			com = Command.valueOf(comInput[0].toUpperCase());//get the correct command line
			
			//check existence of user and print error message
			if (comInput.length>1) {
				if (com!=Command.ADDPLAYER && com!=Command.STARTGAME && com!=Command.RANKINGS
						&& com!=Command.EDITPLAYER && com!=Command.ADDAIPLAYER) {
					contentInput = comInput[1].split(","); //get the contents in the optional parameter in command line
					index = nim.checkUsername(comInput[1].split(",")[0]);
					if (index==-1) {
						System.out.println("The player does not exist.");
						continue;
					}
				}
			}
			
			switch (com) { //determine which command is used 
			
			case ADDPLAYER:
				nim.addPlayer(comInput,true); //add human player
			    break;
			    
			case ADDAIPLAYER:
				nim.addPlayer(comInput,false); //add AI player
				break;
			    
			case REMOVEPLAYER: 
				nim.removePlayer(comInput, keyboard, index);
				break;
				
			case EDITPLAYER:
				nim.editPlayer(comInput);
				break;
				
			case RESETSTATS:
				nim.resetStats(comInput, keyboard, index);
				break;
				
			case DISPLAYPLAYER:
				nim.displayPlayer(comInput, index);
				break;
				
			case RANKINGS:
				if (comInput.length==1) {
					nim.ranking(" ");
				}
				else
					nim.ranking(comInput[1]);
				break;
				
			case STARTGAME:
				nim.startGame(comInput,keyboard,game);
				break;
				
			case EXIT: 
				System.out.println();
				flag=false; //terminate the system
				outputStream = new PrintWriter(new FileOutputStream("players.dat.txt"));
				for (int i=0;i<=count;i++) {
					outputStream.println(player[i].getInfo());
				}
				outputStream.close();
				break;
			
			default: break;
				
			}
		} catch (IllegalArgumentException e) {
			System.out.println("\'"+ comInput[0] + "\'" + " is not a valid command.");
		}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	
	}
	
	//This method is to check whether the username is correct and return the index of the user in the player array
	public int checkUsername(String name) {
		for (int i=0; i<=count; i++) {
			if (player[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	// This method is to proceed addplayer command
	// boolean type in parameter list is to decide whether human or AI player, true for human, false for AI.
	//throw exception to handle incorrect number of arguments input
	public void addPlayer(String[] comInput,boolean type) throws Exception {
		if (comInput.length==1) {
			throw new Exception("Incorrect number of arguments supplied to command.");
		}
		String[] contentInput = comInput[1].split(",");
		if (contentInput.length<3) {
			throw new Exception("Incorrect number of arguments supplied to command.");
		}
		if (count>=0) {
			int index = checkUsername(contentInput[0]);
			if (index!=-1) {
				System.out.println("The player already exists.");
				return;
			}
		}
		count = count + 1;
		if (type)
			player[count] = new NimHumanPlayer(contentInput[0],contentInput[1],contentInput[2]);
		else
			player[count] = new NimAIPlayer(contentInput[0],contentInput[1],contentInput[2]);
		
	}
	
	//This method is to proceed editplayer command
	//throw exception to handle incorrect number of arguments input
	public void editPlayer(String[] comInput) throws Exception {
		if (comInput.length==1) {
			throw new Exception("Incorrect number of arguments supplied to command.");
		}
		String[] contentInput = comInput[1].split(",");
		if (contentInput.length<3) {
			throw new Exception("Incorrect number of arguments supplied to command.");
		}
		int index = checkUsername(contentInput[0]);
		if (index!=-1) {
			player[index].editPlayer(contentInput[1], contentInput[2]);
		}
		else {
			System.out.println("The player does not exist.");
		}
	}
	
	//This method is to proceed removeplayer command
	public void removePlayer(String[] comInput,Scanner keyboard,int index) {
		if (comInput.length==1) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String confirmation = keyboard.nextLine();
			if (confirmation.equals("y")) {
				player = new NimPlayer[100];
				count = -1;
				return;
			}
			else
				return;
		}//remove all players or not
		
		player[index] = null;
		player[index] = player[count];
		player[count] = null; 
		count = count - 1;// move the last player in the collection to the removed position
	}
	
	//This method is to proceed resetstats command
	public void resetStats(String[] comInput,Scanner keyboard,int index) {
		if (comInput.length==1) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String confirmation = keyboard.nextLine();
			if (confirmation.equals("y")) {
				for (int i=0; i<=count ; i++) {
					player[i].resetStats();
				}
				return;
			}
			else
				return;
		}// reset statiscs for all players or not 
		
		player[index].resetStats();
	}
	
	//This method is to proceed displayplayer command
	public void displayPlayer(String[] comInput, int index) {
		if (count!=-1) {
			if (comInput.length==1) {
				SortPlayer disp = new SortPlayer(player,count);
				disp.sort("username"," ");
				for (int i=0; i<=count ; i++) {
					player[i].dispPlayer();
				}
				return;
			}
			player[index].dispPlayer();
			return;
		}//display players when there are players in the list
		
		else 
			return;
	}
	
	//This method is to proceed rankings command
	public void ranking(String order) {
		SortPlayer rankResult = new SortPlayer(player,count);
		rankResult.sort("ratio",order);// sort the player based on ration first and the small value appears first
		if (order.equals("asc")) {
			for (int j=0; j<=count && j<=10; j++) {
				player[j].dispRank();
			}
		}
		else {
			for (int j=count; j>=0 && j>count-10; j--) {
				player[j].dispRank();
			}
		}	
	}
	
	//This method is to proceed startgame command
	//throw exception to handle incorrect number of arguments input
	public void startGame(String[] comInput, Scanner keyboard, NimGame game) throws Exception {
		if (comInput.length==1) {
			throw new Exception("Incorrect number of arguments supplied to command.");
		}
		String[] content = comInput[1].split(",");
		if (content.length<4) {
			throw new Exception("Incorrect number of arguments supplied to command.");
		}
		int indexP1 = checkUsername(content[2]);
		int indexP2 = checkUsername(content[3]);
		if (indexP1==-1 || indexP2==-1) {
			System.out.println("One of the players does not exist.");
			return;
		} //check whether both two users exist
		
		game.setNimGame(Integer.parseInt(content[0]), Integer.parseInt(content[1]),
				player[indexP1],player[indexP2]);
		game.playGame(keyboard);
	}
}
