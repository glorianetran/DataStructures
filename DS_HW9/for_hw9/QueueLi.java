package for_hw9;
//Gloriane Tran
//Data Structures 124
//Assignment #9
//October 29th, 2015

public class QueueLi<E> {

	private Node<E> beginMarker;
	private Node<E> endMarker;
	int size = 0; 

	private static class Node<E> {

		public Node(E x, Node<E> p){
			data = x; 
			next = p;
		}//Node

		public E data; 
		public Node<E> next;
	}//Node

	public QueueLi(){
		beginMarker = null;
		endMarker = null;
	}//QueueLi constructor

	//TODO: Runtime: theta(1)
	public boolean isEmpty(){
		return size == 0;
	}//Empty
	
	//TODO: Runtime: theta(1)
	public void empty(){
		beginMarker = null;
		endMarker = null;
		size = 0;
	}//empty
	
	//TODO: Runtime: theta(1)
	public int size(){
		return size; 
	}//size

	
	//TODO: Runtime: theta(1)
	public E enqueue(E x){ 
		Node<E> temp = new Node<>(x, null);
		if(isEmpty()){
			beginMarker = temp;
			endMarker = temp;
			size++;
			return x;
		}else{
		endMarker.next = temp;
		endMarker = temp;
		size++;
		return x;
		}//else
	}//enqueue 

	//TODO: Runtime: theta(1)
	public E dequeue(){
		if(isEmpty()){
			System.out.println("Your list is empty yo");
			return null;
		}else{
			E currentItem = beginMarker.data;
			beginMarker = beginMarker.next;
			size--;
			return currentItem;
		}//else
	}//dequeue

	//TODO: Runtime: theta(1) 
	public E getFront(){
	if(!isEmpty()){
		return beginMarker.data;
	}else{
		return null;
	}//else
	}//peak

	public static void main(String[] args) {
	//queue of integers 
	QueueLi<Integer> q1 = new QueueLi<>();
	//queue of strings 
	QueueLi<String> q2 = new QueueLi<>();
	//empty list 
	QueueLi<String> q3 = new QueueLi<>();

	//enqueue integers into integer list
	System.out.println("\nEnqueued " + q1.enqueue(5) + " into integer list. The size is now " + q1.size() );
	System.out.println("Enqueued " + q1.enqueue(2) + " into integer list. The size is now " + q1.size() );
	System.out.println("Enqueued " + q1.enqueue(3) + " into integer list. The size is now " + q1.size() );
	
	//enqueue strings into strings list 
	System.out.println("\nEnqueued " + q2.enqueue("Apple") + " into string list. The size is now " + q2.size() );
	System.out.println("Enqueued " + q2.enqueue("Banana") + " into string list. The size is now " + q2.size() );
	System.out.println("Enqueued " + q2.enqueue("Cake") + " into string list. The size is now " + q2.size() );
	System.out.println("Enqueued " + q2.enqueue("Pineapple") + " into string list. The size is now " + q2.size() );
	
	//check to see if the list is empty (if empty method works)
	if(q1.isEmpty()){
		System.out.println("\nThe list of integers is empty.");
	}else{
		System.out.println("\nThe list of integers is not empty.");
	}//if
	
	if(q2.isEmpty()){
		System.out.println("The list of strings is empty.");
	}else{
		System.out.println("The list of strings is not empty.");
	}//if
	
	if(q3.isEmpty()){
		System.out.println("The empty list is empty.");
	}else{
		System.out.println("The empty list is not empty.");
	}//if

	//check to see peak method (Note to self: FIFO)
	System.out.println("\nThe first item of the integer list of size " + q1.size() + " is "  + q1.getFront());
	System.out.println("The first item of the string list of size " + q2.size() + " is "  + q2.getFront());
	System.out.println("The first item of an empty list of size " + q3.size() + " is "  + q3.getFront());
	
	//calling dequeue
	System.out.println("\nDequeued " + q1.dequeue() + " from integer list. The size is now " + q1.size());
	System.out.println("Dequeued " + q2.dequeue() + " from string list. The size is now " + q2.size());
	System.out.println("Dequeued " + q3.dequeue() + " from integer list. The size is now " + q3.size());
	
	//testing to make sure dequeue and getFront worked
	System.out.println("\nThe first integer of the integer list of size " + q1.size() + " is "  + q1.getFront());
	System.out.println("The first string of the string list of size " + q2.size() + " is "  + q2.getFront());
	System.out.println("The first item of the empty list of size " + q3.size() + " is "  + q3.getFront());
	
	//dequeue again to make sure it works
	System.out.println("\nDequeued " + q1.dequeue() + " from integer list of size " + q1.size());
	System.out.println("Dequeued " + q2.dequeue() + " from integer list of size " + q2.size());
	System.out.println("The first integer of the integer list of size " + q1.size() + " is "  + q1.getFront());
	System.out.println("The irst string of the string list of size " + q2.size() + " is "  + q2.getFront());
	
	//enqueue again to make sure it works
	System.out.println("\nEnqueued " + q1.enqueue(66) + " into integer list of size " + q1.size() );
	System.out.println("Enqueued " + q2.enqueue("JackRabbit") + " into string list of size " + q2.size() );
	System.out.println("Dequeued " + q1.dequeue() + " from integer list of size " + q1.size());
	System.out.println("Dequeued " + q2.dequeue() + " from string list of size " + q2.size());
	System.out.println("The first integer of the integer list of size " + q1.size() + " is "  + q1.getFront());
	System.out.println("The first string of the string list of size " + q2.size() + " is "  + q2.getFront());
	
	//checking that we can empty the lists
	q1.empty();
	System.out.println("Checking to see if the first list was emptied: " + q1.getFront());
	q2.empty();
	System.out.println("Checking to see if the second list was emptied: " + q2.getFront());
	
	}//main
}//QueueLi