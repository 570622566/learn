package test.com.mina2;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MsgResponseEncoder extends ProtocolEncoderAdapter {

	private final Charset charset;

	public MsgResponseEncoder(Charset charset) {
		// TODO Auto-generated constructor stub
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
	 //   System.out.println(message);
	    IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
	    
	   //String strOut = DateSecret.encryptDES(message.toString());//别看这里的处理，这里是我的数据包加密算法~你可以直接拿message.toString当数据
	    String strOut = message.toString();
	    buf.putInt(strOut.getBytes(Charset.forName("utf-8")).length);
	    buf.putString(strOut,Charset.forName("utf-8").newEncoder());
	    buf.flip();
	    out.write(buf);
	    
	}

}
