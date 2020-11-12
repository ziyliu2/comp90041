/* ********************************************************
 * Name:   Ziyue Liu
 * NetID:  ziyliu2
 * 
 * Description:  Project C is the last one in a series of 
 *               three. Based on project B, this project
 *               has added more functions about record players
 *               statistics and AIplayer.
 * 
 * Written:       5/28/2019
 * Last updated:  5/30/2019
 * 
 * javac Nimsys.java
 * java Nimsys
 * 
 **********************************************************/
import java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;



public class Nimsys {
	private static int arrayLength = 100;
	
	public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args){
		String Order;
		NimPlayer[] players = new NimPlayer[arrayLength];
		String[] orders = {"addplayer", "addaiplayer", "removeplayer", "editplayer", "resetstates", 
				"displayplayer", "rankings", "startgame", "exit"};
		
		File playerFile = new File("players.dat");
		if(playerFile.exists()) {
			try {
				FileInputStream inputData = new FileInputStream("players.dat");
				ObjectInputStream inputStream = new ObjectInputStream(inputData);
				players = (NimPlayer[]) inputStream.readObject();

				inputStream.close();
				
			} catch (IOException e) {
                e.printStackTrace();
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } 
		}
		
		// Welcome message
		System.out.println("Welcome to Nim");
		System.out.println();
		
		// Running program constantly except user input "exit".
		do {
			System.out.print("$");
			String input = keyboard.nextLine();
			// Split the input and put in order array respectively.
			String[] order = input.split(" |,");
			Order = order[0];
			
			try {
				boolean contain = false;
				for(int i = 0; i < orders.length; i++) {
					if(Order.equals(orders[i])) {
						contain = true;
						break;
					}
				}
				
				if(contain == false) {
					throw new InvalidCommandException(Order);
				}
			} catch(InvalidCommandException e) {
				System.out.println(e.getMessage());
				System.out.println();
			}
			
			// Add player into players array.
			if(Order.equals("addplayer")) {
				
				try {
					
					if(order.length == 4) {
						addPlayer(players,order);
						System.out.println();
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
				
			}
			
			// Add player into players array.
			if(Order.equals("addaiplayer")) {
							
				try {
								
					if(order.length == 4) {
						addAIPlayer(players,order);
						System.out.println();
					} else {
						throw new InvalidArgsException();
					}
								
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
							
			}
			
			// Remove player from players array.
			if(Order.equals("removeplayer")) {
				
				try {
					
					if(order.length == 1 || order.length == 2) {
						removePlayer(players,order);
						System.out.println();
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
				
			}
			
			// Edit player from players array.
			if(Order.equals("editplayer")) {
				
				try {
					
					if(order.length == 4) {
						editPlayer(players,order);
						System.out.println();
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
				
			}
			
			// Reset players' statuses in players array.
			if(Order.equals("resetstats")) {
				
				try {
					
					if(order.length == 1 || order.length == 2) {
						resetStats(players,order);
						System.out.println();
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
				
			}
			
			// Display players in players array.
			if(Order.equals("displayplayer")) {
				
				try {
					
					if(order.length == 1 || order.length == 2) {
						sortUserName(players);
						displayPlayer(players,order);
						System.out.println();
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
				
			}
			
			// Sort players in players array according to win rate.
			if(Order.equals("rankings")) {
				
				try {
					
					if(order.length == 1 || order.length == 2) {
						sortUserName(players);
						
						if(order.length == 1) {
							//Sort in descending order.
							playerRankingDescendingly(players);
						} else if(order[1].equals("desc")) {
							playerRankingDescendingly(players);
						} else if(order[1].equals("asc")) {
							// Sort in ascending order.
							playerRankingAscendingly(players);
						}
						
						System.out.println();
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
				
			}
			
			// Play the game of Nim.
			if(Order.equals("startgame")) {
				
				try {
					
					if(order.length == 5) {
						int stoneQuantity = Integer.parseInt(order[1]);
						int maxRemove = Integer.parseInt(order[2]);
						String player1 = order[3];
						String player2 = order[4];
						NimPlayer p1 = null, p2 = null;
						
						int i;
						final String reg ="\\d+\\.{0,1}\\d*";
						boolean playerExist = false;
						boolean stoneNum = order[1].matches(reg);
						boolean upperBound = order[2].matches(reg);
						boolean exist = false;
						boolean digital = true;
						
						// Judge whether stone number and upper bound have input correctly.
						if(digital == true && stoneNum == false) {
							digital = false;
						}
						
						if(digital == true && upperBound == false) {
							digital = false;
						}
						
						// Judge whether participants have recorded in players array.
						if(digital == true && exist == false) {
							for(i = 0; i < players.length; i++) {
								if(players[i] != null && player1.equals(players[i].getUserName())) 
								{
									p1 = players[i];
									playerExist = true;
									exist = true;
									break;
								}	
							}
						}	
						if(digital == true && exist == false) {
							System.out.println("One of the players does not exist.");
								System.out.println();
								playerExist = false;
						}
						
						// Judge whether players can start the game.
						if(digital == true && playerExist == true) {
							exist = false;
							if(exist == false) {
								for(i = 0; i < players.length; i++) {
									if(players[i] != null && player2.equals(players[i].getUserName())) 
									{
										p2 = players[i];
										playerExist = true;
										exist = true;
										break;
									}	
								}
							}	
							if(exist == false) {
								System.out.println("One of the players does not exist.");
									System.out.println();
									playerExist = false;
							}	
						}
						
						if(playerExist == true) {
							NimGame game = new NimGame(stoneQuantity, maxRemove, p1, p2);
						}
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}
	
			}
			
			// Exit game.
			if(Order.equals("exit")) {
				
				try {
					
					if(order.length == 1) {
						
						try {
							FileOutputStream outputData = new FileOutputStream("player.dat");
							ObjectOutputStream outputStream = new ObjectOutputStream(outputData);
							outputStream.writeObject(players);
							outputStream.flush();
							outputStream.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
						System.out.println();
						System.exit(0);
						
					} else {
						throw new InvalidArgsException();
					}
					
				} catch(InvalidArgsException e) {
					System.out.println(e.getMessage());
					System.out.println();
				}

			}	
		}while(!Order.equals("exit"));
	}
	
	public static void addPlayer(NimPlayer[] playerArray, String[] orderArray) {
		String target = orderArray[1];
		int i;                           //index
		boolean exist = false;
		
		// Search whether the player has already existed.
		for(i = 0; i < playerArray.length; i++) {
			if(playerArray[i] != null) {
				if(target.equals(playerArray[i].getUserName())) {
				    System.out.println("The player already exists.");
				    exist = true;
				    break;
			    } else {
			    	break;
			    }
			}
		}
		
		if(exist == false) {
			// Find a null element to fill player's details in.
			for(i = 0; i < playerArray.length; i++) {
				if(playerArray[i] == null) {
					playerArray[i] = new NimHumanPlayer(orderArray[1], orderArray[2],
							orderArray[3], 0, 0, 0);
					break;
				}	
			}
	    }
	}
	
	public static void addAIPlayer(NimPlayer[] playerArray, String[] orderArray) {
		String target = orderArray[1];
		int i;                           //index
		boolean exist = false;
		
		// Search whether the player has already existed.
		for(i = 0; i < playerArray.length; i++) {
			if(playerArray[i] != null) {
				if(target.equals(playerArray[i].getUserName())) {
				    System.out.println("The player already exists.");
				    exist = true;
				    break;
			    } else {
			    	break;
			    }
			}
		}
		
		if(exist == false) {
			// Find a null element to fill player's details in.
			for(i = 0; i < playerArray.length; i++) {
				if(playerArray[i] == null) {
					playerArray[i] = new NimAIPlayer(orderArray[1], orderArray[2],
							orderArray[3], 0, 0, 0);
					break;
				}	
			}
	    }
	}
	
	public static void removePlayer(NimPlayer[] playerArray, String[] orderArray) {
		int i;
		
		//Judge whether the user wants to remove all players.
		if(orderArray.length != 1) {
			String target = orderArray[1];
			boolean exist = false;
			
			// Judge whether target player is exist.
			// If find it, remove.
			if(exist == false) {
				for(i = 0; i < playerArray.length; i++) {
					if(playerArray[i]!=null && target.equals(playerArray[i].getUserName())) 
					{
						playerArray[i] = null;
						exist = true;
						break;
				    }
				}
			}
			
			// Doesn't find target player, show prompt.
			if(exist == false) {
				System.out.println("The player does not exist.");
			}
			
		} else {
			// Remove all players.
			System.out.println("Are you sure you want to remove "
					+ "all players? (y/n)");
			String answer = keyboard.nextLine();
			if(answer.equals("y")) {
				for(i = 0; i < playerArray.length; i++) {
					playerArray[i] = null;
			    }
			}
		}
	}
	
	public static void editPlayer(NimPlayer[] playerArray, String[] orderArray) {
		
		if(orderArray.length != 1) {
			
			String target = orderArray[1];
			for(int i = 0; i < playerArray.length; i++) {
				
				if(playerArray[i] != null) {
					// Aim to edit target player.
					if(target.equals(playerArray[i].getUserName())) {
						playerArray[i].setFamilyName(orderArray[2]);
						playerArray[i].setGivenName(orderArray[3]);
					    break;
				    } else {
				    	// Reset all players.
				    	System.out.println("The player does not exist.");
						break;
				    }
				}
				
			}
		}
		
	}
	
	public static void resetStats(NimPlayer[] playerArray, String[] orderArray) {
		int i;
		
		if(orderArray.length != 1) {
			String target = orderArray[1];
			boolean exist = false;
			if(exist == false) {
				for(i = 0; i < playerArray.length; i++) {
					if(playerArray[i]!=null && target.equals(playerArray[i].getUserName())) 
					{
						playerArray[i].setPlayTime(0); 
						playerArray[i].setWinTime(0);
						playerArray[i].setWinRate(0);
						exist = true;
						break;
				    }
				}
			}
			if(exist == false) {
				System.out.println("The player does not exist.");
			}
		} else {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String answer = keyboard.nextLine();
			if(answer.equals("y")) {
				for(i = 0; i < playerArray.length; i++) {
					if(playerArray[i] != null) {
						playerArray[i].setPlayTime(0); 
						playerArray[i].setWinTime(0);
						playerArray[i].setWinRate(0);
					}
				}
			}
		}
	}
	
	public static void displayPlayer(NimPlayer[] playerArray, String[] orderArray) {
		int i;
		if(orderArray.length != 1) {
			String target = orderArray[1];
			boolean exist = false;
			if(exist == false) {
				for(i = 0; i < playerArray.length; i++) {
					if(playerArray[i]!=null && target.equals(playerArray[i].getUserName())) 
					{
						System.out.println(playerArray[i].toString());
						exist = true;
						break;
				    }
				}
			}
			if(exist == false) {
				System.out.println("The player does not exist.");
			}
		} else {
			for(i = 0; i < playerArray.length; i++) {
				if(playerArray[i] != null) {
					System.out.println(playerArray[i].toString());
				}
			}
		}
	}
	
	public static void playerRankingDescendingly(NimPlayer[] rank) {
		NimPlayer temp;
		int index;
		for(int i = 0; i < rank.length; i++){
			if (rank[i] != null) {
				temp = rank[i];
				index = i;
				for(int j = i + 1; j < rank.length; j++){
					if(rank[j] != null && temp.getWinRate() < rank[j].getWinRate()){
						temp = rank[j];
						index = j;
					}
				}
			
				// Swap elements
				rank[index] = rank[i];
				rank[i] = temp;
			}	
		}
		
		int count = 0;
		for(int i = 0; i < rank.length; i++) {
			if(rank[i] != null) {
				float rate = rank[i].getWinRate();
				// Make sure all outputs have the save format.
				if(rate == 0) {
					if(rank[i].getPlayTime() < 10) {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "   | 0" + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					} else {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "   | " + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					}
				} else if(rate > 0 & rate < 1) {
					if(rank[i].getPlayTime() < 10) {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "  | 0" + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					} else {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "  | " + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					}
				} else if(rate == 1) {
					if(rank[i].getPlayTime() < 10) {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + " | 0" + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					} else {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + " | " + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					}
				}
				count ++;
			}
			if(count == 10) {
				break;
			}
		}
	}
	

	
	public static void playerRankingAscendingly(NimPlayer[] rank) {
		NimPlayer temp;
		int index;
		for(int i = 0; i < rank.length; i++){
			if (rank[i] != null) {
				temp = rank[i];
				index = i;
				for(int j = i + 1; j < rank.length; j++){
					if(rank[j] != null && temp.getWinRate() > rank[j].getWinRate()){
						temp = rank[j];
						index = j;
					}
				}
			
				// Swap elements
				rank[index] = rank[i];
				rank[i] = temp;
			}	
		}
		
		int count = 0;
		for(int i = 0; i < rank.length; i++) {
			if(rank[i] != null) {
				float rate = rank[i].getWinRate() ;
				
				/*
				 * Divide the win rate into three different situation,
				 * rate = 0, 0 < rate < 1 and rate = 1, to make sure 
				 * that all players have the same format of output.
				 */
				if(rate == 0) {
					if(rank[i].getPlayTime() < 10) {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "   | 0" + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					} else {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "   | " + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					}
				} else if(rate > 0 & rate < 1) {
					if(rank[i].getPlayTime() < 10) {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "  | 0" + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					} else {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + "  | " + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					}
				} else if(rate == 1) {
					if(rank[i].getPlayTime() < 10) {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + " | 0" + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					} else {
						System.out.println(Math.round(rank[i].getWinRate()*100) + "%" + " | " + 
								rank[i].getPlayTime() + " games | " + rank[i].getGivenName() + 
								" " + rank[i].getFamilyName());
					}
				}
				count ++;// Record how many players have been illustrated.
			}
			// Maximum amount of illustrated players is 10.
			if(count == 10) {
				break;
			}
		}	
	}
	
	// Operate Nim. 
	
	public static void sortUserName(NimPlayer[] playerArray) {
		NimPlayer temp;
		int index;
		for(int i = 0; i < playerArray.length; i++){
			if (playerArray[i] != null) {
				temp = playerArray[i];
				index = i;
				for(int j = i + 1; j < playerArray.length; j++){
					if(playerArray[j] != null) {
						if(temp.getUserName().compareTo(playerArray[j].getUserName()) > 0){
							temp = playerArray[j];
							index = j;
						}
					}
				}
			
				// Swap elements
				playerArray[index] = playerArray[i];
				playerArray[i] = temp;
			}	
		}
	}
}