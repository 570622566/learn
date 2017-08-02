package test.com.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/7/31.
 */
public class ThreadA extends Thread {

    private Service service;
    private  CountDownLatch countDownLatch;

    public ThreadA(Service service, CountDownLatch countDownLatch) {
        this.service = service;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await(); //CountDowmLatch是一种灵活的闭锁实现，包含一个计数器，该计算器初始化为一个正数，表示需要等待事件的数量。countDown方法递减计数器，表示有一个事件发生，而await方法等待计数器到达0，表示所有需要等待的事情都已经完成。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.seckill();
    }

}
