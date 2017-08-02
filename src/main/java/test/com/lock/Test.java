package test.com.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/7/31.
 */
public class Test {

    /**
     *  计数器，用来控制线程,传入的参数表示计数器的个数
     */
    private final static CountDownLatch mCountDownLatch = new CountDownLatch(500);

    public static void main(String[] args) {
        Service service = new Service();

        for (int i = 0; i < 500; i++) {
            ThreadA threadA = new ThreadA(service,mCountDownLatch);
            threadA.start();
            mCountDownLatch.countDown();
        }

    }
}
