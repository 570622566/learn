package test.com.mina2.image.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import test.com.mina2.image.oper.MyProtocalCodecFactory;

public class ImageServerMina {

	public static void main(String[] args) {

		NioSocketAcceptor acceptor=new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("code",new ProtocolCodecFilter(new MyProtocalCodecFactory("utf-8")));
		acceptor.setHandler(new MinaImageHander());
		try {
			acceptor.bind(new InetSocketAddress(9999));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
