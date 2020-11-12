/* ********************************************************
 * Name:   Ziyue Liu
 * NetID:  ziyliu2
 * 
 * Description:  Necessary classes of Nimsys, including
 *               how to play Nim,stones and upper bound 
 *               of each move.
 * 
 * Written:       5/28/2019
 * Last updated:  5/30/2019
 * 
 * javac NimGame.java
 * java NimGame
 * 
 **********************************************************/
public class NimGame {
	private static int stoneNum;
	private static int upperBound;
	
	// Getters and Setters.
	public static int getStoneNum() {
		return stoneNum;
	}
	
	public static void setStoneNum(int remainNum) {
		NimGame.stoneNum = remainNum;
	}
	
	public static int getUpperBound() {
		return upperBound;
	}
	
	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}
	
	// Play the game.
	public NimGame(int stoneNum, int upperBound, NimPlayer player1, NimPlayer player2) {
		super();
		this.stoneNum = stoneNum;
		this.upperBound = upperBound;
		
		System.out.println();
		
		System.out.println("Initial stone count: " + stoneNum);
		
		System.out.println("Maximum stone removal: " + upperBound);
		
		System.out.print("Player 1: ");
		System.out.println(player1.getGivenName() + " " + player1.getFamilyName());
		
	    player1.increasePlayTime(1);
	    
	    System.out.print("Player 2: ");
		System.out.println(player2.getGivenName() + " " + player2.getFamilyName());
		
		player2.increasePlayTime(1);
	    
	    System.out.println();
		    
		int step = 1;
		int r;         //rent
		String currentPlayerGivenName,currentPlayerFamilyName;
		float player1WinRate, player2WinRate; 
		
		while(this.stoneNum > 0) {
	    	r = step % 2;
	        if (r == 1) {
	        	player1.removeStone();
	        	
		    } else {
		    	player2.removeStone();
		    }

		    // Judge who is the winner.
		    if (this.stoneNum == 0) {
		    	
		        if (r == 0) {
		        	currentPlayerGivenName = player1.getGivenName();
		        	currentPlayerFamilyName = player1.getFamilyName();
		        	player1.increaseWinTime(1);
		        } else {
		        	currentPlayerGivenName = player2.getGivenName();
		        	currentPlayerFamilyName = player2.getFamilyName();
		        	player2.increaseWinTime(1);
	            }
		        
		    	System.out.println("Game Over");
		        System.out.println(currentPlayerGivenName + " " + currentPlayerFamilyName
		        		+ " wins!");
		   	    System.out.println();
	        }
	        step ++;
	    } 
	    
	    //Update participants' win rate after each play.
	    player1WinRate = (float) player1.getWinTime() / player1.getPlayTime();
	    player2WinRate = (float) player2.getWinTime() / player2.getPlayTime();
	    player1.setWinRate(player1WinRate);
	    player2.setWinRate(player2WinRate);

	}
}


	

	




