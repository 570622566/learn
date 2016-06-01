package test.com.designpattern.create.singleton.safesynchronized;

public class Singleton {
	
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static Singleton instance = null;
    
    /* 私有构造方法，防止被实例化 */
    private Singleton() {
    }

	
    /**
     * synchronized关键字锁住的是这个对象，这样的用法，在性能上会有所下降，因为每次调用getInstance()，
     * 都要对对象上锁，
     * 事实上，只有在第一次创建对象的时候需要加锁，之后就不需要了
     * @return
     */
	public static synchronized Singleton getInstance() { 
        if (instance == null) {
                instance = new Singleton();
        }
        return instance;
	}
	
	
	public static Singleton getInstance_1() {
        if (instance == null) {
                synchronized (instance) {
                        if (instance == null) {
                                instance = new Singleton();
                        }
                }
        }
        return instance;
}
	
	
	
}
