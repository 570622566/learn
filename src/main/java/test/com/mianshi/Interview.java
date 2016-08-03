package test.com.mianshi;

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
	 public static void main(String[] args) {
		 one();
		 two();
		 three();
		 
		 
	}
}
