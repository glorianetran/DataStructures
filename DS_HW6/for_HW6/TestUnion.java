package for_HW6;
//Gloriane Tran
// Θ(size + size) if m is one size and n is another size is Θ(m+n) 
import java.util.*;
public class TestUnion {

	public static <AnyType extends Comparable<AnyType>> List<AnyType>
	union (List<AnyType> list1, List<AnyType> list2) {

		Iterator itr1 = list1.iterator();
		Iterator itr2 = list2.iterator();

		List<AnyType> unionList = new LinkedList<AnyType>();

		//check the list is empty
		if(!itr1.hasNext() || !itr2.hasNext()){
			if(!itr1.hasNext()){
				return list2;
			}//if
			return list1;
		}//if

		AnyType item1 = (AnyType) itr1.next();
		AnyType item2 = (AnyType) itr2.next();
		
		while(itr1.hasNext() && itr2.hasNext()){
			if(item1.compareTo(item2) < 0){
				unionList.add(item1);
				item1 = (AnyType) itr1.next();
			}else if (item1.compareTo(item2) > 0){				
				unionList.add(item2);
				item2 = (AnyType)itr2.next();
			}else{
				unionList.add(item1);
				item1 = (AnyType) itr1.next();
				item2 = (AnyType) itr2.next();
			}//else
		}//while
		
		if(!itr1.hasNext()){
			unionList.add(item1);
		}else{
			unionList.add(item2);
		}//if
		
		while(itr1.hasNext()){
			unionList.add(item1);
			item1 = (AnyType) itr1.next();
			if(!itr1.hasNext()){
				unionList.add(item1);
			}//if
		}//while
			
		while(itr2.hasNext()){
		unionList.add(item2);
		item2 = (AnyType) itr2.next();
		if(!itr2.hasNext()){
			unionList.add(item2);
		}//if
		}//while
		
		return unionList;
	}//union

	//sample testing
	//you need to provide additional testing samples including String objects
	public static void main(String[] args)
	{
		LinkedList<Integer> list1 = new LinkedList<>();
		for (int i = 0; i < 20; i++){
			list1.add(i);
		}//for
		System.out.println("Integer test (big list, small list)");
		System.out.println("List 1: " + list1);
		LinkedList<Integer> list2 = new LinkedList<>();
		for (int i = -6; i < list1.size() + 10; i = i + 3){
			list2.add(i);
		}//for
		System.out.println("List 2: " + list2);
		List<Integer> resultList = union(list1, list2);
		System.out.println("Union is: " + resultList);
		resultList = union(list2, list1);
		System.out.println("Union is: " + resultList);
		
		LinkedList<String> list3 = new LinkedList<>();
		LinkedList<String> list4 = new LinkedList<>();
		
		System.out.println("\nString test (big list, small list)");
		list3.add("Aardvark");
		list3.add("Catup");
		list3.add("Elephantitis");
		list3.add("Gottem");
		list3.add("Lonely");
		list3.add("Octa");
		list3.add("Weezy");
		list3.add("Zinger");
		System.out.println("List 3: " + list3);
		list4.add("Catup");
		list4.add("Glorious");
		list4.add("Hafting");
		list4.add("Lava");
		list4.add("Translucent");
		list4.add("Yoyo");
		System.out.println("List 4: " + list4);
		List<String> resultList2 = union(list3, list4);
		System.out.println("Union is: " + resultList2);
		resultList2 = union(list4, list3);
		System.out.println("Union is: " + resultList2);
		
		System.out.println("\nEmpty List test (empty list, small list)");
		LinkedList<String> list5 = new LinkedList<>();
		System.out.println("List 5: " + list5);
		System.out.println("List 3: " + list3);
		List<String> resultList3 = union(list5, list3);
		System.out.println("Union is: " + resultList3);
		resultList3 = union(list3, list5);
		System.out.println("Union is: " + resultList3);
		
		System.out.println("\nEmpty List test (empty list, empty list)");
		LinkedList<String> list6 = new LinkedList<>();
		System.out.println("List 5: " + list5);
		System.out.println("List 6: " + list6);
		List<String> resultList4 = union(list5, list6);
		System.out.println("Union is: " + resultList4);
		resultList4 = union(list6, list5);
		System.out.println("Union is: " + resultList4);
		
	}//main
}//TestUnion