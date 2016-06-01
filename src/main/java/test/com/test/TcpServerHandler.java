package test.com.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpServerHandler extends SimpleChannelInboundHandler<Object>{
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
       System.out.println("SERVER接收到消息:"+msg);  

        ctx.channel().writeAndFlush("yes, server is accepted you ,nice !"+msg);  

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();  
	}
	
	
	

}
