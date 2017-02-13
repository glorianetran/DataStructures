package for_HW8;
//Gloriane Tran 
//Data Structures 124 
//October 22nd, 2015
//Assignment 8 

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
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree( )
	{
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * @param x the item to insert.
	 */
	//		public void insert( AnyType x )
	//		{
	//			root = insert( x, root );
	//		}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * @param x the item to remove.
	 */
	public void remove( AnyType x )
	{
		root = remove( x, root );
	}

	/**
	 * Find the smallest item in the tree.
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin( )
	{
		if( isEmpty( ) )
			throw new UnderflowException( );
		return findMin( root ).element;
	}

	/**
	 * Find the largest item in the tree.
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax( )
	{
		if( isEmpty( ) )
			throw new UnderflowException( );
		return findMax( root ).element;
	}

	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return true if not found.
	 */
	public boolean contains( AnyType x )
	{
		return contains( x, root );
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty( )
	{
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree( )
	{
		if( isEmpty( ) )
			System.out.println( "Empty tree" );
		else
			printTree( root );
	}

	//public recursion method
	public void printTreePreOrder(){
		if(isEmpty()){
			System.out.println("Empty Tree ):");
		}else{
			printTreePreOrder(root);
		}//if
	}//printTreePreOrder

	public void printTreePostOrder(){
		if(isEmpty()){
			System.out.println("Empty Tree ): ");
		}else{
			printTreePostOrder(root);
		}//if
	}//printTreePostOrder

	public AnyType find(){
		if(isEmpty()){
			throw new UnderflowException();
		}else{
			return find(root.element);
		}//if
	}//find

	public void insert(AnyType x){
		BinaryNode<AnyType> t = root; 
		boolean done = false;
		if(t == null){
			root = new BinaryNode<AnyType>(x);
			return;
		}//if
		while(!done){
			if(t.element.compareTo(x) == 0){
				return;
			}//if
			if(t.element.compareTo(x) > 0){
				if(t.left == null){
					t.left = new BinaryNode<AnyType>(x);
					done = true;
				}else{
					t = t.left;
				}//else
			}//if 
			if(t.element.compareTo(x) < 0){
				if(t.right == null){
					t.right = new BinaryNode<AnyType>(x);
					done = true;
				}else{
					t = t.right;
				}//else
			}//if 
		}//while
	}//insert

	//TODO: Runtime: O(n) with n being the number of nodes in the tree
	public int numberOfNodes(){
		BinaryNode<AnyType> t = root; 
		if(root == null){
			return 0;
		}//if
		return numberOfNodes(root.left) + numberOfNodes(root.right) + 1;
	}//numberOfNodes

	//TODO: Runtime: O(n) with n being the number of nodes in the tree
	public int numberOfLeaves(){
		if(root == null){
			return 0;
		}//if
		return numberOfLeaves(root.left) + numberOfLeaves(root.right);
	}//numberOfLeaves

	//TODO: Runtime: O(n) with n being the number of nodes in the tree
	public int numberOfFullNodes(){
		if(root == null){
			return 0;
		}//if
		return numberOfFullNodes(root);
	}//numberOfFullNodes
	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
//	private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
//	{
//		if( t == null )
//			return new BinaryNode<>( x, null, null );
//
//		int compareResult = x.compareTo( t.element );
//
//		if( compareResult < 0 )
//			t.left = insert( x, t.left );
//		else if( compareResult > 0 )
//			t.right = insert( x, t.right );
//		else
//			;  // Duplicate; do nothing
//		return t;
//	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return t;   // Item not found; do nothing

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = remove( x, t.left );
		else if( compareResult > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = remove( t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	{
		if( t == null )
			return null;
		else if( t.left == null )
			return t;
		return findMin( t.left );
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
	{
		if( t != null )
			while( t.right != null )
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return false;

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			return contains( x, t.left );
		else if( compareResult > 0 )
			return contains( x, t.right );
		else
			return true;    // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * @param t the node that roots the subtree.
	 */
	private void printTree( BinaryNode<AnyType> t )
	{
		if( t != null )
		{
			printTree( t.left );
			System.out.println(t.element);
			printTree( t.right );
		}
	}

	private void printTreePreOrder(BinaryNode<AnyType> t){
		if(t!= null){
			System.out.println(t.element);
			printTreePreOrder(t.left);
			printTreePreOrder(t.right);
		}//if
	}//printTreePreOrder

	private void printTreePostOrder(BinaryNode<AnyType> t){
		if(t!= null){
			printTreePostOrder(t.left);
			printTreePostOrder(t.right);
			System.out.println(t.element);
		}//if
	}//printTreePostOrder

	private int numberOfNodes(BinaryNode<AnyType> t) {
		if(t == null){
			return 0;
		}//if
		return numberOfNodes(t.left) + numberOfNodes(t.right) + 1;
	}//countNodes

	private int numberOfLeaves(BinaryNode<AnyType> t){
		if(t == null){
			return 0;
		}//if
		if(t.left==null && t.right==null){
			return 1;
		}//if
		return numberOfLeaves(t.left) + numberOfLeaves(t.right);
	}//numberOfNodes

	private int numberOfFullNodes(BinaryNode<AnyType> t){
		if(t == null){
			return 0;
		}//if
		if(t.left == null && t.right == null){
			return 0;
		}//if
		if(t.left != null && t.right!= null){
			return 1 + numberOfFullNodes(t.left) + numberOfFullNodes(t.right);
		}//if
		 return numberOfFullNodes(t.left) + numberOfFullNodes(t.right);
	}//numberOfFullNodes

	private AnyType find( AnyType x){
		BinaryNode<AnyType> t = root;
		while (t != null) {
			if( t.element.compareTo(x) < 0 ) {
				t = t.right;
			} else if ( t.element.compareTo(x) > 0 ){
				t = t.left;
			} else {
				return t.element;
			}//else
		}//while
		return null;
	}//find

	/**
	 * Internal method to compute height of a subtree.
	 * @param t the node that roots the subtree.
	 */
	private int height( BinaryNode<AnyType> t )
	{
		if( t == null )
			return -1;
		else
			return 1 + Math.max( height( t.left ), height( t.right ) );    
	}

	// Basic node stored in unbalanced binary search trees
	private static class BinaryNode<AnyType>
	{
		// Constructors
		BinaryNode( AnyType theElement )
		{
			this( theElement, null, null );
		}

		BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
		{
			element  = theElement;
			left     = lt;
			right    = rt;
		}

		AnyType element;            // The data in the node
		BinaryNode<AnyType> left;   // Left child
		BinaryNode<AnyType> right;  // Right child
	}


	/** The tree root. */
	private BinaryNode<AnyType> root;


	// Test program
	public static void main( String [ ] args )
	{
		BinarySearchTree<Integer> t = new BinarySearchTree<>( ); //intial tree
		BinarySearchTree<Integer> t2 = new BinarySearchTree<>(); //balanced tree of integers
		BinarySearchTree<String> t3 = new BinarySearchTree<>(); //balanced tree of strings
		BinarySearchTree<Integer> t4 = new BinarySearchTree<>();//empty tree
		BinarySearchTree<Integer> t5 = new BinarySearchTree<>(); //unbalanced tree of integers
		final int NUMS = 4000;
		final int GAP  =   37;

		System.out.println( "Checking... (no more output means success)" );


		for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) 
			t.insert( i );


		for( int i = 1; i < NUMS; i+= 2 )
			t.remove( i );


		if( NUMS < 40 )
			t.printTree( );
		if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
			System.out.println( "FindMin or FindMax error!" );

		for( int i = 2; i < NUMS; i+=2 )
			if( !t.contains( i ) )
				System.out.println( "Find error1!" );

		for( int i = 1; i < NUMS; i+=2 )
		{
			if( t.contains( i ) )
				System.out.println( "Find error2!" );
		}//for

		//Testing Preorder Traversal 
		t2.insert(44);
		t2.insert(5);
		t2.insert(16);
		t2.insert(14);
		t2.insert(4);
		t2.insert(3);
		t2.insert(23);
		t2.insert(27);
		System.out.println("\nTesting Preorder Traversal");
		t2.printTreePreOrder();

		System.out.println("\nTesting Postorder Traversal");
		t2.printTreePostOrder();

		//Testing Postorder Traversal 
		t3.insert("Cat");
		t3.insert("Dog");
		t3.insert("Monkey");
		t3.insert("Awkward");
		t3.insert("Aardvark");
		t3.insert("Bingles");
		t3.insert("Cup");
		System.out.println("\nTesting Preorder Traversal");
		t3.printTreePreOrder();

		System.out.println("\nTesting Postorder Traversal");
		t3.printTreePostOrder();


		//Testing find method with list of Integers
		try{
			System.out.println("\nTesting find method with list of integers");
			System.out.println("Find 27: " + t2.find(27));
			System.out.println("Find 4: " + t2.find(4));
			System.out.println("Find 16: " + t2.find(16));
			System.out.println("Find 44: " + t2.find(44));
			System.out.println("Find 23: " + t2.find(23));
			System.out.println("Find 14: " + t2.find(14));
			System.out.println("Find 14: " + t2.find(14));
		}catch (UnderflowException e){
			e.toString();
		}//catch

		//Testing find method with list of strings
		try{
			System.out.println("\nTesting find method with list of strings");
			System.out.println("Find Aardvark: " + t3.find("Aardvark"));
			System.out.println("Find Monkey: " + t3.find("Monkey"));
			System.out.println("Find Cat: " + t3.find("Cat"));
			System.out.println("Find Cup: " + t3.find("Cup"));
			System.out.println("Find Bingles: " + t3.find("Bingles"));
			System.out.println("Find Cotton: " + t3.find("Cotton"));
		}catch (UnderflowException e){
			e.toString();
		}//catch

		//Testing insert method with list of integers 
		System.out.println("\nInserting 44 into list of integers: ");
		t2.insert(44);
		t2.printTree();

		System.out.println("\nInserting 22 into list of integers: ");
		t2.insert(22);
		t2.printTree();

		System.out.println("\nInserting 2 into list of integers: ");
		t2.insert(2);
		t2.printTree();

		System.out.println("\nInserting 14 into list of integers: ");
		t2.insert(14);
		t2.printTree();

		//Testing insert method with list of strings
		System.out.println("\nInserting Bonkers into list of strings: ");
		t3.insert("Bonkers");
		t3.printTree();

		System.out.println("\nInserting Aaaahhhh into list of strings: ");
		t3.insert("Aaaahhhh");
		t3.printTree();

		System.out.println("\nInserting Zebra into list of strings: ");
		t3.insert("Zebra");
		t3.printTree();

		System.out.println("\nInserting Cat into list of strings: ");
		t3.insert("Cat");
		t3.printTree();



		//Testing number of full nodes in the list of integers
		System.out.println("\nNumber of nodes in the list of integers " + t2.numberOfNodes());
		//Testing number of nodes in the list of strings
		System.out.println("\nNumber of nodes in the list of strings " + t3.numberOfNodes());
		//Testing number of nodes in an empty list
		System.out.println("\nNumber of nodes in an empty list " + t4.numberOfNodes());

		//Testing number of nodes in the list of strings
		System.out.println("\nNumber of leaves in the list of strings " + t3.numberOfLeaves());
		//Testing number of full nodes in the list of integers
		System.out.println("\nNumber of leaves in the list of integers " + t2.numberOfLeaves());
		//Testing number of leaves in an empty list
		System.out.println("\nNumber of leaves in an empty list " + t4.numberOfLeaves());

		//Testing number of full nodes in the list of integers
		System.out.println("\nNumber of full nodes in the list of integers " + t2.numberOfFullNodes());
		//Testing number of full nodes in the list of strings
		System.out.println("\nNumber of full nodes in the list of strings " + t3.numberOfFullNodes());
		//Testing number of full nodes in an empty list
		System.out.println("\nNumber of full nodes in an empty list " + t4.numberOfFullNodes()); 

		//Inserting integers in an unbalanced tree
		System.out.println("\nTesting unbalanced tree: ");
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
		
		//Testing number of nodes in an unbalanced tree
		System.out.println("\nNumber of nodes in the unbalanced list of strings " + t5.numberOfNodes());

		System.out.println("\nTesting find method with unbalanced tree");
		try{
			System.out.println("Find 32: " + t5.find(32));
			System.out.println("Find 40: " + t5.find(40));
			System.out.println("Find 37: " + t5.find(37));
			System.out.println("Find 34: " + t5.find(34));
			System.out.println("Find 75: " + t5.find(75));
		}catch (UnderflowException e){
			e.toString();
		}//catch

		System.out.println("\nNumber of full nodes in an unbalanced list " + t5.numberOfFullNodes()); 
		System.out.println("\nNumber of full nodes in an empty list " + t4.numberOfFullNodes());

	}//main
}//BinarySearchTree
