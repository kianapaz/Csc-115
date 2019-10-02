/* 
 * Kiana Pazdernik
 * V00896924
 * CSC 115 Assignment 2
 * October 6, 2018
 * Method and class provided by CSC 115
 */
 
public class TaskListLL implements TaskList {
    // Creates an initial Linked list with a head
    private TaskListNode head;

	// Constructor that gives head a null value
    public TaskListLL() 
    {
		head = null;
    }
	
	// Returns length of the linked list
    public int getLength() 
    {
    	// A temporary node in a linked list is created to iterate through the list
    	// A counter is created to count each list
    	TaskListNode curr = head;
    	int length = 0;
 		
    	while(curr != null)
    	{
    		// While iterating through the list, length adds one
    		// The current list becomes the next current list
    		length++;
    		curr = curr.next;
    	}
    	// Returning the count of nodes
    	return length;

    }

	// Checks the Linked list if there are any tasks
    public boolean isEmpty() 
    {
        TaskListNode curr = head;
        // If the linked list has a task, return false
        // Otherwise return true
        if(curr != null)
        {
        	return false;
        }
        
        return true;
    }

	// Removes the head of linked list, while keeping the other tasks
    public Task removeHead() 
    {
    	// If the linked list is empty, return null
        if(head == null)
        {
        	return null;
        }
        
        // Creates a temporary node 
        // Changes the head to be the next node in the linked list
        TaskListNode curr = head;
        head = head.next;
        
        // The temporary node becomes equal to null
        // The original head becomes null and is returned
        curr.next = null;
        return curr.task;

    }

    // Removes a specific node in the linked list
    public Task remove(int number) 
    {
    	// Creates two temporary nodes to iterate through the list
        TaskListNode curr = head;
        TaskListNode prev = null;
        
		// In case list is empty then return null
		if(head == null) return null;
		
		// If the head has the number being removed
		if (head.task.getNumber() == number)
		{
			// Head becomes the next node in the list
			head = curr.next;
			return curr.task;
		}
		
		// While the loop continues, ensuring the number is in the loop
		while(curr.next != null)
		{
			// If the current number is equal to the number being removed
			if (curr.next.task.getNumber() == number)
			{
				// Previous saves the current.next reference
				prev = curr.next;

				// The current.next's node will now point to the node after current.next
				// The current.next will then be garbage collected, since nothing is referencing it
				curr.next = curr.next.next;

				break;
			}
			// Continue through the loop until current's number is equal to the number being removed
			curr = curr.next;
		}
		// Returning the task that is being removed
		return curr.task;
        
    }

	// Inserting a node at specific postions
    public boolean insert(int priority, int number) 
    {
    	// Creating the new node with the new priority and number
       Task newTask = new Task(priority, number);
       TaskListNode newNode = new TaskListNode(newTask);
       
       // Creating temporary nodes to iterate through the list
       TaskListNode prev = null; 
       TaskListNode curr = head;
       
       // If the linked list is empty
       // The first node becomes the new node
       if(head != null)
       {
			// Using the compare method in Task.java 
			// The current's priority is compared to newNode's priority
			//  While the priority of the current node is greater than the new node
			// The loop executes
			while(curr.task.compareTo(newNode.task) == 1) 
			{
				
				// Checks to see if the number of the new node and current node equal each other
				// If so, return false
				if(curr.task.getNumber() == newNode.task.getNumber())
				{
					return false;
				}
				// The current node and previous node iterates through the linked list
				prev = curr;
				curr = curr.next;
    			
    			// If the current node is null
    			// The new node is then placed 
				if(curr == null)
				{
					curr = newNode;
					prev.next = curr;
					newNode.next = null;
					return true;
				}

			}

			// Using the compare to method in Task.java
			// If the current node's priority is less then the new node
			if(curr.task.compareTo(newNode.task) == -1) 
			{
				
				// Checks to see if the number of the new node and current node equal each other
				// If so, return false
				if(curr.task.getNumber() == newNode.task.getNumber())
				{
					return false;
				}
				
				// If there is only head  and the new nodes priority is higher,
				// The new node replaces the head
				if(head.task.getPriority() < newNode.task.getPriority()) 
				{
					newNode.next = curr;
					head = newNode;
					return true;
				}
				// Otherwise, the node before current (previous)
				// Previous then points to the new node
				// New node points to current
				newNode.next = prev.next;
    			prev.next = newNode;
    			
    			return true;

			}
			
			// Using the compare to method in Task.java
			// If the current and new node's priority equals each other
			if(curr.task.compareTo(newNode.task) == 0)
			{
				// Checks to see if the number of the new node and current node equal each other
				// If so, return false
				if(curr.task.getNumber() == newNode.task.getNumber())
				{
					return false;
				}
				
				// Checking if the current.next is null
				if(curr.next != null)
				{
					// If there are multiple nodes with the same priority
					// Find the last node with the same priority
					if(curr.next.task.compareTo(newNode.task) == 0)
					{
						prev = curr;
						curr = curr.next;
						
						// If the current node's number is again compared to the new node's number
						if(curr.task.getNumber() == newNode.task.getNumber())
						{
							return false;
						}
					}
					
				}
				// The new node then is placed after the current node
				// In order of whichever node was inserted prior
				newNode.next = curr.next; 
            	curr.next = newNode;
            
				return true;
				
			}			
       }
       // The new node has become the head node, returning true since new node has been inserted
       else head = newNode;
       return true;

    }

	// Retrieves the task at a specific position
    public Task retrieve(int pos) 
    {
    	TaskListNode curr = head;
    	// The temporary node iterates through the list
    	if(head != null)
    	{
    		// Until the temporary reaches the position
    		// The current node becomes the next one
			for(int i = 0; i < pos; i++)
			{
				curr = curr.next;
			}
			// Once the position is reached, returns the task at the position
			return curr.task;
		}
		// Otherwise returns null
		return null;

    }
}
