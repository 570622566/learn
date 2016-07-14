package test.com.netty.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeSeverHandler implements Runnable {

	
	private int port;
	CountDownLatch latch;
	AsynchronousServerSocketChannel asynchronousServerSocketChannel;
	
	public AsyncTimeSeverHandler(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
		try {
			asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			asynchronousServerSocketChannel.bind(new InetSocketAddress( port));
			System.out.println("the time server is start in port:"+port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);//完成一组正在执行的操作之前,允许当前线程一直阻塞.防止服务器执行完成退出
		
		doAccept();//接受客户端连接
		
	}

	private void doAccept() {
		// TODO Auto-generated method stub
		asynchronousServerSocketChannel.accept(this,new AcceptCompletionHandler());
		//接受accept操纵成功的通知消息,
	}

}
