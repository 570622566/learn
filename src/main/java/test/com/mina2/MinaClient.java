package test.com.mina2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {
	
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";
	private static final int HEART_INTERVAL = 10;
	private static final int HEART_TIMEOUT = 30;
	
	public static void main(String[] args) {
		
		// 创建客户端连接器.
		NioSocketConnector connector = new NioSocketConnector();
		// 设置日志记录器
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		// 设置编码过滤器
		//connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MsgCodecFactory()));
		TextLineCodecFactory  textLineCodecFactory= new TextLineCodecFactory(Charset.forName("GBK"));
		textLineCodecFactory.setDecoderMaxLineLength(1024*10);
		connector.getFilterChain().addLast("codec",  new ProtocolCodecFilter(textLineCodecFactory ));  
		
		 ClientKeepAliveFactoryImpl ckafi = new ClientKeepAliveFactoryImpl(); 
		 KeepAliveFilter kaf = new KeepAliveFilter(ckafi, IdleStatus.READER_IDLE,KeepAliveRequestTimeoutHandler.CLOSE); 
		 //表明如果当前连接的读通道空闲的时候在指定的时间间隔getRequestInterval后发送出心跳请求，以及发出Idle事件。 KeepAliveRequestTimeoutHandler设置为CLOSE表明，当发出的心跳请求在规定时间内没有接受到反馈的时候则调用CLOSE方式 关闭连接 
		 
		//  kaf.setForwardEvent(true);   // 说明：继续调用 IoHandlerAdapter 中的 sessionIdle时间 

		  kaf.setRequestInterval(HEART_INTERVAL); //   说明：设置当连接的读取通道空闲的时候，心跳包请求时间间隔 
		  kaf.setRequestTimeout(HEART_TIMEOUT);  //设置心跳包请求后 等待反馈超时时间  超过该时间后则调用KeepAliveRequestTimeoutHandler.CLOSE 
		  
		//connector.getFilterChain().addLast("heart", kaf);  // 说明： 该过滤器加入到整个通信的过滤链中。 
		// 设置连接超时检查时间
		connector.setConnectTimeoutCheckInterval(30);
		// 设置事件处理器
		connector.setHandler(new ClientHandler());

		// 建立连接
		ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 3001));

		// 等待连接创建完成
		cf.awaitUninterruptibly();
		IoSession session = cf.getSession();
		 for(int i=0;i<3;i++)
		//session.write("020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 370202020202020202, OU=00, OU=00, O=10, L=00, L=02, S=37, C=CN&10.49.138.1750302");
			 // session.write("020337&370302");
		 session.write("020342&ADMIN$>>C:\\windows&&C$>>C:\\&&D$>>D:\\&&H$>>H:\\&&IPC$>>&&&0302");
		session.getCloseFuture().awaitUninterruptibly(); // 等待连接断开
		connector.dispose();
		
		// 发送消息

	}
	
	private static class  ClientKeepAliveFactoryImpl implements KeepAliveMessageFactory {
		
		@Override
		public boolean isRequest(IoSession session, Object message) {
			//  服务器不会给客户端发送请求包，因此不关注请求包，直接返回false 
			return false;
		}
		
		@Override
		public boolean isResponse(IoSession session, Object message) {
			// 客户端关注请求反馈，因此判断mesaage是否是反馈包
			if(message.equals(HEARTBEATRESPONSE)){
				return true;
			}
			return false;
		}
		
		@Override
		public Object getRequest(IoSession session) {
			//  获取心跳请求包
			return HEARTBEATREQUEST;
		}
		
		@Override
		public Object getResponse(IoSession session, Object request) {
			// 服务器不会给客户端发送心跳请求，客户端当然也不用反馈  该方法返回null
			return null;
		}
		
	}
}
