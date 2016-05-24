package test.com.mina2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端主程序
 * @author Administrator
 *
 */
public class MinaServer {
	
    private static final int PORT = 8901;// 定义监听端口  
    /** 30秒后超时 */  
    private static final int IDELTIMEOUT = 30;  
    /** 15秒发送一次心跳包 */  
    private static final int HEARTBEATRATE = 15;  
    private static final String HEARTBEATREQUEST = "0x11";  
    private static final String HEARTBEATRESPONSE = "0x12";  
    private static final Logger LOG = LoggerFactory.getLogger(MinaServer.class);  


    public static void main(String[] args) throws Exception {
		
        IoAcceptor acceptor = new NioSocketAcceptor();  
        
        acceptor.getSessionConfig().setReadBufferSize(1024);  
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDELTIMEOUT);
        
        
        // 设置日志记录器  
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());  
        
        // 设置编码过滤器  
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));  
        
        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();  

        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,  
                IdleStatus.BOTH_IDLE);  
        //设置是否forward到下一个filter  
        heartBeat.setForwardEvent(true);  
        //设置心跳频率  
        heartBeat.setRequestInterval(HEARTBEATRATE);  // 本服务器为被定型心跳  即需要每10秒接受一个心跳请求  否则该连接进入空闲状态 并且发出idled方法回调
        
        acceptor.getFilterChain().addLast("heartbeat", heartBeat);  

        // 指定业务逻辑处理器  
        acceptor.setHandler(new ServerHandler());  
        // 设置端口号  
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));  
        // 启动监听线程  
        acceptor.bind();  
        
        System.out.println("Server started on port： " + PORT);  

	}
    
    
private static   class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
    	
    	@Override
    	public boolean isRequest(IoSession session, Object message) {
    		System.out.println("isRequest:"+message);
    		// TODO Auto-generated method stub
    		if(message.equals(HEARTBEATREQUEST)){
    			
    			System.out.println("请求心跳包信息: " + message);
    			return true;  
    		}

			return false;
    	}
    	
    	@Override
    	public boolean isResponse(IoSession session, Object message) {
    		// TODO Auto-generated method stub
    		System.out.println("isResponse:"+message);
    		if(message.equals(HEARTBEATRESPONSE)){
    			System.out.println("客户端响应心跳包信息: " + message);
    			return true;  
    		}
    		
    		return false;
    	}
    	
    	@Override
    	public Object getRequest(IoSession session) {
    		// TODO Auto-generated method stub
    		System.out.println("getRequest");
            LOG.info("请求预设信息: " + HEARTBEATREQUEST);  
            System.out.println("请求预设信息: " + HEARTBEATREQUEST);
            return HEARTBEATREQUEST;  
    	}
    	
    	@Override
    	public Object getResponse(IoSession session, Object request) {
    		System.out.println("getResponse");
    	    LOG.info("响应预设信息: " + HEARTBEATRESPONSE);  
            /** 返回预设语句 */  
    	    System.out.println("响应预设信息: " + HEARTBEATRESPONSE);
            return HEARTBEATRESPONSE;
    	}
    	
    }
}


