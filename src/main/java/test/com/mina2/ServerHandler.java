package test.com.mina2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {
	
	 private final static Logger log = LoggerFactory  
	            .getLogger(ServerHandler.class);
	/* 
	 连接创建事件 
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
        // 显示客户端的ip和端口  
		System.out.println(session.getRemoteAddress().toString());
	}
	
	
	   
	    @Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	    	
	    	if(cause instanceof java.io.IOException){
	    		session.close();
	    	}
	}



		/** 
	     * 消息接收事件 
	     */  
		@Override
	    public void messageReceived(IoSession session, Object message) throws Exception  
	    {  
	        String str = message.toString();  
	        if (str.trim().equalsIgnoreCase("quit"))  
	        {  
	            // 结束会话  
	            session.close(true);  
	            return;  
	        }  
	        // 返回消息字符串  
	        //session.write("Hi Client!");  
	        // 打印客户端传来的消息内容  
	        System.out.println("Message written..." + str);  
	        String ip = session.getRemoteAddress().toString();  
	        log.info("===> Message From " + ip + " : " + message);
	        System.out.println("===> Message From " + ip + " : " + message);
	        
	    } 
	
	
}
