/* ********************************************************
 * Name:   Ziyue Liu
 * NetID:  ziyliu2
 * 
 * Description:  This class contains the method of playing 
 *               Nim for AI player.
 * 
 * Written:       5/28/2019
 * Last updated:  5/30/2019
 * 
 * javac NimAIPlayer.java
 * java NimAIPlayer
 * 
 **********************************************************/

import java.util.Random;

public class NimAIPlayer extends NimPlayer implements Testable{

	public NimAIPlayer(String userName, String familyName, String givenName, int playTime, int winTime, float winRate) {
		super(userName, familyName, givenName, playTime, winTime, winRate);
		
	}

	public String advancedMove(boolean[] available, String lastMove) {
		
		String move = "";
		return move;
	}

	public void removeStone() {
		
		int remainNum = NimGame.getStoneNum();
		int validMove;
		int removeNum;
		int q = (NimGame.getStoneNum()-1)/(NimGame.getUpperBound()+1);  //quotient
		
		System.out.print(remainNum + " stones left:");
        
	    for(int num = 0; num < remainNum; num++) {
		    System.out.print(" *");    
        }
	    
	    System.out.println();
    	System.out.println(this.getGivenName() + "'s turn - remove how many?");
	    
	    //Judge how many stones should be moved according to the strategy.
	    if(NimGame.getUpperBound() < NimGame.getStoneNum()) {
    		validMove = NimGame.getUpperBound();
    	} else {
    		validMove = NimGame.getStoneNum();
    	}
		
	    if(NimGame.getStoneNum()%(q*(NimGame.getUpperBound()+1)+1) == 2) {
	    	Random r = new Random();
	    	//Get a random number from 1 to the upper bound.
	    	removeNum = r.nextInt(validMove) + 1;
	    } else {
	    	removeNum = (NimGame.getStoneNum()-1)%(NimGame.getUpperBound()+1);
	    }
	    
	    remainNum = remainNum - removeNum;
		NimGame.setStoneNum(remainNum);
		System.out.println();
	}

}
