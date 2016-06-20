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
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端主程序
 * 
 * @author Administrator
 *
 */
public class MinaServer {

	private static final int PORT = 3001;// 定义监听端口
	/** 30秒后超时 */
	private static final int IDELTIMEOUT = 60;
	/** 15秒发送一次心跳包 */
	private static final int HEARTBEATRATE = 15;
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";
	private static final Logger LOG = LoggerFactory.getLogger(MinaServer.class);
    private static IoAcceptor acceptor = new NioSocketAcceptor(); // 创建非阻塞的socket传输Acceptor

	
	public static IoAcceptor getInstance(){
		return acceptor;
	}
	
	public static void main(String[] args) throws Exception {
		/**
		 * 
		 * IoAcceptor：显然此方法的名字源于socket的accept方法，它的实现类有：
		 * 
		 * 
		 * NioSocketAcceptor : 非阻塞的socket传输Acceptor 
		 * NioDatagramAcceptor :非阻塞的udp传输Acceptor 
		 * AprSocketAcceptor : 基于apr的阻塞传输Acceptor
		 * VmPipeSocketAcceptor : 虚拟机内的 Acceptor
		 */
	

		// acceptor.getSessionConfig().setReadBufferSize(1024);
		 acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
		 IDELTIMEOUT);//30s后,设定session进入空闲状态
		LoggingFilter lf = new LoggingFilter();
		lf.setSessionOpenedLogLevel(LogLevel.DEBUG);// 配置日志级别

		// 设置日志记录器
		acceptor.getFilterChain().addLast("logger", lf);

		// 设置编码过滤器
		//acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MsgCodecFactory()));
		 acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

		KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
		KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory, IdleStatus.BOTH_IDLE, heartBeatHandler);
		heartBeat.setForwardEvent(true);//idle事件回发  当session进入idle状态的时候 依然调用handler中的idled方法
		// 设置心跳频率
		heartBeat.setRequestInterval(HEARTBEATRATE); // 本服务器为被定型心跳 即需要每15秒接受一个心跳请求  否则该连接进入空闲状态 并且发出idled方法回调  heartPeriod其实就是服务器对于客户端的IDLE监控时间。
		
		heartBeat.setRequestTimeout(20); //超过该时间后则调用KeepAliveRequestTimeoutHandler.CLOSE 
		 acceptor.getFilterChain().addLast("heartbeat", heartBeat);

		// 指定业务逻辑处理器
		ServerHandler serverHandler = new ServerHandler();
		acceptor.setHandler(serverHandler);
		// acceptor.setHandler( new TimeServerHandler() );

		// 设置端口号
		acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
		// 启动监听线程
		acceptor.bind();
		System.out.println("Server started on port： " + PORT);

		Thread.sleep(60000l);
		// serverHandler.sendMessage("High!!");

	}

	private static class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {

		@Override
		public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {
            System.out.println("《*服务器端*》心跳包发送超时处理(及长时间没有发送（接受）心跳包)"); 

			filter.setRequestTimeoutHandler(CLOSE);
		}

	}

	private static class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
		

		/* 返回给客户端的心跳包数据 return 返回结果才是客户端收到的心跳包数据 
		 */
		@Override
		public Object getRequest(IoSession session) { // 1 被动型心跳机制无请求 因此直接返回null
			// TODO Auto-generated method stub
		//	System.out.println("getRequest");
		//	LOG.info("请求预设信息: " + HEARTBEATREQUEST);
			//	System.out.println("请求预设信息: " + HEARTBEATREQUEST);
			
			//return HEARTBEATREQUEST;
			return null;
		}
		

		/* 
		 * 接受到的客户端数据包
		 */
		@Override
		public Object getResponse(IoSession session, Object request) {
			System.out.println("getResponse..."+request);
			
			LOG.info("响应预设信息: " + HEARTBEATRESPONSE);
			/** 返回预设语句 */
			System.out.println("响应预设信息: " + HEARTBEATRESPONSE);
			return HEARTBEATRESPONSE;
		}
		
		
		/**
		 * 判断是否是客户端发送来的的心跳包此判断影响 KeepAliveRequestTimeoutHandler实现类 
         * 判断是否心跳包发送超时 
		 */
		@Override
		public boolean isRequest(IoSession session, Object message) {
			System.out.println("isRequest:" + message);
			if (message.equals(HEARTBEATREQUEST)) {
				System.out.println("接收到客户端心数据包引发心跳事件,心跳数据包是》 " + message);
				return true;
			}
			return false;
		}
		
		
		/**
		 * 判断发送信息是否是心跳数据包此判断影响 KeepAliveRequestTimeoutHandler实现类   判断是否心跳包发送超时
		 */
		@Override
		public boolean isResponse(IoSession session, Object message) {
		//	System.out.println("isResponse:" + message);
			//if (message.equals(HEARTBEATRESPONSE)) {
              //  System.out.println("服务器发送数据包中引发心跳事件: " + message); 
			 // 	return false;
			//}

			return false;
		}



	}
}
