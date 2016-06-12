package test.com.mina2;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MsgCodecFactory implements ProtocolCodecFactory {
	
	
	//这里注册自己编写的解码工具  
	private final MsgResponseEncoder encoder; // 编码  
	private final MsgRequestDecoder decoder; // 解码  
	
	public MsgCodecFactory(Charset charset) {  
	    encoder = new MsgResponseEncoder(charset);  
	    decoder = new MsgRequestDecoder(charset);  
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
