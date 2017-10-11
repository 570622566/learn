package test.com.handle;

import java.util.Arrays;

public class ArrayDemo {
	
	public static void main(String[] args) {
		
		 // initializing unsorted byte array
	    byte byteArr[] = {10,20,15,22,35};

	    // sorting array
	    Arrays.sort(byteArr);

	    // let us print all the elements available in list
	    System.out.println("The sorted byte array is:");
	    for (byte number : byteArr) {
	      System.out.println("Number = " + number);
	    }

	    // entering the value to be searched
	    byte searchVal = 35;

	    // entering the range of index
	    int retVal = Arrays.binarySearch(byteArr,2,5,searchVal);

	    System.out.println("The index of element 35 is : " + retVal);
	}
}
