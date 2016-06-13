package test.com.mina2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import net.sf.json.JSONObject;


public class TimeServerHandler extends IoHandlerAdapter {
	 @Override
	    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
	    {
	        cause.printStackTrace();
	    }

	    @Override
	    public void messageReceived( IoSession session, Object message ) throws Exception
	    {
	    	JSONObject json = JSONObject.fromObject(message.toString());
	        String str = message.toString();
	        if( str.trim().equalsIgnoreCase("quit") ) {
	            session.close();
	            return;
	        }
	        session.write("hi friends,how are you in python's world? your message is:"+json.optString("content"));
	        System.out.println(str);
	    }

	    @Override
	    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
	    {
	        System.out.println( "IDLE " + session.getIdleCount( status ));
	    }
}
