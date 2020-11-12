/* ********************************************************
 * Name:   Ziyue Liu
 * NetID:  ziyliu2
 * 
 * Description:  Necessary classes of Nimsys, including
 *               all players' user names, family names, 
 *               given names, times that have played and 
 *               wined and win rate respectively. In 
 *               addition, it's an abstract class to run
 *               different players' remove stone method.
 * 
 * Written:       5/28/2019
 * Last updated:  5/30/2019
 * 
 * javac NimPlayer.java
 * java NimPlayer
 * 
 **********************************************************/

import java.io.Serializable;

public abstract class NimPlayer implements Serializable {
	private String userName;
	private String familyName;
	private String givenName;
	private int playTime;
	private int winTime;
	private float winRate;
	
	// Getters and Setters.
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public int getPlayTime() {
		return playTime;
	}
	
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	
	public int getWinTime() {
		return winTime;
	}
	
	public void setWinTime(int winTime) {
		this.winTime = winTime;
	}
	
	public float getWinRate() {
		return winRate;
	}
	
	public void setWinRate(float winRate) {
		this.winRate = winRate;
	}
	
	public NimPlayer(String userName, String familyName, String givenName,
			int playTime, int winTime, float winRate) {
		this.userName = userName;
		this.familyName = familyName;
		this.givenName = givenName;
		this.playTime = playTime;
		this.winTime = winTime;
		this.winRate = winRate;
	}
	
	public String toString(){
		return (userName + "," + givenName + "," + familyName + "," + 
				playTime + " games" + "," + winTime + " wins");
	}
	
	//Method to update player's played time.
	public void increasePlayTime(int play_time) {
		playTime = playTime + play_time;
	}
	
	//Method to update player's time of becoming winner.
	public void increaseWinTime(int win_time) {
		winTime = winTime + win_time;
	}
	
	//Build an abstract class of removeStone.
	public abstract void removeStone();
	
}


