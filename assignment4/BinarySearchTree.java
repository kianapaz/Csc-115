/*
 * Name: Kiana Pazdernik
 * ID: V00896924
 * Date: November 24, 2018
 * Filename: BinarySearchTreeTree.java
 * Details: Csc 115. Assignment 4. Code provided by csc 115
 */
 
 import java.util.Iterator;

/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree. 
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	/*
	 * attachRightSubtree method is not supported
	 * therefor throws an unsupported exception
	 */
	public void attachRightSubtree(BinaryTree<E> right) 
	{
		throw new UnsupportedOperationException();
	}
	
	/*  
	 * Inserts an item into the BST
	 */
	public void insert(E item) 
	{
		// Calls insert method with parameter of the BST root and the item to insert
		root = insert(root, item);
	}
	/*  
	 * Helper Insert method that accepts a root and an item
	 */
	private TreeNode<E> insert(TreeNode<E> current, E item)
	{
		// If the BST is empty
		// The new item becomes the root 
		if(current == null)
		{
			current = new TreeNode<E>(item);
		}
		// If the item is greater than the node's item
		else if(current.item.compareTo(item) < 0)
		{
			// Iterate through the right node until item is inserted
			current.right = insert(current.right, item);
		}
		// If item is less than the node's item
		else if(current.item.compareTo(item) > 0)
		{
			// Iterate through the left nodes until item is inserted
			current.left = insert(current.left, item);
		}
		return current;
	}
	
	/*
	 * Retrieve method searches for an item and returns it
	 */
	public E retrieve(E item) 
	{
		return retrieve(root, item);
	}
	/*
	 * Helper Retrieve method that accepts a node and an item
	 */
	private E retrieve(TreeNode<E> current, E item)
	{
		// If the tree is empty, return null
		if(item == null) return null;
		
		// While the current node does not equal the item wanted
		while(current.item.compareTo(item) != 0)
		{
			// If item is less than the current node
			if(current.item.compareTo(item) > 0)
			{
				// Iterate through the left nodes
				current = current.left;
			}
			// If the item is greater than the current node
			else if(current.item.compareTo(item) < 0)
			{
				// Iterate through the right nodes
				current = current.right;
			}
			// Otherwise return null
			if(current == null) return null;
		}
		return current.item;
		
	}

	/*
	 * Delete removes an item from the BST 
	 * returns the removed item
	 */
	public E delete(E item) 
	{
		// Creates a new tree node that equals the removed node
		TreeNode<E> removed = delete(root, item);
		
		// If the removed node is not null, return the removed item
		if(removed != null)
		{
			return removed.item;
		}
		return null;
	}
	/* Helper method for delete, returns the deleted node
	 * Divided into four cases:
	 * case 1: base case if the root is null
	 * case 2: if the node has two children
	 * case 3: if the node has one child
	 * case 4: if the node is a leafnode 
	 */
	private TreeNode<E> delete(TreeNode<E> node, E item)
	{
		// Base case if the node is null
		if(node == null)
		{
			return null;
		}
		// Otherwise iterate through the tree
		// If the item is greater than the current node's item
		else if(node.item.compareTo(item) < 0)
		{
			// Iterate through the right nodes
			node.right = delete(node.right, item);
		}
		// If the item is less than the current node's item
		else if(node.item.compareTo(item) > 0)
		{
			// Iterate through the left nodes
			node.left = delete(node.left, item);
		}
		// The item equals the current node's item
		else
		{
			// case 2: The node has two children
			if(node.left != null && node.right != null)
			{
				// Find the left most node on the right tree side
				TreeNode<E> currentNode = findLeftMost(node.right);
				// The left most node becomes the current node
				node.item = currentNode.item;

				// The left most node of the right tree is then deleted
				node.right = delete(node.right, node.item);
			}
			// case 3: The node has only one child
			else if(node.left == null || node.right == null)
			{
				// If the left child is null
				// The node is replaced by the right child
				if(node.left == null)
				{
					node = node.right;
				}
				// If the right child is null
				// The node is replaced by the left child
				else if(node.right == null)
				{
					node = node.left;
				}
			}
			// case 4: Node has no children 
			else
			{
				return null;
			}
		}
		return node;
	}
	/*
	 * Another helper method for delete
	 * Finds left most node of the right tree side
	 */
	private TreeNode<E> findLeftMost(TreeNode<E> node)
	{
		// While the node has another node to the left
		// Iterates until the left most node is found
		while(node.left != null)
		{
			node = node.left;
		}
		return node;
	}

	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		// NOTE TO STUDENT: something to get you started.
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		String s1 = new String("optimal");
		String s2 = new String("needs");
		String s3 = new String("programmers");
		String s4 = new String("CSC115");
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		
		
		String test = tree.retrieve("needs");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s2);
			if (test.equals(s2)) {
				System.out.println("Confirmed");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}
		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println();
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();
		
		/* Additional testing
		 * Creates/ adds number to a BST
		 * Then removes nodes and searches for other nodes
		 */
		BinarySearchTree<String> example = new BinarySearchTree<String>();
		String a = "50";
		String b = "30";
		String c = "20";
		String d = "40";
		String e = "70";
		String f = "60";
		String g = "80";
		
		// Inserting the elements into the BST
		example.insert(a);
		example.insert(b);
		example.insert(c);
		example.insert(d);
		example.insert(e);
		example.insert(f);
		example.insert(g);
		
		// Prints the tree inorder
		Iterator<String> thisOne = example.inorderIterator();
		System.out.println("\nExample BST:\nprinting out the contents of the tree inorder:");
		while (thisOne.hasNext()) {
			System.out.print(thisOne.next()+" ");
		} 
		// Prints tree in postorder
		Iterator<String> thisTwo = example.postorderIterator();
		System.out.println("\nprinting out the contents of the tree postorder:");
		while (thisTwo.hasNext()) {
			System.out.print(thisTwo.next()+" ");
		} 
		// Prints tree in preorder
		Iterator<String> thisThree = example.preorderIterator();
		System.out.println("\nprinting out the contents of the tree preorder:");
		while (thisThree.hasNext()) {
			System.out.print(thisThree.next()+" ");
		} 
		System.out.println();

		// Deletes nodes
		example.delete("50");
		example.delete("30");
		System.out.println();
		
		// Searches for nodes
		if(example.retrieve("70").equals(e))
		{
			System.out.println("Found 70");
		}
		else{
			System.out.println("Can not find 70");
		}
		if(example.retrieve("20").equals(c))
		{
			System.out.println("Found 20");
		}
		else{
			System.out.println("Can not find 20");
		}

		// Prints the example BST
		System.out.println();
		DrawableBTree<String> yes = new DrawableBTree<String>(example);
		yes.showFrame();
		
	}
}

	

	
