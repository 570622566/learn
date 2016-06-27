package test.com.handle;

import java.util.concurrent.atomic.AtomicBoolean;

public class Test8 {


	
	public static void main(String[] args) {
		AtomicBoolean wakeupCalled = new AtomicBoolean(true);
		
		if(wakeupCalled.getAndSet(false)){
		
			System.out.println("aa");
		}
		if(wakeupCalled.getAndSet(false)){
			
			System.out.println("bb");
		}

	}
}
