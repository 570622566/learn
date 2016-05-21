package test.com.jdk8;

import java.util.function.Supplier;

public class FibonacciSupplier implements Supplier<Long> {
	
	long a =0;
	long b =1;
	
	@Override
	public Long get() {
		long x = a +b;
		a =b;
		b =x;
		return a;
	}

}
