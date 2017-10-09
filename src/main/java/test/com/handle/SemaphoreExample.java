package test.com.handle;

import java.util.concurrent.Semaphore;

/**
 * 
 * 信号量相当于一个计数器，如果线程想要访问某个资源，则先要获得这个资源的信号量，并且信号量内部的计数器减1 ，信号量内部的计数器大于0则意味着有可以使用的资源,当线程使用完某个资源时，必须释放这个资源的信号量。信号量的一个作用就是可以实现指定个线程去同事访问某个资源。只需要在初始化 。 
信号量在 Java中的实现是 Semaphore  ，其在初始化时传入一个整型数， 用来指定同步资源最大的并发访问量
 * @author Administrator
 *
 */
public class SemaphoreExample {
	
    private static Semaphore semaphore = new Semaphore(2);  //同步资源最大的并发量

    private String lock = "lock";  
    
    private static int count = 0;  
    
    static class MyThread extends Thread {  
    	  
        @Override  
        public void run() {  
            super.run();  
            try {  
                while (true) {  
                    semaphore.acquire();  
                    Thread.sleep(500);  
                    System.out.println(Thread.currentThread().getName() + "get the lock success and run the syn code " + count++);  
                    semaphore.release();  
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
        MyThread thread3 = new MyThread();  
        MyThread thread4 = new MyThread();  
        MyThread thread5 = new MyThread();  
        thread1.start();  
        thread2.start();  
        thread3.start(); 
        thread4.start(); 
        thread5.start(); 
    }  
    
}
