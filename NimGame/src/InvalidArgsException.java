
public class InvalidArgsException extends Exception{
	
	public InvalidArgsException(){
        super("Incorrect number of arguments supplied to command.");
    }
	
	public InvalidArgsException(String message) {
		super(message);
	}
}
