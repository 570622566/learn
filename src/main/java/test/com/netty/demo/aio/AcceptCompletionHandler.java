package test.com.netty.demo.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeSeverHandler>{

	@Override
	public void completed(AsynchronousSocketChannel result, AsyncTimeSeverHandler attachment) {
		// TODO Auto-generated method stub
		attachment.asynchronousServerSocketChannel.accept(attachment, this);
		//当我们调用AsynchronousServerSocketChannel的accpt方法后,如果有新的客户单连接接入,系统将回调我们传入的
		//CompletionHandler实现的completed方法,表示接入成功.当我们继续调用它的acctpe方法,接入其他的客户单连接,形成一个循环.
		//每当一个客户连接成功之后,再异步接受新的客户端连接.
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new ReadCompletionHandler(result));
		//接受缓冲区,异步channel携带的附件,接收通知回调的业务handler
	}

	@Override
	public void failed(Throwable exc, AsyncTimeSeverHandler attachment) {
		// TODO Auto-generated method stub
		exc.printStackTrace();
		attachment.latch.countDown();
	}



}
