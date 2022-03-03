/**
 * Exception class when an enqueue is called on full queue
 * @author vanessa
 *
 */
public class QueueOverflowException extends RuntimeException{
	/**
	 * Creates an QueueOverflowException with default message
	 */
	QueueOverflowException(){
		super("Deququ method is called on an empty queue");
	}
	
	/**
	 * Creates an QueueOverflowException with custom message
	 * @param message
	 */
	QueueOverflowException(String message){
		super(message);
	}

}
