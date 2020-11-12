/* ********************************************************
 * Name:   Ziyue Liu
 * NetID:  ziyliu2
 * 
 * Description:  This class contains the method of playing 
 *               Nim for human player.
 * 
 * Written:       5/28/2019
 * Last updated:  5/30/2019
 * 
 * javac NimHumanPlayer.java
 * java NimHumanPlayer
 * 
 **********************************************************/

public class NimHumanPlayer extends NimPlayer {

	public NimHumanPlayer(String userName, String familyName, String givenName, int playTime, int winTime,
			float winRate) {
		super(userName, familyName, givenName, playTime, winTime, winRate);
	}

	public void removeStone() {
		
		int remainNum = NimGame.getStoneNum();
		int removeNum;
		String remove;
		int validMove;
		
		//Judge whether participant's input is valid, If not, Show a prompt and
        //let it input again.
        boolean numJudge = false;
        do {
	        System.out.print(remainNum + " stones left:");
	        
		    for(int num = 0; num < remainNum; num++) {
			    System.out.print(" *");
	        }
		    
		    System.out.println();
        	System.out.println(this.getGivenName() + "'s turn - remove how many?");
        	remove = Nimsys.keyboard.nextLine();
        	removeNum = Integer.parseInt(remove);
        	System.out.println();
        	
        	//Judge whether the upper bound should be the left stones or the
        	//upper bound that has set at the beginning.
        	if(NimGame.getUpperBound() < NimGame.getStoneNum()) {
        		validMove = NimGame.getUpperBound();
        	} else {
        		validMove = NimGame.getStoneNum();
        	}
        	
        	//Judge whether a move is a valid move.
        	try {
        		if(removeNum >= 1 && removeNum <= validMove) {
        			remainNum = remainNum - removeNum;
        			numJudge = true;
        			NimGame.setStoneNum(remainNum);
        			
        		} else {
        			throw new InvalidMoveException(validMove);
        		}
        	} catch(InvalidMoveException e) {
				System.out.println(e.getMessage());
				System.out.println();
			} 
        	
        } while (numJudge == false);	

	}

}
