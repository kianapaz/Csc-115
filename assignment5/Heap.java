/*
 * Name: Kiana Pazdernik
 * ID: V00896924
 * Date: December 1, 2018
 * File: Heap.java
 * Details: Assignment 5: Emergency Room, Csc 115
 */

import java.util.NoSuchElementException;
import java.util.Vector;

/*
 * Heap.java creates a Heap that is a binary tree data structure 
 * The Heap has both structural and ordering properties
 * Heap.java is used to implement the basic methods for PriorityQueue.java
 */

public class Heap<E extends Comparable<E>> 
{

	private Vector<E> heapArray;;

	/* 
	 * Heap constructor initializing a new Heap array
	 */
	public Heap() 
	{
		heapArray = new Vector<E>();
	}

	/* 
	 * Checks if heap is empty
	 * returns true or false
	 */
	public boolean isEmpty()
	{
		return heapArray.isEmpty();
	}

	/*
	 * size() return the size of the heap array
	 */
	public int size()
	{
		return heapArray.size();
	}

	/*
	 * Swaps elements in the heap
	 */
	private void swapElement(int firstIndex, int secondIndex)
	{
		// Creates a current heap item to store first item
		E current = heapArray.get(firstIndex);
	
		// The first index is then swapped with the second item
		heapArray.setElementAt(heapArray.get(secondIndex), firstIndex);
		heapArray.setElementAt(current, secondIndex);
	
	}
	/*
	 * Inserts an item into the heap so that the binary tree is always complete
	 */
	public void insert(E item)
	{
		// Adds the item into the last position 
		heapArray.addElement(item);
	
		// At the index, calls heapifyUp()
		int index = size() -1;
		heapifyUp(index);
	}
	/*
	 * Helper method of Insert
	 * Ensures the heap is always a max heap
	 */
	private void heapifyUp(int item)
	{
		// If the item is greater than the parent node
		if( item > 0 && heapArray.get(getParenetindex(item)).compareTo(heapArray.get(item)) < 0)
		{
			// The item is then swapped with the parent node
			swapElement(item, getParenetindex(item));
			// Calls heapifyUp() to ensure the item is in the correct place
			heapifyUp(getParenetindex(item));

		}

	}
	/*
	 * Removes the root of the max Heap
	 * Sets the last inserted item as root, returns the new root
	 */
	public E removeRootItem()
	{	
		// If the heap is empty throw an exception
		if(isEmpty())
		{
			throw new NoSuchElementException("The heap is empty.");
		}
		// Create a new root item as the root
		E rootItem = heapArray.firstElement();
		// Sets the last inserted item as the root
		heapArray.setElementAt(heapArray.lastElement(), 0);
		// Removes the root
		heapArray.remove(size() - 1);
		// Calls heapifyDown() to rearrange the max heap
		heapifyDown(0);
		
		return rootItem;
		
	}
	/*
	 * Helper method for removing the root
	 */
	private void heapifyDown(int index)
	{
		// Getting left and right child of nodes at index
		int left = (2 * index +1);
		int right = (2 * index +2);
		// Sets the root as the largest item
		int largest = index;
		
		// Compares the left item to the root
		// If the left item is greater, the largest becomes the left item
		if(left < size() && heapArray.get(largest).compareTo(heapArray.get(left)) < 0)
		{
			largest = left;
		}
		// Compares the right item with the largest item
		// If the right item is greater than the largest
		// the right becomes the largest item
		if(right < size() && heapArray.get(largest).compareTo(heapArray.get(right)) < 0)
		{
			largest = right;
		}
		// If the largest isn't the root
		if(largest != index)
		{
			// Swaps the root with the largest item
			swapElement(index, largest);
			// Calls heapifyDown() to ensure the item is the largest in the max heap
			heapifyDown(largest);
		}
	}

	/*
	 * getRootItem() returns the root item 
	 */
	public E getRootItem()
	{
		// If the heap is empty throw an exception
		if(isEmpty())
		{
			throw new NoSuchElementException("The heap is empty.");
		}
		// Return the root
		return heapArray.get(0);
	}


	private int indexOf(E p){
		for (int i = 1; i < heapArray.capacity(); i++) {
			if (heapArray.elementAt(i).equals(p))   {
				return i;
			}
		}
		return -1;
	}
	// Method name is getParenetindex(),
	// Assuming proper spelling meant getParentIndex()
	private int getParenetindex(int child){
		return child/2;
	}


	public void print_vector() {
		System.out.println(" *************** Array is ***************");
		for (int i = 0; i < heapArray.size(); i++){
			System.out.println(heapArray.elementAt(i));
		}
	}
	/*
	 * Testing the Heap methods in the main method
	 */
	public static void main(String args []){
		
		// Create a heap of ER_Patient
		Heap <ER_Patient> hp = new Heap <ER_Patient>();
		// Inserting patients
		hp.insert(new ER_Patient ("Chronic"));	
		hp.insert(new ER_Patient ("Life-threatening"));
		hp.insert(new ER_Patient ("Major fracture"));
		hp.insert(new ER_Patient ("Chronic"));
		hp.insert(new ER_Patient ("Walk-in"));
		// Printing the hp array
		hp.print_vector();
		// Removing the highest priority patients
		hp.removeRootItem();
		hp.removeRootItem();
		hp.removeRootItem();
		
		
		// Additional testing 
		
		// Create a new heap of int
		Heap<Integer> heap = new Heap<>();
		// Inserting integers
		System.out.println("Inserted integers");
		heap.insert(78);
		heap.insert(32);
		heap.insert(4);
		heap.insert(90);
		heap.insert(278);
		heap.insert(6392);
		heap.insert(67);
		heap.insert(5);
		heap.insert(7);
		heap.insert(9);
		// Print the heap array
		heap.print_vector();
		
		System.out.println();
		// Removing the root items
		System.out.println("Removing the root of heap:");
		System.out.println(heap.removeRootItem());
		System.out.println(heap.removeRootItem());
		System.out.println(heap.removeRootItem());
		System.out.println(heap.removeRootItem());
		System.out.println(heap.removeRootItem());
		// Printing the heap array
		heap.print_vector();
		
	}
}
