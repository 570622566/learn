package test.com.lock;

/**
 * Created by Administrator on 2017/7/31.
 */
public class LoggingWidget extends Widget {

    public static void main(String[] args) {
        LoggingWidget lw=new LoggingWidget();
        lw.doSomething();

    //    可见，在java内部，调用父类的synchronized方法和调用自己类中其他synchronized方法都不会阻碍该程序的运行，正是因为java线程是基于“每线程（per-thread）”，而不是基于“每调用的（per-invocation）”的。
        // 重进入的实现是通过为每个锁关联一个请求计数和一个占有它的线程


    }
    public synchronized void doSomething(){
        System.out.println("LoggingWidget->doSomething()");
        doAnotherThing();         //调用自己类中其他的synchronized方法
        super.doSomething();   //调用父类的synchronized方法
    }
    private synchronized void doAnotherThing(){
        System.out.println("LoggingWidget->doAnotherThing()");
    }
}
class Widget{
    public synchronized void doSomething(){
        System.out.println("Widget->doSomething()");
    }
}
