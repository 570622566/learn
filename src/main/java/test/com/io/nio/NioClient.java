package test.com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
	
	private Selector selector;
	
	public void initClient(String ip,int port) throws IOException{
		
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		this.selector = Selector.open();
		channel.connect(new InetSocketAddress(ip, port));
		channel.register(selector, SelectionKey.OP_CONNECT);
		
	}
	
	
	public void listen() throws Exception{
		for(;;){
		    // 选择一组可以进行I/O操作的事件，放在selector中,客户端的该方法不会阻塞，
		      // 这里和服务端的方法不一样，查看api注释可以知道，当至少一个通道被选中时，
		      // selector的wakeup方法被调用，方法返回，而对于客户端来说，通道一直是被选中的
			selector.select(); 
			Iterator  ite = this.selector.selectedKeys().iterator();
			while(ite.hasNext()){
				SelectionKey key = (SelectionKey) ite.next();
				ite.remove();
				if(key.isConnectable()){
					SocketChannel channel = (SocketChannel) key.channel();
					if(channel.isConnectionPending()){
						channel.finishConnect();
					}
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("hello server").getBytes()));
			        channel.register(this.selector, SelectionKey.OP_READ); // 获得了可读的事件

				}else if(key.isReadable()){
					read(key);
				}
			}
			
			
			
		}
	}


	private void read(SelectionKey key) throws Exception {
		// TODO Auto-generated method stub
	    SocketChannel channel = (SocketChannel) key.channel();
	    ByteBuffer buffer = ByteBuffer.allocate(12);
	    channel.read(buffer);
	    byte[] data = buffer.array();
	    String msg = new String(data).trim();
	    System.out.println("client receive msg from server:" + msg);
	    ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
	    channel.write(outBuffer);
	}
	
	public static void main(String[] args) throws Exception {
	    NioClient client = new NioClient();
	    client.initClient("localhost", 8989);
	    client.listen();
	  }
	
}
