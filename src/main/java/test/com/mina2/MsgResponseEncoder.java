package test.com.mina2;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MsgResponseEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
	 //   System.out.println(message);
	    IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
	    
	    String strOut = message.toString();
	    buf.putInt(strOut.getBytes(Charset.forName("GBK")).length);
	    buf.putString(strOut,Charset.forName("GBK").newEncoder());
	    buf.flip();
	    out.write(buf);
	    
	}

}
