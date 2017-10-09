package test.com.handle;

import java.util.concurrent.locks.ReentrantLock;

/**'
 * 
 * 互斥锁在java中的实现就是 ReetranLock , 在访问一个同步资源时，它的对象需要通过方法 tryLock() 获得这个锁，如果失败，返回 false，成功返回true。根据返回的信息来判断是否要访问这个被同步的资源。看下面的例子
 * @author Administrator
 *
 */
public class ReentranLockExample {

	 private static int count = 0;  
	 private static ReentrantLock reentrantLock = new ReentrantLock();  
	 	
	 static class MyThread extends Thread{  
		  
	        @Override  
	        public void run() {  
	            super.run();  
	            try {  
	                while (true){  
	                    boolean result = reentrantLock.tryLock();  
	                    if (result){  
	                        System.out.println(Thread.currentThread().getName() + "get the lock success and run the syn code " + count ++);  
	                        reentrantLock.unlock();  
	                    }else{  
	                        System.out.println(Thread.currentThread().getName() + "get the lock failed and run the syn code " + count);  
	                    }  
	                    System.out.println(Thread.currentThread().getName() + "run the asyntronized code  " + count);  
	                    Thread.sleep(500);  
	                }  
	  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	 
	 public static void main(String[] args){  
	        MyThread thread1 = new MyThread();  
	        MyThread thread2 = new MyThread();  
	        thread1.start();  
	        thread2.start();  
	    } 
}
