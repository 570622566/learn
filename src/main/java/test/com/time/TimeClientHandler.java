package test.com.time;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m = (ByteBuf) msg;
		
		try {
			long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000l;
			System.out.println(new Date(currentTimeMillis));
			ctx.close();
		} finally {
			// TODO: handle finally clause
			m.release();
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
        cause.printStackTrace();
        ctx.close();

	}

	
	
	
	
}
