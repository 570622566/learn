package test.com.mina2.image.server;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import test.com.mina2.image.entity.Message;

public class MinaImageHander implements IoHandler {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();
		System.out.println("one client connect:"+session);
		//检测到有连接了，服务器就可以发生图片给客服端了，在什么地方发送根据自己情况而定
		Message mes=new Message();
		FileInputStream fileInputStream = new FileInputStream("d:\\smil\\20130226\\image.jpg");
		FileChannel channel=fileInputStream.getChannel();
		String jsonStr = "{\"ack\":\"true\", \"command\":\"getstandbydata\", \"dcsn\":\"sn-001\", \"id\":\"2001\"}";

		mes.setImagename(jsonStr);
		mes.setImagenamelongth(mes.getImagename().length());
		ByteBuffer bytebuffer=ByteBuffer.allocate((int) channel.size());
		bytebuffer.clear();
		channel.read(bytebuffer);
		mes.setImagelongth(channel.size());
		mes.setImage(bytebuffer.array());
		mes.setAlonght((int) (4+4+8+mes.getImagelongth()+mes.getImagenamelongth()));
		System.out.println("limit:"+bytebuffer.limit());
		System.out.println("mes.getImagelongth():"+mes.getImagelongth());
		IoBuffer io=IoBuffer.allocate((int) (4+4+8+mes.getImagelongth()+mes.getImagenamelongth()));
		io.putInt(mes.getAlonght());
		io.putInt(mes.getImagenamelongth());
		io.putString(mes.getImagename(),encoder );
		io.putLong(mes.getImagelongth());
		io.put(bytebuffer);
		io.put(mes.getImage());
		System.out.println("send remaining:"+io.limit());
		io.flip();
		System.out.println("数据已经读取完毕，准备发送");
		session.write(io);
	//	fileInputStream.close();
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

}
