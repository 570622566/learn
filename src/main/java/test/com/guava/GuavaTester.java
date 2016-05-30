package test.com.guava;

import java.util.Optional;

public class GuavaTester {

	public static void main(String args[]){
	      GuavaTester guavaTester = new GuavaTester();

	      Integer invalidInput = null;

	      Optional<Integer> a =  Optional.of(invalidInput);
	      Optional<Integer> b =  Optional.of(new Integer(10));
	      
	      System.out.println(guavaTester.sum(a,b));      
	   }

	   public Integer sum(Optional<Integer> a, Optional<Integer> b){
	      return a.get() + b.get();
	   }	
	   /**
	    * Exception in thread "main" java.lang.NullPointerException
	at java.util.Objects.requireNonNull(Objects.java:203)
	at java.util.Optional.<init>(Optional.java:96)
	at java.util.Optional.of(Optional.java:108)
	at test.com.guava.GuavaTester.main(GuavaTester.java:12)

	    * 
	    */
	
}
