package test.com.mianshi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Interview {
	
	 private static void one(){
	        String str1 = "hello";
	        String str2 = "he"+new String("llo");
	        System.err.println(str1==str2);
	        System.out.println("1. false");
	 }
	 
	  private static void two(){
	        int i = Integer.MAX_VALUE;
	        System.err.println((i+1)<i);
	        System.out.println("2. 存在一个i, 使得(i+1)<i");
	    }
	  
	   private static void three(){
	        System.err.println("gc is not a Java Thread, it is a native thread");
	        Thread.getAllStackTraces().keySet().forEach(thread -> System.out.println(thread.getName()+"->"+thread.isDaemon()+" "+thread.getPriority()));
	        System.out.println("3. gc线程是daemon线程");
	    }
	   
	   private static volatile int count = 0;
	   private static void four(){
	        ExecutorService executorService = Executors.newCachedThreadPool();
	        for(int j=0; j<10; j++){
	            executorService.submit(()->{
	                for(int i=0; i<1000000; i++){
	                    count++;
	                }
	            });
	        }
	        System.out.println("count should be: "+10000000+", actual be: "+count);
	        System.out.println("4. volatile不能保证线程安全");
	    }
	 public static void main(String[] args) {
		 one();
		 two();
		 three();
		// four();
		 
		 
	}
}
