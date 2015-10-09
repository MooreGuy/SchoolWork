package cis232.a2;

import weiss.util.*;

public class Main {
	public static void main(String[] args) {
		A2232GMoo<String> myArrayList = new A2232GMoo<String>();
		myArrayList.add("b");
		printCollection(myArrayList);
		myArrayList.add("a");
		printCollection(myArrayList);
		myArrayList.add("c");
		printCollection(myArrayList);
		myArrayList.add("e");
		printCollection(myArrayList);
		myArrayList.add("c");
		printCollection(myArrayList);
		myArrayList.add("c");	
		printCollection(myArrayList);
		myArrayList.add(4, "c");	
		printCollection(myArrayList);
		System.out.println(myArrayList.set(3, "z") + "\n");
		printCollection(myArrayList);

		System.out.println(A2232GMoo.binarySearch(myArrayList, "c"));
	}

	public static <AnyType> void printCollection(
			AbstractCollection<AnyType> collection) {
		for(AnyType any : collection) {
			System.out.println(any);
		}
		System.out.println("");
	}
}
