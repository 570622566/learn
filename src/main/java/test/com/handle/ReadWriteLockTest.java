package test.com.handle;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock中定义了2个内部类, ReentrantReadWriteLock.ReadLock和ReentrantReadWriteLock.WriteLock, 分别用来代表读取锁和写入锁. ReentrantReadWriteLock对象提供了readLock()和writeLock()方法, 用于获取读取锁和写入锁. 
 
读取锁允许多个reader线程同时持有, 而写入锁最多只能有一个writter线程持有.
读写锁的使用场合: 读取共享数据的频率远大于修改共享数据的频率. 在上述场合下, 使用读写锁控制共享资源的访问, 可以提高并发性能.
如果一个线程已经持有了写入锁, 则可以再持有读写锁. 相反, 如果一个线程已经持有了读取锁, 则在释放该读取锁之前, 不能再持有写入锁.
可以调用写入锁的newCondition()方法获取与该写入锁绑定的Condition对象, 此时与普通的互斥锁并没有什么区别. 但是调用读取锁的newCondition()方法将抛出异常. 
 * @author Administrator
 *
 */
public class ReadWriteLockTest {

    private static ReadWriteLock lock = new ReentrantReadWriteLock();  
    private static Person person = new Person("David Beckham", true);  
  
    public static void main(String[] args) {  
    	
    	
    	
        new Thread(){  
            public void run() {  
                while(true) {  
                    try {  
                        lock.readLock().lock();
                     //   System.out.println("读锁，读操作。。");
                        System.out.print("name = " + person.getName());  
                        System.out.println(", isMan = " + person.isMan());  
                    } finally {  
                        lock.readLock().unlock();  
                    }  
                }  
            };  
        }.start();  
        
        
        new Thread() {  
            public void run() {  
                boolean state = true;  
                while(true) {  
                    try {  
                        lock.writeLock().lock();  
                        
                   //     System.out.println("获取写锁，开始写操作。。");
                        if (state) {  
                            person.setName("Lady GaGa");  
                            person.setMan(false);  
                            state = false;  
                        } else {  
                            person.setName("David Beckham");  
                            person.setMan(true);  
                            state = true;  
                        }  
                          
                    } finally {  
                        lock.writeLock().unlock();  
                    }  
                }  
            };  
        }.start();  
    }  
}  


  
class Person {  
	
    private String name;  
    private boolean isMan;  
  
    public Person(String name, boolean isMan) {  
        this.name = name;  
        this.isMan = isMan;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public boolean isMan() {  
        return isMan;  
    }  
  
    public void setMan(boolean isMan) {  
        this.isMan = isMan;  
    }  
}  