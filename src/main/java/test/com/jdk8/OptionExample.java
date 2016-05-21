package test.com.jdk8;

import java.util.Optional;

public class OptionExample {
	
	
	public static void main(String[] args) {
		
		OptionExample opt = new OptionExample();
		Integer value1 = null;
		Integer value2 = new Integer(10);
		
		
	      //Optional.ofNullable - allows passed parameter to be null.
	      Optional<Integer> a = Optional.ofNullable(value1);

	      //Optional.of - throws NullPointerException if passed parameter is null
	      Optional<Integer> b = Optional.of(value2);
	      System.out.println(opt.sum(a,b));

		
		
	}

	private Integer  sum(Optional<Integer> a, Optional<Integer> b) {
	      //Optional.isPresent - checks the value is present or not

		  System.out.println("First parameter is present: " + a.isPresent());
	      System.out.println("Second parameter is present: " + b.isPresent());
	      
	      //Optional.orElse - returns the value if present otherwise returns
	      //the default value passed.
	      Integer value1 = a.orElse(new Integer(0));//orElse 返回结果,否则返回为自定的值
	      //Optional.get - gets the value, value should be present
	      Integer value2 = b.get();     
		
	      return value1 + value2;
	}
}
