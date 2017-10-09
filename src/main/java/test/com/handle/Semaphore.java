package test.com.handle;

public class Semaphore {
	private boolean signal = false; // 使用signal可以避免信号丢失

	public synchronized void take() {
		this.signal = true;
		this.notify();
	}

	public synchronized void release() throws InterruptedException {
		while (!this.signal) // 使用while避免假唤醒
			wait();
		this.signal = false;
	}
}
