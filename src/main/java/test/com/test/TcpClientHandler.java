package test.com.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		  System.out.println("client接收到服务器返回的消息:"+msg);
	}

	
}
