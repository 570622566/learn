package test.com.handle;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *  CountDownLatch 实现一个等待机制，在诸如 等待与会者到达后，开始会议的使用中。ConutDownLatch 在初始化中一个计数器，用来指定需要等待的个数。在并发编程中，所解决的需求就是，等待所有的线程到达某个点后。才开始进行下一步，有点类似于开会，只有当所有的与会人员都到齐后，会议才能开始
 * @author Administrator
 *
 */
public class CountDownLatchExample {

	private static CountDownLatch mCountDownLatch = new CountDownLatch(3);

	static class MyThread extends Thread {
		int awaitTime;

		public MyThread(int i) {
			this.awaitTime = i;
		}

		@Override
		public void run() {
			super.run();
			try {
				while (true) {
					Thread.sleep(awaitTime);
					System.out.println(Thread.currentThread().getName() + "arrived ");
					mCountDownLatch.countDown();
					mCountDownLatch.await(); // 可以指定等待时间
					System.out.println(Thread.currentThread().getName() + "start meeting ");
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyThread thread1 = new MyThread(500);
		MyThread thread2 = new MyThread(1000);
		MyThread thread3 = new MyThread(2000);
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
