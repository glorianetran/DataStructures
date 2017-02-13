package for_HW7;

// Gloriane Tran
// O (m + n) with m being the number of items in one list
// and n being the number of items in the other list 
import java.util.*;

public class TestPrintLots {

	private static <AnyType> void printLots(List<AnyType> l, List<Integer> p){
		List<AnyType> resultList = new LinkedList<>();

		//if there's an empty list (Change later to be specific if I don't have time)
		if(l.isEmpty() || p.isEmpty()) {
			System.out.println("Your list is empty.");
			return;
		}//empty check

		Iterator<AnyType> lRator = l.iterator(); 
		Iterator<Integer> pRator = p.iterator();
		Integer currentPVal = 0;
		Integer lastPVal = 0;
		Integer temp = 0;
		Integer pValDifference = 0;
		int lsize = l.size();
		AnyType lItem  = null;

		while(pRator.hasNext()){ 
			temp = pRator.next();
			if(temp > 0 && temp <= lsize){
				lastPVal = currentPVal;
				currentPVal = temp;
				pValDifference = currentPVal - lastPVal;
				for(int i = 0; i < pValDifference; i++){
					if(lRator.hasNext()){
						lItem = lRator.next();
					}//if 
				}//for
				resultList.add(lItem);
			}//if
		}//while (nothing left in p list)

		System.out.println("List of values in requested positions: " + resultList);
	}//printLots


	// sample testing
	// you need to provide additional testing samples including String objects
	public static void main(String[] args)
	{
		//Testing Integers
		LinkedList<Integer> list1 = new LinkedList<>();
		for (int i = 0; i < 20; i++)
			list1.add(i * 10);
		System.out.println("List of values: " + list1);
		LinkedList<Integer> list2 = new LinkedList<>();
		for (int i = -6; i < list1.size() + 10; i = i + 3)
			list2.add(i);
		System.out.println("List of positions: " + list2);
		printLots(list1, list2);
		
		//Testing Strings
		LinkedList<String> list3 = new LinkedList<>();
		list3.add("Basketball");
		list3.add("Capricorn");
		list3.add("Elephantitis");
		list3.add("Donkey");
		list3.add("Tamale");
		list3.add("String");
		list3.add("UV");
		list3.add("Zoink");
		System.out.println("\nList of values: " + list3);
		LinkedList<Integer> list4 = new LinkedList<>();
		for(int i = -18; i < 20; i = i + 2){
			list4.add(i);
		}//for
		System.out.println("List of positions: " + list4);
		printLots(list3, list4);
		
		//Testing empty lists
		LinkedList<Integer> list5 = new LinkedList<>();
		System.out.println("\nList of values: " + list5);
		System.out.println("List of positions: " + list4);
		printLots(list5, list4);
		
		System.out.println("\nList of values: " + list3);
		System.out.println("List of positions: " + list5);
		printLots(list3, list5);
		
	}//main
}//TestPrintLots
