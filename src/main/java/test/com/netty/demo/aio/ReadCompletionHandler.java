package test.com.netty.demo.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	
    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
	if (this.channel == null)
	    this.channel = channel;
    }

	
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		attachment.flip();//为后续缓冲区读取数据做好准备
		byte[] body = new byte[attachment.remaining()];//根据缓冲区的可读字节数创建字节数组,然后根据new String方法创建请求消息,并对消息进行判断
		
		attachment.get(body);
		try {
		    String req = new String(body, "UTF-8");
		    System.out.println("The time server receive order : " + req);
		    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new java.util.Date(
			    System.currentTimeMillis()).toString() : "BAD ORDER";
		    doWrite(currentTime);//调用dowWrite方法返回给客户端
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	}

	private void doWrite(String currentTime) {
		// TODO Auto-generated method stub
		if (currentTime != null && currentTime.trim().length() > 0) {
		    byte[] bytes = (currentTime).getBytes();
		    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		    writeBuffer.put(bytes);
		    writeBuffer.flip();
		    channel.write(writeBuffer, writeBuffer,
			    new CompletionHandler<Integer, ByteBuffer>() {
				@Override
				public void completed(Integer result, ByteBuffer buffer) {
				    // 如果没有发送完成，继续发送
				    if (buffer.hasRemaining())
					channel.write(buffer, buffer, this);
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
				    try {
					channel.close();
				    } catch (IOException e) {
					// ingnore on close
				    }
				}
			    });
		}
	}


	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		try {
		    this.channel.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}


}
