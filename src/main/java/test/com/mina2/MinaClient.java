package test.com.mina2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {
	
	
	public static void main(String[] args) {
		
        // 创建客户端连接器.  	
        NioSocketConnector connector = new NioSocketConnector();  
        // 设置日志记录器  
        connector.getFilterChain().addLast("logger", new LoggingFilter());  
        // 设置编码过滤器  
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));   
        // 设置连接超时检查时间  
        connector.setConnectTimeoutCheckInterval(30);  
        // 设置事件处理器  
        connector.setHandler(new ClientHandler());  
        
        // 建立连接  
        ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 8901));  
        
        // 等待连接创建完成  
        cf.awaitUninterruptibly(); 
        IoSession session = cf.getSession();
        // 发送消息  
        //cf.getSession().write("Hi Server!");  
        session.write("020306&192.168.3.52&0302");
        // 发送消息  
       // cf.getSession().write("quit"); 
     // 等待连接断开  
        session.getCloseFuture().awaitUninterruptibly();  
        // 释放连接  
        connector.dispose();  
        
	}
}
