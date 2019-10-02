/* 
 * Kiana Pazdernik
 * CSC 115 Assignment 3: Calculator 
 * November 4, 2018
 *
 */
 // StackEmptyException is used when an exception is thrown 
 // The exception will occur if the stack is empty 
 public class StackEmptyException extends RuntimeException
 {
 	// When an exception is thrown
 	// It calls this class which is extended from Runtime Exception
 	// And gives the exception in the terminal
 	public StackEmptyException()
	{
		super();
	}
	
	// When an exception is thrown
	// Gives an exception with a message
	public StackEmptyException(String msg)
	{
		super(msg);
	}
 }