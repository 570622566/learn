package test.com.mianshi;

public class Test {
	
	public static void main(String[] args) {  
		
        MyThread thread = new MyThread();  
        Thread a = new Thread(thread, "t1");  
        Thread b = new Thread(thread, "t2");  
  
        a.start();  
        b.start();  
  
        try {  
            a.join();  
            b.join();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        System.out.println(thread.num);  //这是内存不可见引起的
    }  
	
}


	class MyThread implements Runnable {  
	    int num = 1000000;  
	    public void run() {  
	        if (Thread.currentThread().getName().equals("t1")) {  
	            increment();  
	        } else {  
	            decrement();  
	        }  
	    }  
	  
	    public synchronized  void increment() {  
	        for (int i = 0; i < 100; i++) {  
	        	 try {
	 	            Thread.sleep(1);
	 	        } catch (InterruptedException e) {
	 	        }
	            num++;  
	        }  
	    }  
	  
	    public synchronized  void decrement() {  
	        for (int i = 0; i < 100; i++) {  
	        	 try {
	 	            Thread.sleep(1);
	 	        } catch (InterruptedException e) {
	 	        }
	            num--;  
	        }  
	    }  
	} 

