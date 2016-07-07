package test.com.mina2;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {
	SessionMap map = SessionMap.newInstance();

	private final static Logger log = LoggerFactory.getLogger(ServerHandler.class);

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		if(session ==null){
			return;
		}
		
		map.removeSession(String.valueOf(session.getId()), session);
		
		IoFuture closeFuture = session.closeNow();
		closeFuture.addListener(new IoFutureListener<IoFuture>(){
			@Override
			public void operationComplete(IoFuture future) {
				
				if(future instanceof CloseFuture  ){
					((CloseFuture) future).setClosed();
				}
			}
			
		});
		if(closeFuture.isDone()){
			System.out.println("session"+session.getId()+"已经关闭了");
		}
	}

	/*
	 * 连接创建事件
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// 显示客户端的ip和端口
		System.out.println(session.getRemoteAddress().toString());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("exceptionCaught........."+cause.getMessage());
		if (cause instanceof java.io.IOException) {
			session.closeNow();
		}
	}

	/**
	 * 消息接收事件
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();
		if (str.trim().equalsIgnoreCase("quit")) {
			// 结束会话
			session.closeNow();
			return;
		}
		//map.addSession(String.valueOf(session.getId()), session);
		
		// 返回消息字符串
		/*
		 * session.write("Hi Client!"); session.write("version:1.0.1"); //
		 * 打印客户端传来的消息内容 System.out.println("Message written..." + str); String
		 * ip = session.getRemoteAddress().toString(); log.info(
		 * "===> Message From " + ip + " : " + message); System.out.println(
		 * "===> Message From " + ip + " : " + message);
		 */
		/*****************************************************/

		System.out.println("message:" + message);
		String msss = message.toString();
		if (StringUtils.isNotBlank(message.toString()) && message.toString().startsWith("0203")
				&& message.toString().endsWith("0302")) {
			String msg = message.toString().substring(msss.indexOf("0203") + 4, msss.lastIndexOf("0302"));
			String reqs[] = msg.substring(3).split("&");
			// 370202020202020202, OU=00, OU=00,
			// O=10, L=00, L=02, S=37, C=CN
			String ous[] = reqs[1].split(",");
			String name[] = ous[0].replaceAll("CN=", "").split(" ");
			session.write(reqs[0] + "&" + name[0]);// 登录成功，返回用户名和账号
		}
		
		
		

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("发送的消息:" + message);
		super.messageSent(session, message);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
		Map<Long, IoSession> managedSessions = session.getService().getManagedSessions();
		
		System.out.println("当前的连接数量:" + session.getService().getManagedSessionCount());
		// System.out.println("当前的session:"+acceptor.getManagedSessions());
		for (Entry<Long, IoSession> entry : managedSessions.entrySet()) {
			System.out.println("键:" + entry.getKey() + "值:" + entry.getValue());

		}
		}
	

	public void sendMessage(Object message) {
		map.sendMessage(message);
	}

}
