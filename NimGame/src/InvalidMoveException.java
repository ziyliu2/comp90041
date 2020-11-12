
public class InvalidMoveException extends Exception{
	
	public InvalidMoveException(int validMove) {
		super("Invalid move. You must remove between 1 and " + validMove + " stones.");
	}
	
	public InvalidMoveException(String message) {
		super(message);
	}
}
