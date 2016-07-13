package test.com.netty.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {
	
	private Selector selector;
	
	private ServerSocketChannel servChannel;
	
	private volatile boolean stop;
	
	public MultiplexerTimeServer(int port) {
		try {
			selector = Selector.open();//Opens a selector. 


			servChannel = ServerSocketChannel.open();//Opens a server-socket channel. 
			//The new channel's socket is initially unbound; 
			//it must be bound to a specific address via one of its socket's bind methods before connections can be accepted. 
				
			servChannel.configureBlocking(false);//设置为异步
			servChannel.socket().bind(new InetSocketAddress(port), 1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);//直接收连接的时间
			System.out.println("The time server is start in port: "+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void stop(){
		this.stop = true;
	}
	
	@Override
	public void run() {
		while(!stop){
			try {
				selector.select(1000);//selector 每隔1s被唤醒一次,selector
				
				Set<SelectionKey> selectedKeys = selector.selectedKeys();//当有就绪状态的channel时,selector将返回就绪状态的channel的selectorKey集合
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try {
						handleInput(key);//对channel集合进行迭代是网络的异步读写操作所在
					} catch (Exception e) {
						e.printStackTrace();
						if(key!=null){
							key.cancel();
							if(key.channel()!=null)
								key.channel().close();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(selector!=null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws Exception {
		// TODO Auto-generated method stub
		if(key.isValid()){
			//处理新接入的请求信息
			if(key.isAcceptable()){//接收新客户端请求消息,根据SelectionKey的操作位进行判断即可获知网络事件的类型,通过
				//相当于完成TCP的三次握手,tcp链路正式建立.
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector,  SelectionKey.OP_READ);//add the new connection to the selector
				
			}
			if(key.isReadable()){
				//读取客户端的消息
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);//先开辟一个1k的缓冲区
				int readBytes = sc.read(readBuffer);//读取请求码流
				if(readBytes>0){
					readBuffer.flip();//读写转换,用于后续对缓冲区的读取操作
					//   limit = position;  position = 0;
			        
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);//将缓冲区可读的字节数组赋值到新创建的缓冲区当中去	
					String body = new String(bytes,"UTF-8");
					System.out.println("the time server receiver order: "+body);
					String currentTime = "QUERY TIME ORDER"
							.equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
							
					doWrite(sc,currentTime);
				}else if(readBytes <0 ){
					key.cancel();
					sc.close();
				}else{
					//读到0字节,忽略
				}
				
			}
		}
	}

	private void doWrite(SocketChannel channel, String response) throws Exception {//将消息发送给客户端
		if(response!=null && response.trim().length()>0){
			byte[] bytes = response.getBytes();//将字符串变成字节数字
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);//根据字节数组的容量创建ByteBuffer
			writeBuffer.put(bytes);//将字节数组复制到缓冲区中
			writeBuffer.flip();
			channel.write(writeBuffer);//注意,因为是异步的操作他并不保证一次能把需要发送的字节数发送完成,也就是我们常说的写半包的问题
		}
		
	}

}
