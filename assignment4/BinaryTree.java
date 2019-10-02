/*
 * Name: Kiana Pazdernik
 * ID: V00896924
 * Date: November 24, 2018
 * Filename: BinaryTree.java
 * Details: Csc 115. Assignment 4. Code provided by csc 115
 */

import java.util.Iterator;

/*
 * NOTE TO STUDENT:
 * Comment and implement all incomplete methods.
 * Any methods that have comments are already complete and
 * you must not change them.
 * You may add methods that you find helpful, remembering
 * that no public method allows access to a TreeNode directly.
 * Remove this comment and provide your own header.
 */

/**
 * BinaryTree is a basic generic BinaryTree data structure.
 * It is referenced based, using TreeNodes to connect the tree.
 * It contains any element determined by the placeholder E.
 * The basic ADT is adapted from <i>Java, Walls &amp; Mirrors,</i> by Prichard and Carrano.
 */
public class BinaryTree<E> {

	/* The root is inherited by any subclass
	 * so it must be protected instead of private.
	 */
	protected TreeNode<E> root;

	/**
	 * Create an empty BinaryTree.
	 */
	public BinaryTree() 
	{
		root = null;
	}

	/**
	 * Create a BinaryTree with a single item.
	 * @param item The only item in the tree.
	 */
	public BinaryTree(E item) 
	{
		root = new TreeNode<E>(item);
	}

	/**
	 * Used only by subclasses and classes in the same package (directory).
	 * @return The root node of the tree.
	 */
	protected TreeNode<E> getRoot() 
	{
		return root;
	}

	/**
	 * Creates a binary tree from a single item for the root and two subtrees.
	 * Once the two subtrees are attached, their references are nullified to prevent
	 * multiple entries into <i>this</i> tree.
	 * @param item The item to be inserted into the root of this tree.
	 * @param leftTree A binary tree, which may be empty.
	 * @param rightTree A binary tree, which may be empty.
	 */
	public BinaryTree(E item, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new TreeNode<E>(item);
		attachLeftSubtree(leftTree);
		attachRightSubtree(rightTree);
	}

	/**
	 * Attaches a subtree to the left of the root node, replacing any subtree that was 
	 * previously there.
	 * @param left The new left subtree of the root.
	 *	This subtree may be empty.
	 *	Once attached, this parameter reference is nullified to prevent multiple
	 *	access to <i>this</i> tree.
	 * @throws TreeException if <i>this</i> tree is empty.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		if (root == null) {
			throw new TreeException("Cannot attach to an empty tree.");
		}
		if (left == null) {
			return;
		}
		root.left = left.root;
		left.root.parent = root;
		left = null;
	}

	/**
	 * @return a preorder iterator of the binary tree.
	 */
	public Iterator<E> preorderIterator() {
		return new BinaryTreeIterator<E>("preorder",root);
	}

	/**
	 * @return an inorder iterator of the binary tree.
	 */
	public Iterator<E> inorderIterator() {
		return new BinaryTreeIterator<E>("inorder", root);
	}

	/**
	 * @return a postorder iterator of the binary tree.
	 */
	public Iterator<E> postorderIterator() {
		return new BinaryTreeIterator<E>("postorder",root);

	}

/* 
 * isEmpty method checks if the root is null and returns a boolean
 */
	public boolean isEmpty() 
	{
		return root == null;
	}

	/* 
	 * Attaches a subtree to the right side of the root node
	 * Replaces any subtree that was previously there
	 * Once the two subtrees are attached, the parameter is nullified, preventing multiple acces to the tree
     */
	public void attachRightSubtree(BinaryTree<E> right) 
	{
		// If the root is null, throws an exception
		if (root == null) 
		{
			throw new TreeException("Cannot attach to an empty tree.");
		}
		// If the right is null, returns
		if (right == null) 
		{
			return;
		}
		// Otherwise, the right of the root becomes the right node
		root.right = right.root;
		right.root.parent = root;
		right = null;
	}
		
	/*
	 * Height method returns an int
	 */
	public int height() 
	{
		// If the BST is empty, return  the height of 0
		if(isEmpty())
		{
			return 0;
		}
		// Otherwise, calls height
		else
		{
			TreeNode<E> current = root;
			return height(root);
		}
	}
	/* 
	 * Helper method height, takes a parameter of a node
	 * The height is defined as 1 + the maximum height
	 */
	private int height(TreeNode<E> node) 
	{
		// If the root is null, returns a negative value
		if(root == null)
		{
			return -1;
		}
		// Creates two integers of the height of the left and right side
		int left = height(node.left);
		int right = height(node.right);
		
		// Compares the left and right side
		// If the left is greater, returns left + 1
		if(left > right)
		{
			return left + 1;
		}
		// If right is greater, returns right + 1
		else
		{
			return right + 1;
		}
	}

}

	
