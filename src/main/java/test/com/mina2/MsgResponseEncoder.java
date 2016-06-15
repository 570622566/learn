package test.com.mina2;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MsgResponseEncoder extends ProtocolEncoderAdapter {
	/**
	 * 
	 * 一般来说我们如果想从一长串字节流中得到我们要的数据并组织成业务上的pojo，我们一般用以下几种方法：

		1、采用固定长度的message  浪费内存空间,不推荐

		2、用固定的头标示body的长度   适合自定义的编解码器做为过滤

		3、用基于文本的标示，如换行、回车等    mina自带的TextLineCodeFiter,已经实现,但是不能存在\r\n的特殊字符
	 * 
	 * 
	 */
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
	    System.out.println(message);
	    IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
	    
	    String strOut = message.toString();
	    buf.putInt(strOut.getBytes(Charset.forName("GBK")).length);//现在开始的四个字节中存放了,当前信息的长度
	    buf.putString(strOut,Charset.forName("GBK").newEncoder());
	    buf.flip();
	    out.write(buf);
	    
	}

}
