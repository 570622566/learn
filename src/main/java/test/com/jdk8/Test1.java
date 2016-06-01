package test.com.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test1 {
	
	public static void main(String[] args) {
		
	    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	    Stream<Integer> stream = numbers.stream();
	    
	    stream.filter((x) -> {
	    	return x % 2 ==0;
	    }).map((x) -> {
	    	return x*x;
	    }).forEach(System.out::println);
	    
	    
	    Stream<Long> natural = Stream.generate(new NaturalSupplier());
	    natural.map((x) -> {
	    	return x *x;
	    }).limit(10).forEach(System.out::println);
		
	    //生成斐波那契数列
        Stream<Long> fibonacci = Stream.generate(new FibonacciSupplier());
        fibonacci.limit(10).forEach(System.out::println);
	    
        //自己另外写一个
  
	    
	    for(int i= 1 ; i< 10 ;i++){
	    	System.out.println(i+": "+fib(i));
	    }
	    
	}
	
	public static long fib(int n){
		if(n<=1){
			return n;
		}else{
			return fib(n-1)+fib(n-2);
		}
	}
}
