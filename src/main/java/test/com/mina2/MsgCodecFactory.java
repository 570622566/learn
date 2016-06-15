package test.com.mina2;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author Administrator
 *
 *在mina中如果没有ProtocolCodecFilter，发送端的 一个IoSession.write(Object message)操作会触发接收端的多个messageReceived(IoSessionsession, Object message) 事件，多个IoSession.write(Object message)操作也可能会导致只触发了一个messageReceived事件，这不就乱套了嘛。

  很多时候我们需要知道当前message的终止位置和下一个message的起始位置。

  所以,分离基础协议逻辑和业务逻辑。
 *
 *
 */
public class MsgCodecFactory implements ProtocolCodecFactory {
	
	
	//这里注册自己编写的解码工具  
	private final MsgResponseEncoder encoder; // 编码  
	private final MsgRequestDecoder decoder; // 解码  
	
	public MsgCodecFactory() {  
	    encoder = new MsgResponseEncoder();  
	    decoder = new MsgRequestDecoder();  
	}  
	
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	    return encoder;  
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
	    return decoder;  
	}

}
