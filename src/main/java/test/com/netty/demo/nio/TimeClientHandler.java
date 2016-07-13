package test.com.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable {
	
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	public TimeClientHandler(String host, int port) {
		this.host = host == null ? "127.0.0.1" : host;
		this.port = port;
		
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		
		
	}

	@Override
	public void run() {
		try {
			doConnect();  //发送链接请求,连城是成功的,所以不重连操作,
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
	
		}
		
		
		while(!stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if(key!=null){
							key.cancel();
							if(key.channel()!=null){
								key.channel().close();
							}
						}
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		if(selector!=null){
			try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		// TODO Auto-generated method stub
		if(key.isValid()){
			//判断是否连接成功
			SocketChannel sc = (SocketChannel) key.channel();
			
			if(key.isConnectable()){ //Tests whether this key's channel has either finished, or failed to finish, its socket-connection operation. 
				//客户端已经返回给ack应答消息
				if(sc.finishConnect()){ //Finishes the process of connecting a socket channel. 
					//诺为true,则说明客户端连接成功
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				}else//诺为false,则直接抛出IOException
					System.exit(1);
			}
			
			if(key.isReadable()){//可读的
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readbytes = sc.read(readBuffer);//由于是异步的,必须对异步结果进行调用
				if(readbytes>0){
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("Now is : "+body);
					this.stop = true;
						
				}else if(readbytes<0){
					key.cancel();
					sc.close();
				}else{
					//读到字节,忽略
				}
				
			}
		}
	}

	private void doWrite(SocketChannel sc) throws IOException {
		// TODO Auto-generated method stub
		byte[] req = "QUERY TIME ORDER".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		sc.write(writeBuffer);
		if(!writeBuffer.hasRemaining()){//判断缓冲区中的消息全部发送完成.
			System.out.println("Send order 2 server succeed.");
		}
		
	}

	private void doConnect() throws IOException {
		if(socketChannel.connect(new InetSocketAddress(host,port))){
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite(socketChannel);
		}else{//如果没有连接成功,则将SocketChannel注册到多路复用器上,注册为SelectionKey.OP_CONNECT   当服务器返回syn-ack消息后,Selector就能轮询到这个SocketChanle处于连接就绪状态.
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

}
