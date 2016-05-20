package test.com.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java8Test {

	public static void main(String[] args) {

		List<String> names1 = new ArrayList<String>();
		names1.add("Mahesh ");
		names1.add("Suresh ");
		names1.add("Ramesh ");
		names1.add("Naresh ");
		names1.add("Kalpesh ");

		List<String> names2 = new ArrayList<String>();
		names2.add("Mahesh ");
		names2.add("Suresh ");
		names2.add("Ramesh ");
		names2.add("Naresh ");
		names2.add("Kalpesh ");

		Java8Test tester = new Java8Test();
		System.out.println("Sort using Java 7 syntax: ");

		tester.sortUsingJava7(names1);
		System.out.println(names1);

		System.out.println("Sort using Java 8 syntax: ");
		tester.sortUsingJava8(names2);
		System.out.println(names2);

		// with type declaration //声明了一个接口的实现类
		MathOperation addition = (int a, int b) -> a + b;
		// with out type declaration
		MathOperation subtraction = (a, b) -> a - b;
		// with return statement along with curly braces
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};
		
		// without return statement and without curly braces
		MathOperation division = (int a, int b) -> a / b;
		System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	    System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	    System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	    System.out.println("10 / 5 = " + tester.operate(10, 5, division));
	    
	      //with parenthesis
	    GreetingService greetService1 = message -> 
	    System.out.println("Hello "+message);
	    
	    
	    //without parenthesis
	      GreetingService greetService2 = (message) ->
	      System.out.println("Hello " + message);
	      
	      greetService1.sayMessage("Mahesh");
	      greetService2.sayMessage("Suresh");

	      /**
	       * Lambda expressions are used primarily to define inline implementation of a functional interface, i.e., an interface with a single method only. In the above example, we've used various types of lambda expressions to define the operation method of MathOperation interface. Then we have defined the implementation of sayMessage of GreetingService.
			1.内联函数接口的实现
			Lambda expression eliminates the need of anonymous class and gives a very simple yet powerful functional programming capability to Java.	
	        2.隐去了匿名类 
	       */
	}
	
	interface GreetingService{
	      void sayMessage(String message);
	}
	
	private int operate(int a, int b, MathOperation mathOperation) {

		return mathOperation.operation(a, b);
	}

	interface MathOperation {
		int operation(int a, int b);
	}
	// sort using java 8

	private void sortUsingJava8(List<String> names2) {
		// TODO Auto-generated method stub
		Collections.sort(names2, (s1, s2) -> s1.compareTo(s2));
	}

	// sort using java 7

	private void sortUsingJava7(List<String> names) {
		// TODO Auto-generated method stub
		Collections.sort(names, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}

		});
	}

}
