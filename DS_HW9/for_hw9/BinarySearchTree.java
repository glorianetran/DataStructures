package for_hw9;
//Gloriane Tran
//Data Structures 124
//Assignment #9
//October 29th, 2015

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	// public void insert( AnyType x )
	// {
	// root = insert( x, root );
	// }

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(AnyType x) {
		root = remove(x, root);
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin() {
		if (isEmpty())
			throw new UnderflowException();
		return findMin(root).element;
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax() {
		if (isEmpty())
			throw new UnderflowException();
		return findMax(root).element;
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return true if not found.
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	// public recursion method
	public void printTreePreOrder() {
		if (isEmpty()) {
			System.out.println("Empty Tree ):");
		} else {
			printTreePreOrder(root);
		} // if
	}// printTreePreOrder

	public void printTreePostOrder() {
		if (isEmpty()) {
			System.out.println("Empty Tree ): ");
		} else {
			printTreePostOrder(root);
		} // if
	}// printTreePostOrder

	public AnyType find() {
		if (isEmpty()) {
			throw new UnderflowException();
		} else {
			return find(root.element);
		} // if
	}// find

	public void insert(AnyType x) {
		BinaryNode<AnyType> t = root;
		boolean done = false;
		if (t == null) {
			root = new BinaryNode<AnyType>(x);
			return;
		} // if
		while (!done) {
			if (t.element.compareTo(x) == 0) {
				return;
			} // if
			if (t.element.compareTo(x) > 0) {
				if (t.left == null) {
					t.left = new BinaryNode<AnyType>(x);
					done = true;
				} else {
					t = t.left;
				} // else
			} // if
			if (t.element.compareTo(x) < 0) {
				if (t.right == null) {
					t.right = new BinaryNode<AnyType>(x);
					done = true;
				} else {
					t = t.right;
				} // else
			} // if
		} // while
	}// insert

	// TODO: Runtime: O(n) with n being the number of nodes in the tree
	public int numberOfNodes() {
		BinaryNode<AnyType> t = root;
		if (root == null) {
			return 0;
		} // if
		return numberOfNodes(root.left) + numberOfNodes(root.right) + 1;
	}// numberOfNodes

	// TODO: Runtime: O(n) with n being the number of nodes in the tree
	public int numberOfLeaves() {
		if (root == null) {
			return 0;
		} // if
		return numberOfLeaves(root.left) + numberOfLeaves(root.right);
	}// numberOfLeaves

	// TODO: Runtime: O(n) with n being the number of nodes in the tree
	public int numberOfFullNodes() {
		if (root == null) {
			return 0;
		} // if
		return numberOfFullNodes(root);
	}// numberOfFullNodes

	// TODO: Runtime:  O(k + log N) 
	public void printRange(AnyType lower, AnyType upper) {
		if (lower.compareTo(upper) > 0) {
			AnyType temp = lower;
			lower = upper;
			upper = temp;
		} // if (switches values to check and use them)
		if (root == null) {
			return;
		} else {
			printRange(root, lower, upper);
		} // else
	}// printElements

	private void printRange(BinaryNode<AnyType> t, AnyType lower, AnyType upper) {
		if (t != null) {
			if ((root.element).compareTo(lower) > 0) {
				printRange(t.left, lower, upper);
			}  if ((t.element).compareTo(lower) >= 0 && (t.element).compareTo(upper) <= 0) {
				System.out.print(t.element + " ");
			}  if ((t.element).compareTo(upper) < 0) {
				printRange(t.right, lower, upper);
			} // else if
		} // else
	}// printElements

	// TODO: theta(n) because I check every node, but with every node, I perform
	// constant work
	// when I enqueue and dequeue.
	public void printLevelOrder() {
		if (isEmpty()) {
			System.out.println("Empty Tree");
		} else {
			QueueLi<BinaryNode<AnyType>> q = new QueueLi<>();
			BinaryNode<AnyType> currentNode;
			q.enqueue(root);
			while (!q.isEmpty()) {
				currentNode = q.dequeue();
				if (currentNode.left != null) {
					q.enqueue(currentNode.left);
				} // if
				if (currentNode.right != null) {
					q.enqueue(currentNode.right);
				} // if
				System.out.print(currentNode.element + " ");
			} // while
		} // else
	}// printLevelOrder

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	// private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
	// {
	// if( t == null )
	// return new BinaryNode<>( x, null, null );
	//
	// int compareResult = x.compareTo( t.element );
	//
	// if( compareResult < 0 )
	// t.left = insert( x, t.left );
	// else if( compareResult > 0 )
	// t.right = insert( x, t.right );
	// else
	// ; // Duplicate; do nothing
	// return t;
	// }

	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return t; // Item not found; do nothing

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true; // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private void printTree(BinaryNode<AnyType> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	private void printTreePreOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			System.out.println(t.element);
			printTreePreOrder(t.left);
			printTreePreOrder(t.right);
		} // if
	}// printTreePreOrder

	private void printTreePostOrder(BinaryNode<AnyType> t) {
		if (t != null) {
			printTreePostOrder(t.left);
			printTreePostOrder(t.right);
			System.out.println(t.element);
		} // if
	}// printTreePostOrder

	private int numberOfNodes(BinaryNode<AnyType> t) {
		if (t == null) {
			return 0;
		} // if
		return numberOfNodes(t.left) + numberOfNodes(t.right) + 1;
	}// countNodes

	private int numberOfLeaves(BinaryNode<AnyType> t) {
		if (t == null) {
			return 0;
		} // if
		if (t.left == null && t.right == null) {
			return 1;
		} // if
		return numberOfLeaves(t.left) + numberOfLeaves(t.right);
	}// numberOfNodes

	private int numberOfFullNodes(BinaryNode<AnyType> t) {
		if (t == null) {
			return 0;
		} // if
		if (t.left == null && t.right == null) {
			return 0;
		} // if
		if (t.left != null && t.right != null) {
			return 1 + numberOfFullNodes(t.left) + numberOfFullNodes(t.right);
		} // if
		return numberOfFullNodes(t.left) + numberOfFullNodes(t.right);
	}// numberOfFullNodes

	private AnyType find(AnyType x) {
		BinaryNode<AnyType> t = root;
		while (t != null) {
			if (t.element.compareTo(x) < 0) {
				t = t.right;
			} else if (t.element.compareTo(x) > 0) {
				t = t.left;
			} else {
				return t.element;
			} // else
		} // while
		return null;
	}// find

	/**
	 * Internal method to compute height of a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private int height(BinaryNode<AnyType> t) {
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	// Basic node stored in unbalanced binary search trees
	private static class BinaryNode<AnyType> {
		// Constructors
		BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}

		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}

		AnyType element; // The data in the node
		BinaryNode<AnyType> left; // Left child
		BinaryNode<AnyType> right; // Right child
	}

	/** The tree root. */
	private BinaryNode<AnyType> root;

	// Test program
	public static void main(String[] args) {
		BinarySearchTree<Integer> t2 = new BinarySearchTree<>(); // balanced
																	// tree of
																	// integers
		BinarySearchTree<String> t3 = new BinarySearchTree<>(); // balanced tree
																// of strings
		BinarySearchTree<Integer> t4 = new BinarySearchTree<>();// empty tree
		BinarySearchTree<Integer> t5 = new BinarySearchTree<>(); // unbalanced
																	// tree of
																	// integers
		t2.insert(44);
		t2.insert(5);
		t2.insert(16);
		t2.insert(14);
		t2.insert(4);
		t2.insert(3);
		t2.insert(23);
		t2.insert(27);

		t3.insert("Cat");
		t3.insert("Dog");
		t3.insert("Monkey");
		t3.insert("Awkward");
		t3.insert("Aardvark");
		t3.insert("Bingles");
		t3.insert("Cup");

		// Inserting integers in an unbalanced tree
		System.out.println("\nThe numbers in the unbalanced tree are: ");
		t5.insert(55);
		t5.insert(43);
		t5.insert(40);
		t5.insert(42);
		t5.insert(35);
		t5.insert(37);
		t5.insert(32);
		t5.insert(28);
		t5.insert(33);
		t5.insert(34);
		t5.insert(30);
		t5.insert(65);
		t5.printTree();

		System.out.println("\nThe numbers within the range of 40 and 65 are: ");
		t5.printRange(40, 65);

		System.out.println("\nThe numbers within the range of 28 and 43 are: ");
		t5.printRange(28, 43);

		System.out.println("\nThe numbers within the range of 37 and 70 are: ");
		t5.printRange(70, 37);

		System.out.println("\nThe numbers in balanced tree are: ");
		t2.printTree();
		System.out.println("\nThe numbers in the range 1 - 50 are: ");
		t2.printRange(1, 50);

		System.out.println("\nThe numbers in the range 5 - 27 are: ");
		t2.printRange(5, 27);

		System.out.println("\nThe numbers in the range 27 - 23 are: ");
		t2.printRange(27, 23);

		System.out.println("\nThe strings of the balanced tree of strings is: ");
		t3.printTree();
		System.out.println("\nThe strings in the range Apple and Monkey are: ");
		t3.printRange("Apple", "Monkey");

		System.out.println("\nThe strings in the range Aardvark and Dog are: ");
		t3.printRange("Aardvark", "Dog");

		System.out.println("\nThe strings in the range Cat and Monkey are: ");
		t3.printRange("Cat", "Monkey");

		System.out.println("\nThe strings in the range Monkey and Cat are: ");
		t3.printRange("Monkey", "Cat");

		System.out.println("\nThe integers in the range 0 and 4 for an empty list ");
		t4.printRange(2, 4);

		// Print level order testing
		System.out.println("\nThe integers printed in level order: ");
		t2.printLevelOrder();
		System.out.println("\nThe unbalanced integer list printed in level order: ");
		t5.printLevelOrder();
		System.out.println("\nThe strings printed in level order: ");
		t3.printLevelOrder();
		System.out.println("\nThe empty list printed in level order: ");
		t4.printLevelOrder();

		t4.insert(10);
		t4.insert(11);
		t4.insert(12);
		t4.insert(13);
		t4.insert(4);
		t4.insert(6);
		t4.insert(2);
		t4.insert(3);
		t4.insert(1);
		t4.insert(5);
		t4.insert(8);
		t4.insert(7);
		t4.insert(9);
		System.out.println("\nThe figure 4.72 list printed in level order: ");
		t4.printLevelOrder();

	}// main
}// BinarySearchTree
