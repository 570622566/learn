package test.com.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter  {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)  {
		ByteBuf in = (ByteBuf) msg;
	    try {
	        while (in.isReadable()) { // (1)
	            System.out.print((char) in.readByte());
	            System.out.flush();
	        }
	    } finally {
	        ReferenceCountUtil.release(msg); // (2)
	    }
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
        ctx.close();

	}
	

}
