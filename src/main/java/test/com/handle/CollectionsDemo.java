package test.com.handle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CollectionsDemo {
	public static void main(String args[]) {
		// create arraylist
		ArrayList<String> arlst = new ArrayList<String>();

		// populate the list
		arlst.add("TP");
		arlst.add("PROVIDES");
		arlst.add("QUALITY");
		arlst.add("TUTORIALS");

		// search the list for key 'QUALITY'
		int index = Collections.binarySearch(arlst, "QUALITY");

		System.out.println("'QUALITY' is available at index: " + index);
		
		
		 char charArr[] = {'a', 'c', 'b', 'e','d'};

		    // sorting array
		    Arrays.sort(charArr);

		    // let us print all the elements available in list
		    System.out.println("The sorted char array is:");
		    for (char number : charArr) {
		      System.out.println("Number = " + number);
		    }

		    // entering the value to be searched
		    char searchVal = 'e';

		    int retVal = Arrays.binarySearch(charArr, searchVal);
		    System.out.println("The index of e is : " + retVal);

	}
}
