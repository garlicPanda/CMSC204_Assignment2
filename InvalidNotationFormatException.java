/**
 * exception class for incorrect notation format
 * @author vanessa
 *
 */

public class InvalidNotationFormatException extends RuntimeException{
	/**
	 * create InvalidNotationFormatException with default message
	 */
	public InvalidNotationFormatException() {
		super("Notation format is incorrect");
	}
	
	/**
	 * create InvalidNotationFormatExceptionwith custom message
	 * @param message
	 */
	public InvalidNotationFormatException(String message) {
		super(message);
	}

}
