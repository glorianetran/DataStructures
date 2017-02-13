package for_review_2;

public class Deque<AnyType> {
	private int currentSize = 0;
	private int size = 100; 
	private int front; 
	private int back;
	private AnyType[] theArray = (AnyType[]) new Object[size];
	
	//empty method
	private boolean isEmpty(){
		return currentSize == -1; 
	}//isEmpty
	
	//doubleArray could have a constant runtime for average cases (Arguable) 
	public void doubleArray(){
		AnyType[] newArray = (AnyType[]) new Object[theArray.length*2];
		for(int i = 0; i < theArray.length; i++){
			newArray[i] = theArray[i];
		}//for
		theArray = newArray;
	}//doubleArray
	
	public AnyType push(AnyType x){
		if(currentSize == theArray.length){
			doubleArray();
		}//if
		theArray[++front] = x;
		return theArray[front];
	}//push 
	
	
	public AnyType inject(AnyType x){
		if(currentSize == theArray.length){
			doubleArray();
		}//if
		theArray[++back] = x;
		return theArray[back];
	}//pop
	
	public AnyType pop (){
		if(isEmpty()){
			front--;
		}//if
	}//inject
	
	public AnyType eject(){
		
	}//eject
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
