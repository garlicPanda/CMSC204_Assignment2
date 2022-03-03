/**
 * exception class when an push is called on full stack
 * @author vanessa
 *
 */
public class StackOverflowException extends RuntimeException{
	/**
	 * create an StackOverflowException with default message
	 */
	StackOverflowException(){
		super("A top or pop method is called on an empty stack");
	}
	
	/**
	 * create an StackOverflowException with custom message
	 * @param message
	 */
	StackOverflowException(String message){
		super(message);
	}

}
