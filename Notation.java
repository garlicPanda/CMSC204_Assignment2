/**
 * The Notation class will have a method infixToPostfix to convert infix notation
 * to postfix notation that will take in a string and return a string
 * @author vanessa
 *
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Notation extends Object{
	
	/**
	 * Establish the presedence between '+' '-' '*' '/'
	 * if it is another operator or value, return-1
	 * @param c
	 * @return an int
	 */
	
	public static int precedence(char c) {
		if (c == '+' || c == '-') {
			return 3;
		}else if (c == '*' || c == '/') {
			return 2;
		}else {
			return -1;
		}
			
	}
	
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException
	 * @throws QueueOverflowException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException,QueueOverflowException,
	StackOverflowException, StackUnderflowException{
		MyStack<Character> operatorStack = new MyStack<Character>(30);
		MyQueue<Character> postfixQueue = new MyQueue<Character>(30);
		
		int paren1 = 0;
		int paren2 = 0;
		
		for (int i = 0; i < infix.length(); i++) {
			char nextDigit = infix.charAt(i);
			
			switch( nextDigit){
				
				//if the current character is a whitespace
				case ' ': break;
				
				//if the current character is a digit
				case '0': case '1': case '2': case '3': case '4': 
				case '5': case '6': case '7': case '8': case '9':
					postfixQueue.enqueue(nextDigit);
					break;
					
				//if the current character is a open parentheses.
				case '(':
					paren2 ++;
					operatorStack.push(nextDigit);
					break;
					
				//if the current characte is a operatior
				case '+': case '-': case '*': case '/':
					while (!operatorStack.isEmpty() && precedence(nextDigit) <= precedence(operatorStack.top())){
						
						char operator = operatorStack.top();
						postfixQueue.enqueue(operator);
					}
					operatorStack.push(nextDigit);
					break;
					
					//if the current character is a close parentheses;
				case ')':
					paren1 ++;
					while(!operatorStack.isEmpty() && operatorStack.top() !='(') {
						char topOperator = operatorStack.pop();
						postfixQueue.enqueue(topOperator);
					}
					
					if (operatorStack.size() > 1 && operatorStack.top()== '(')
						operatorStack.pop();
					break;
				
			}
		}
		
		if (paren2 != paren1)
			throw new InvalidNotationFormatException("No left prentheses");
		
		while(!operatorStack.isEmpty()) {
			
			if (operatorStack.top() == '(')
				operatorStack.pop();
			else {
				char TopOperator = operatorStack.pop();
				postfixQueue.enqueue(TopOperator);
			}
		}
		return postfixQueue.toString();
	}
	
	/**
	 * Convert an Postfix ecpression into a infix ecpression
	 * @param postfix
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException,
	StackOverflowException, StackUnderflowException{
		
		MyStack<String> myStack = new MyStack<>(30);
		
		//read the postfix expression from left to right
		for (int i = 0; i < postfix.length(); i++) {
			char nextDigit = postfix.charAt(i);
			
			switch(nextDigit) {
			
				//if the current character is white space
			case ' ': break;
			
				//if the current character is a digit
			case '0': case '1': case '2': case '3': case '4': 
			case '5': case '6': case '7': case '8': case '9':
				myStack.push(nextDigit + "");
				break;
				//if the current character is an operatir
			case '+': case '-': case '*': case '/':
				
				//if there are fewer than 2 values throw an error
				if (myStack.size() < 2)
					throw new InvalidNotationFormatException("Error");
				else {
					//pop the top2 values from the stack
					String opt2 = myStack.top();
					myStack.pop();
					String opt1 = myStack.top();
					myStack.pop();
					
					//create a string with 1st value and then the operator and then the 2nd value
					String finalExp = "(" + opt1 + nextDigit + opt2 + ")";
					
					//push the resulting string back to the stack
					myStack.push(finalExp);
				}break;
			
			}
		}
		if (myStack.size() > 1)
			throw new InvalidNotationFormatException("Error");
		else {
			return myStack.top();
		}
	}
	
	/**
	 * Evaluates an Postfix ecpression from a string to a double
	 * @param postfixExpr
	 * @return the evalulation of the postfix expression as a double
	 * @throws InvalidNotationFormatException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException,
	StackOverflowException, StackUnderflowException{
		int opt2 = 0;
		int opt1 = 0;
		
		MyStack<Integer> myStack = new MyStack<>(30);
		
		for (int i = 0; i < postfixExpr.length(); i++) {
			
			char nextDigit = postfixExpr.charAt(i);
			
			switch(nextDigit) {
			//if the current character is white space
			case ' ': break;
			
				//if the current character is a digit
			case '0': case '1': case '2': case '3': case '4': 
			case '5': case '6': case '7': case '8': case '9':
				myStack.push(nextDigit - '0');
				break;
				
				//if the current character is an operatir
			case '+':
				if(myStack.size() < 2)
					throw new InvalidNotationFormatException("Error");
				else{
					opt2 = myStack.pop();
					opt1 = myStack.pop();
					myStack.push(opt1 + opt2);
				}break;
			case '-':
				if(myStack.size() < 2)
					throw new InvalidNotationFormatException("Error");
				else{
					opt2 = myStack.pop();
					opt1 = myStack.pop();
					myStack.push(opt1 - opt2);
				}break;
			case '*':
				if(myStack.size() < 2)
					throw new InvalidNotationFormatException("Error");
				else{
					opt2 = myStack.pop();
					opt1 = myStack.pop();
					myStack.push(opt1 * opt2);
				}break;
			case '/':
				if(myStack.size() < 2)
					throw new InvalidNotationFormatException("Error");
				else{
					opt2 = myStack.pop();
					opt1 = myStack.pop();
					myStack.push(opt1 / opt2);
				}break;
			
			}
		}
		
		if (myStack.size() > 1)
			throw new InvalidNotationFormatException("Error");
		return myStack.pop();
	}
	
	/**
	 * Evaluates an infix expression from a string to a double
	 * @param infixExpr
	 * @return the evaluation of the infix expression as a double
	 * @throws InvalidNotationFormatException
	 * @throws QueueOverflowException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static double evalueInfixExpression(String infixExpr) throws InvalidNotationFormatException,QueueOverflowException,
	StackOverflowException, StackUnderflowException{
		
		//convert the infix expression to postfix by calling method
		String postFix = convertInfixToPostfix(infixExpr);
		
		//evalue postfix by calling the evaluate method
		double evaluatePost = evaluatePostfixExpression(postFix);
		
		return evaluatePost;
	}
	

}
