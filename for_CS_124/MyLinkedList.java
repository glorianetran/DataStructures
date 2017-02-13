package for_CS_124;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType extends Comparable<? super AnyType>> implements Iterable<AnyType>
{
	/**
	 * Construct an empty LinkedList.
	 */
	public MyLinkedList( )
	{
		doClear( );
	}

	private void clear( )
	{
		doClear( );
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void doClear( )
	{
		beginMarker = new Node<>( null, null, null );
		endMarker = new Node<>( null, beginMarker, null );
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	/**
	 * Returns the number of items in this collection.
	 * @return the number of items in this collection.
	 */
	public int size( )
	{
		return theSize;
	}//size

	public boolean isEmpty( )
	{
		return size( ) == 0;
	}//isEmpty

	/**
	 * Adds an item to this collection, at the end.
	 * @param x any object.
	 * @return true.
	 */
	public boolean add( AnyType x )
	{
		add( size( ), x );   
		return true;         
	}//add

	/**
	 * Adds an item to this collection, at specified position.
	 * Items at or after that position are slid one position higher.
	 * @param x any object.
	 * @param idx position to add at.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
	 */
	public void add( int idx, AnyType x )
	{
		addBefore( getNode( idx, 0, size( ) ), x );
	}

	/**
	 * Adds an item to this collection, at specified position p.
	 * Items at or after that position are slid one position higher.
	 * @param p Node to add before.
	 * @param x any object.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
	 */    
	private void addBefore( Node<AnyType> p, AnyType x )
	{
		Node<AnyType> newNode = new Node<>( x, p.prev, p );
		newNode.prev.next = newNode;
		p.prev = newNode;         
		theSize++;
		modCount++;
	}   


	/**
	 * Returns the item at position idx.
	 * @param idx the index to search in.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType get( int idx )
	{
		return getNode( idx ).data;
	}

	/**
	 * Changes the item at position idx.
	 * @param idx the index to change.
	 * @param newVal the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException if index is out of range.
	 */
	public AnyType set( int idx, AnyType newVal )
	{
		Node<AnyType> p = getNode( idx );
		AnyType oldVal = p.data;

		p.data = newVal;   
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * @param idx index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<AnyType> getNode( int idx )
	{
		return getNode( idx, 0, size( ) - 1 );
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * @param idx index to search at.
	 * @param lower lowest valid index.
	 * @param upper highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
	 */    
	private Node<AnyType> getNode( int idx, int lower, int upper )
	{
		Node<AnyType> p;

		if( idx < lower || idx > upper )
			throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ));

		if( idx < size( ) / 2 )
		{
			p = beginMarker.next;
			for( int i = 0; i < idx; i++ )
				p = p.next;            
		}
		else
		{
			p = endMarker;
			for( int i = size( ); i > idx; i-- )
				p = p.prev;
		} 

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * @param idx the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove( int idx )
	{
		return remove( getNode( idx ) );
	}

	/**
	 * Removes the object contained in Node p.
	 * @param p the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove( Node<AnyType> p )
	{
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;

		return p.data;
	}
	//Finds the max number in the linkedList and then returns the reference 
	// to the linkedList
	private Node <AnyType> findMax(){
		Node<AnyType> currentMax = beginMarker.next;
		Node<AnyType> compareMax = beginMarker.next.next;
 		while(compareMax.next!= null){
			if((currentMax.data.compareTo(compareMax.data)) < 0 ){
				currentMax.data = compareMax.data;
			}//if
			compareMax = compareMax.next;
			//
		}//while
		return currentMax; 
	}//findMax
	//The O-Notation of findMax is O(n) with 
	// n being the length of the list (because we do 
	// not know how many items are being put into the list)
	
	public AnyType max(){
		return (isEmpty()? null: findMax().data);
	}//max
	

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString( )
	{
		StringBuilder sb = new StringBuilder( "[ " );

		for( AnyType x : this )
			sb.append( x + " " );
		sb.append( "]" );

		return new String( sb );
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator( )
	{
		return new LinkedListIterator( );
	}

	/**
	 * This is the implementation of the LinkedListIterator.
	 * It maintains a notion of a current position and of
	 * course the implicit reference to the MyLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<AnyType>
	{
		private Node<AnyType> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		public boolean hasNext( )
		{
			return current != endMarker;
		}

		public AnyType next( )
		{
			if( modCount != expectedModCount )
				throw new java.util.ConcurrentModificationException( );
			if( !hasNext( ) )
				throw new java.util.NoSuchElementException( ); 

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove( )
		{
			if( modCount != expectedModCount )
				throw new java.util.ConcurrentModificationException( );
			if( !okToRemove )
				throw new IllegalStateException( );

			MyLinkedList.this.remove( current.prev );
			expectedModCount++;
			okToRemove = false;       
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType>
	{
		public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
		{
			data = d; prev = p; next = n;
		}

		public AnyType data;
		public Node<AnyType>   prev;
		public Node<AnyType>   next;
	}
	
	//Generates random numbers
	int randomNumber (int min, int max){
		int range = (max - min) + 1;
		return(int)(Math.random()*range) + min;
	}//randomNumber
	
	private int theSize;
	private int modCount = 0;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
}

class TestLinkedList
{
	public static void main( String [ ] args )
	{
		MyLinkedList<Integer> myList = new MyLinkedList<>();
		MyLinkedList<Integer> myList2 = new MyLinkedList<>();
 		for(int i = 6; i <= 45; i+=3){
			myList.add(i);
		}//for
		System.out.println(myList);
		
		//Test run #1
		System.out.println("This is the max: " + myList.max());
		
		//Test run a
		myList.doClear();
		System.out.println("This is the new list: " + myList);
		System.out.println("This is my the max of the empty set: " + myList.max());
		
		//Test run b
		for(int i = 18; i <= 27; i++){
			myList2.add(i);
		}//for
		
		System.out.println("This is the new list: " + myList2);
		System.out.println("This is the max: " + myList2.max());
		
		//Test run c
		myList2.doClear();
		for(int i = 27; i >= 18; i--){
			myList2.add(i);
		}//for
		System.out.println("This is the same list in reverse order: " + myList2);
		System.out.println("This is the max: " + myList2.max());
		
		//Test run d
		myList2.doClear();
		for(int i = 0; i <= 9; i++){
			myList2.add((int)(Math.random()*100));
		}//for
		System.out.println("This is the new list " + myList2);
		System.out.println("This is the max: " + myList2.max());
		
	}//main
}//MyLinkedList
