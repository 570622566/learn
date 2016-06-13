package test.com.mina2;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
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
	
    private static final int PORT = 3001;// 定义监听端口  
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
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MsgCodecFactory()));  
        
        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();  
        KeepAliveRequestTimeoutHandler heartBeatHandler = new     KeepAliveRequestTimeoutHandlerImpl();

        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,  
                IdleStatus.BOTH_IDLE,heartBeatHandler);  
        //设置是否forward到下一个filter  
        heartBeat.setForwardEvent(true);  
        //设置心跳频率  
        heartBeat.setRequestInterval(HEARTBEATRATE);  // 本服务器为被定型心跳  即需要每10秒接受一个心跳请求  否则该连接进入空闲状态 并且发出idled方法回调
        heartBeat.setRequestTimeout(120);
        acceptor.getFilterChain().addLast("heartbeat", heartBeat);  

        // 指定业务逻辑处理器  
        ServerHandler serverHandler = new ServerHandler();
     
        acceptor.setHandler(serverHandler); 
        
        // 设置端口号  
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));  
        // 启动监听线程  
        acceptor.bind();  
        System.out.println("Server started on port： " + PORT);  
        
        Thread.sleep(60000l);
        
        serverHandler.sendMessage("hiiiiiiiiiiiiiiiiiiiiiiiiii!!");

	}
    
    private static class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler{

		@Override
		public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
			filter.setRequestTimeoutHandler(DEAF_SPEAKER);
		}
    	
    }
    
private static   class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
    	
    	@Override
    	public boolean isRequest(IoSession session, Object message) {//2  判断是否心跳请求包  是的话返回true 
    		System.out.println("isRequest:"+message);
    		if(message.equals(HEARTBEATREQUEST)){
    			System.out.println("请求心跳包信息: " + message);
    			return true;  
    		}
			return false;
    	}
    	
    	@Override
    	public boolean isResponse(IoSession session, Object message) {//3  由于被动型心跳机制，没有请求当然也就不关注反馈 因此直接返回false
    		// TODO Auto-generated method stub
    		System.out.println("isResponse:"+message);
    		if(message.equals(HEARTBEATRESPONSE)){
    			System.out.println("客户端响应心跳包信息: " + message);
    			return true;  
    		}
    		
    		return false;
    	}
    	
    	@Override
    	public Object getRequest(IoSession session) { //1  被动型心跳机制无请求  因此直接返回null
    		// TODO Auto-generated method stub
    		System.out.println("getRequest");
            LOG.info("请求预设信息: " + HEARTBEATREQUEST);  
            System.out.println("请求预设信息: " + HEARTBEATREQUEST);
            return HEARTBEATREQUEST;  
    	}
    	
    	@Override
    	public Object getResponse(IoSession session, Object request) {//4   根据心跳请求request 反回一个心跳反馈消息 non-nul 
    		System.out.println("getResponse");
    	    LOG.info("响应预设信息: " + HEARTBEATRESPONSE);  
            /** 返回预设语句 */  
    	    System.out.println("响应预设信息: " + HEARTBEATRESPONSE);
    	    //return HEARTBEATRESPONSE;
             return null;
    	}
    	
    }
}


