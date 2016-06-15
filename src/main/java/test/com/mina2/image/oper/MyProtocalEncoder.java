package test.com.mina2.image.oper;

import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MyProtocalEncoder extends ProtocolEncoderAdapter {
	private final String charset;
	public MyProtocalEncoder(String charset) {
		this.charset = charset;
	}

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		
		ByteBuffer b = null;
		
		if (b != null) {
			b.flip();
			IoBuffer ioBuf = IoBuffer.wrap(b);
			out.write(ioBuf);
		}

	}

	public void dispose() throws Exception {
	}
}
