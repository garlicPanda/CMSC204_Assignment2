/**
 * Exception class when an dequeue is called on empty queue
 * @author vanessa
 *
 */
public class QueueUnderflowException extends RuntimeException{
	/**
	 * create an QueueUnderflowException with default message
	 */
	
	QueueUnderflowException(){
		super("Enqueue method is called on a full queue");
	}
	
	/**
	 * create an QueueUnderflowException with custom message
	 * @param message
	 */
	
	QueueUnderflowException(String message){
		super(message);
	}

}
