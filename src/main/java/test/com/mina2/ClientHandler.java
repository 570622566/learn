package test.com.mina2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 *  客户端逻辑控制类 
 * @author Administrator
 *
 */
public class ClientHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("server message response:" + message.toString());// 显示接收到的消息  
	}

	
	
}
