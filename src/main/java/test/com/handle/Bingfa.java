package test.com.handle;

import java.util.Vector;

/**
 *某几个函数的执行都会修改某个变量值，而且这几个函数在不同的线程中被调用，如何保证在多线程程序中，变量值是可预测的
答案:对该变量进行加锁
 * @author Administrator
 *
 */
public class Bingfa {

    private static Vector<Integer> vector = new Vector<Integer>();
	
    
    public void addInteger(){
        //使用变量锁，确保不同函数对该变量的操作是互斥的
        synchronized (vector) {
            for(int i = 0; i<5;i++){
                System.out.println("ADD "+ i);
                vector.add(i);
            }
        }
    }
    
    public void getInteger(){
        //使用变量锁，确保不同函数对该变量的操作是互斥的
        synchronized (vector) {
            while(vector.size()!=0){
                System.out.println("GET "+vector.get(0));
                vector.remove(0);
            }
        }
    }
    
    public static void main(String[] args) { 
        final Bingfa myt2 = new Bingfa(); 
        Thread t1 = new Thread(  new Runnable() {  public void run() {  myt2.addInteger();  }  }, "t1"  ); 
        Thread t2 = new Thread(  new Runnable() {  public void run() { myt2.getInteger();   }  }, "t2"  ); 
        t1.start(); 
        t2.start(); 
   }
    
    
    
}
