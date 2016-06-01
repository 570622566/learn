package test.com.jdk8;

import java.util.function.Supplier;

public class NaturalSupplier implements Supplier<Long> {

	
	long value =0;
	
	@Override
	public Long get() {
		// TODO Auto-generated method stub
        this.value = this.value + 1;

		return this.value;
	}

}
