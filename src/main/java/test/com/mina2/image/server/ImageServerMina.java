package test.com.mina2.image.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import test.com.mina2.image.oper.MyProtocalCodecFactory;

public class ImageServerMina {

	private static final int PORT = 9999;

	public static void main(String[] args) {

		NioSocketAcceptor acceptor=new NioSocketAcceptor();
		
		LoggingFilter lf = new LoggingFilter();
		lf.setSessionOpenedLogLevel(LogLevel.DEBUG);// 配置日志级别

		// 设置日志记录器
		acceptor.getFilterChain().addLast("logger", lf);
		
		acceptor.getFilterChain().addLast("code",new ProtocolCodecFilter(new MyProtocalCodecFactory("utf-8")));
		acceptor.setHandler(new MinaImageHander());
		try {
			acceptor.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server started on port： " + PORT);

		
	}
}
