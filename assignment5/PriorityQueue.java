/*
 * Name: Kiana Pazdernik
 * ID: V00896924
 * Date: December 1, 2018
 * File: PriorityQueue.java
 * Details: Assignment 5, Csc 115
 */
 import java.util.NoSuchElementException;

/*
 * PriorityQueue.java is a queue of the same objects
 * The root item is always the highest priority 
 * PriorityQueue.java is a program that charts patients from the Emergency Room
 * in order of priority
 */

public class PriorityQueue<E extends Comparable<E>> 
{
	
	private Heap<E> heap;

	/*
	 * Constructor: creates an empty heap
	 */
	public PriorityQueue() 
	{
		heap = new Heap<E>();
	}
	
	/*
	 * Removes the highest priority item from the queue by calling the removeRootItem() method
	 * Returns the removed item
	 */
    public E dequeue() 
    {
		return heap.removeRootItem();
    }
	
	/*
	 * Inserts an item into the queue by calling the insert() method
	 */
 	public void enqueue(E item) 
 	{
        heap.insert(item);
    }
	
	/*
	 * Checks if the queue is empty
	 */
    public boolean isEmpty() 
    {
		return heap.isEmpty();
		
    }
	
	/*
	 * Returns the highest priority item in the queue by calling the getRootItem() method
	 */
    public E peek() 
    {
		return heap.getRootItem();
    }
    
	/* 
	 * Main method testing of the ER_Patient
	 */
    public static void main(String args []) 
    {
		
		PriorityQueue <ER_Patient> p1 = new PriorityQueue <ER_Patient> ();

		System.out.println(p1.isEmpty());
		p1.enqueue(new ER_Patient("Walk-in"));
		System.out.println(p1.peek());
		System.out.println(p1.isEmpty());

		
		// Additional Testing 
		
		// Creates a new tester queue
		PriorityQueue<ER_Patient> tester = new PriorityQueue<>();
		// Creates an array of size 6
 		ER_Patient[] patientCount = new ER_Patient[6];
 		// Creates another array of strings
		String[] patientSymptoms = {"Walk-in", "Life-threatening","Chronic","Major fracture", 
							"Chronic", "Major fracture"};
		
		// Inserts the patientSymptoms[] string into the ER_Patient[] array				
		for(int i = 0; i < 6; i++)
		{
			patientCount[i] = new ER_Patient(patientSymptoms[i]);
			
			// Spreading out the admission of each patient by one second
			try 
			{
				Thread.sleep(1000);
				System.out.print("Patients being admitted " + (i + 1) + "/6");
				// The "\b" is a backspace which allows the "Patients being admitted " to be printed only once
				System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");

			} 
			catch (InterruptedException e) 
			{
				System.out.println("sleep interrupted");
				return;
			}
		}
		// Enqueue the patients
		System.out.println("\nEnqueuing Patients: ");
		for(int i = 0; i < 6; i++)
		{
			System.out.println(patientCount[i]);
			tester.enqueue(patientCount[i]);
		}
		// Peeks the root item
		System.out.println("\nPeeking the Queue: " + tester.peek());
		// Check if queue is empty
		System.out.println("Checking if Queue is empty: " + tester.isEmpty());
		// Dequeue the patients
		System.out.println("\nDequeuing Patients: ");
		for(int i = 0; i < 6; i++)
		{
			System.out.println(tester.dequeue());
		}
		// Check if queue is empty
		System.out.print("\nChecking if Queue is empty: " + tester.isEmpty());
		System.out.println();
		
		
    }
}
	
