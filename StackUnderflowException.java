/**
 * exception class when an pop is called on empty stack
 * @author vanessa
 *
 */
public class StackUnderflowException extends RuntimeException{
	
	/**
	 * create an StackUnderflowException with default message
	 */
	StackUnderflowException(){
		super("Push method is called on a full stack");
	}
	
	/**
	 * create an StackUnderflowException with custom message
	 * @param message
	 */
	StackUnderflowException(String message){
		super(message);
	}

}
