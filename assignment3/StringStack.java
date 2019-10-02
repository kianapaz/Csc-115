/* 
 * Kiana Pazdernik
 * CSC 115 Assignment 3: Calculator 
 * Code StringStack class given by Csc 115, edited by Kiana Pazdernik
 * November 4, 2018
 *
 */
 // String Stack class initializes a stack to be used for converting and calculating a Postfix expression
public class StringStack 
{

	public Node top;
	// Initializes the stack
	public StringStack()
	{
		top = null; 
	}
	
	// Checks the stack if is empty
	public boolean isEmpty() 
	{
		return(top == null);
	}

	// Returns the item at the top of the stack then deletes it
	public String pop() 
	{
		// Checks to see if stack is empty
		if(isEmpty())
		{
			throw new StackEmptyException("The stack is empty");
		}
		// Creates a current node as the top node to pop
		// The top node then becomes the next item at the top
		Node current = top;
		top = top.next;
		// The current node is then garbage collected
		return current.item;
	}

	// Returns the item at the top of the stack
	public String peek() 
	{
		// Checks if the stack is empty
		if(isEmpty())
		{
			throw new StackEmptyException("The stack is empty");
		}
		// Returns the item at the top of stack
		return top.item;
	}

	// Puts an item into the top of the stack 
	public void push(String item) 
	{
		// Creates a new node to be placed in stack
		Node newNode = new Node(item);
		// If stack is empty, the new node becomes the top
		if(isEmpty())
		{
			top = newNode;
		}
		else
		{
			// New node becomes the new top
			newNode.next = top;
			top = newNode;
		}
	}

	// Pops out all the items in the stack, the stack is then empty
	public void popAll() 
	{
		while(!isEmpty())
		{
			// A new node becomes the head and is popped each time
			Node itemPopped = top;
			top = top.next;
		}

	}
}
