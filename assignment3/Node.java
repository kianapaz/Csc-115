/* 
 * Kiana Pazdernik
 * CSC 115 Assignment 3: Calculator 
 * November 4, 2018
 *
 */
 // Node class is created to hold an item and a next function 
 public class Node
 {
 	String item;
 	Node next;
 	
 	// Constructor that initializes an item and next function
 	public Node()
 	{
 		item = null;
 		next = null;
 	}
 	// Creates a linked list with this item 
 	public Node(String n)
 	{
 		item = n;
 		next = null;
 	}
 	// This constructor is used to create a new node in a full linked list
 	public Node(String n, Node nextNode)
 	{
 		item = n;
 		next = nextNode;
 	}

 }