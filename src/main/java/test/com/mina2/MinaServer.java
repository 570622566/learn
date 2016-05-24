package test.com.mina2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 服务端主程序
 * @author Administrator
 *
 */
public class MinaServer {
	
    private static final int PORT = 8901;// 定义监听端口  
    
    public static void main(String[] args) throws Exception {
		
        IoAcceptor acceptor = new NioSocketAcceptor();  

        // 设置日志记录器  
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());  
        
        // 设置编码过滤器  
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));  
        
     // 指定业务逻辑处理器  
        acceptor.setHandler(new ServerHandler());  
        // 设置端口号  
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));  
        // 启动监听线程  
        acceptor.bind();  
	}
    
    
}
