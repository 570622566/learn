package test.com.mina2.image.client;

import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import test.com.mina2.image.oper.MyProtocalCodecFactory;

public class ImageMinaClient {

	
	public static void main(String args[])
	{
		NioSocketConnector conn=new NioSocketConnector();
		LoggingFilter lf = new LoggingFilter();
		lf.setSessionOpenedLogLevel(LogLevel.DEBUG);// 配置日志级别

		// 设置日志记录器
		conn.getFilterChain().addLast("logger", lf);
		conn.getFilterChain().addLast("code",new ProtocolCodecFilter(new MyProtocalCodecFactory("utf-8")));

		conn.setHandler(new minaImageHander());
		conn.connect(new InetSocketAddress("127.0.0.1",9999));
		
	}

}
