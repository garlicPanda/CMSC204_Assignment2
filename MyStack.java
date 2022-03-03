/**
 * 
 * @author vanessa
 *
 */
import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> {
	private int capacity;
	private int index;
	private int front;
	private int rear;
	ArrayList<T> data;
	
	//Default Constructor
	public MyStack() {
		
		capacity = 5;
		index = 0;
		front = 0;
		rear = 0;
		data = new ArrayList<T>(capacity);
	}
	
	//Constructor
	public MyStack(int capacity) {
		
		this.capacity = capacity;
		index = 0;
		front = 0;
		rear = 0;
		data = new ArrayList<T>(capacity);
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return index == 0;
	}
	
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		return (index == capacity);
	}
	
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T pop() throws StackUnderflowException{
		if (isEmpty()) {
			throw new StackUnderflowException("The Stack is empty");
		}else {
			front = (front + 1) % capacity;
			index--;
			
			T pop = data.get(data.size()-1);
			data.remove(data.size()-1);
			return pop;
		}
		
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T top() throws StackUnderflowException{
		if(isEmpty()) {
			throw new StackUnderflowException("The Stack is empty");
		}else {
			
			T top = data.get(data.size()-1);
			return top;
		}
	}
	
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return index;
	}
	
	/**
	 * Adds an elements to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean push(T e) throws StackOverflowException{
		if (isFull()) {
			throw new StackOverflowException("The Stack is full");
		}else {
			data.add(e);
			index++;
			rear = (rear + 1) % capacity;
			return true;
		}
	}
	
	/**
	 * Returns the elements of the Stack of a string from bottom to top, the beginning
	 * of the String is the bottom of the stack
	 * @return an string which represent the objects in the Stack from borrom to top
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
	 * Returns the string representation of the element in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements
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
	 * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	 * is the first element in the Stack
	 * @param list elements to be added to the Stack
	 * @throws StackUnderflowException 
	 */
	@Override
	public void fill(ArrayList<T> list){
		
		ArrayList<T> copy = new ArrayList<>();
		copy.addAll(data);
		
		//add each object of list to this copy list
		for(int i = 0; i < list.size(); i++) {
			
			copy.add(list.get(i));
			
			try {
				push(copy.get(i));
			}catch(StackOverflowException e) {
				e.printStackTrace();
			}
		}
		
	}

}
