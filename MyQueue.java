/**
 * generic class for Queue data structure that implements QueueInterface
 * @author vanessa
 * @param <T> data type
 */
import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {
	
	//private ArrayList<T> data
	private int capacity;
	private int front;
	private int rear;
	private int index;
	ArrayList<T> data;
	
	//Constructor
	//Initializes a new MyQueue with a default size of 5
	public MyQueue() {
		
		capacity = 5;
		index = 0;
		front = 0;
		rear = 0;
		data = new ArrayList<T>(capacity);
	}
	
	//Constructor
	//Initializes a new MyQueue with a suctom size
	public MyQueue(int capacity) {
		
		this.capacity = capacity;
		index = 0;
		front = 0;
		rear = 0;
		data = new ArrayList<T>(capacity);
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return (index == 0);
	}
	
	/**
	 * Determines of the Queue is full
	 * @return true is Queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		return (index == capacity);
	}
	
	/**
	 * Delete and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException thrown if queue is empty
	 */
	@Override
	public T dequeue() throws QueueUnderflowException{
		
		if (isEmpty()) {
			throw new QueueUnderflowException("The Queue is empty");
			
		}else {
			T dequeue = data.get(0);
			front = (front + 1) % capacity;
			index--;
			data.remove(0);
			return dequeue;
		}
	}
	
	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return index;
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful. false if not
	 * @throws QueueOverlowException throws if queue is full
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException{
		if(isFull()) {
			throw new QueueOverflowException("The Queue is full");
		}else {
			data.add(e);
			index ++;
			rear = (rear + 1) % capacity;
			return true;
		}
	}
	
	/**
	 * Returns the string representation of the elements in the Queue
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString() {
		String str = "";
		ArrayList<T> cdata = data;
		for (int i = 0; i < cdata.size(); i++) {
			str += cdata.get(i).toString();
		}
		
		//Removes leading and trailing spaces 
		str = str.trim();
		return str;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue,
	 * the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String str = "";
		ArrayList<T> cdata = data;
		String prefix = "";
		for(int i = 0; i < cdata.size(); i++) {
			str += prefix + cdata.get(i).toString();
			prefix = delimiter;
		}
		//Removes leading and trailing spaces 
		str = str.trim();
		return str;
	}
	
	/**
	 * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	 * is the first element in the Queue
	 * @param list elements to be added to the Queue
	 */
	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> copy = new ArrayList<>();
		copy.addAll(data);
		
		//add each object of list to this copy list
		for(int i = 0; i < list.size(); i++) {
			
			copy.add(list.get(i));
			
			try {
				enqueue(copy.get(i));
			}catch(QueueOverflowException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	

}
