package test.com.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	
	public static void main(String[] args) {
		
	String host =args[0];
	int port = Integer.parseInt(args[1]);
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	
	Bootstrap b = new Bootstrap();
	
	b.group(workerGroup);
	b.channel(NioSocketChannel.class);
	b.option(ChannelOption.SO_KEEPALIVE, true);
	b.handler(new ChannelInitializer<SocketChannel>() {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// TODO Auto-generated method stub
			ch.pipeline().addLast( new TimeClientHandler());
		}
	});
	
		
	}
}
