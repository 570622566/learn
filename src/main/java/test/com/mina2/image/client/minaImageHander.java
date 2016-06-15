package test.com.mina2.image.client;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import test.com.mina2.image.entity.Message;

public class minaImageHander implements IoHandler {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("和服务器的连接已经建立");

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
		System.out.println("获得服务器传过来的数据");
		Message mes= (Message) message;
		System.out.println("总长度："+mes.getAlonght());
		System.out.println("imagename:"+mes.getImagename());
		System.out.println("图片长度："+mes.getImagelongth());
		FileOutputStream out=new FileOutputStream("d:\\smil\\image.jpg");
		FileChannel fc=out.getChannel();
		out.write(mes.getImage());
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
