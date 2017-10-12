package test.com.mianshi;

public class Counter {

	  public volatile  static int count = 0; //对于volatile修饰的变量，jvm虚拟机只是保证从主内存加载到线程工作内存的值是最新的  而线程工作内存中的值由于已经加载，不会产生对应的变化
	  
	    public   static  void inc() {
	 
	        //这里延迟1毫秒，使得结果明显
	      /*  try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	        }*/
	 
	        count++;
	    }
	 
	    public static void main(String[] args) {
	 
	        //同时启动1000个线程，去进行i++计算，看看实际结果
	 
	        for (int i = 0; i < 1000000; i++) {
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    inc();
	                }
	            }).start();
	        }
	 
	        //这里每次运行的值都有可能不同,可能为1000
	        System.out.println("运行结果:Counter.count=" + Counter.count);
	    }
}
