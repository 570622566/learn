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
			selector = Selector.open();
			servChannel = ServerSocketChannel.open();
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
			if(key.isAcceptable()){
				
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector,  SelectionKey.OP_READ);
				
			}
			if(key.isReadable()){
				//读取数据
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if(readBytes>0){
					readBuffer.flip();
					//   limit = position;  position = 0;
			        
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
					System.out.println("the time server receiver order: "+body);
					String currentTime = "Query time order"
							.equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "Bad Order";
							
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

	private void doWrite(SocketChannel channel, String response) throws Exception {
		if(response!=null && response.trim().length()>0){
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
			
			
		}
		
	}

}
