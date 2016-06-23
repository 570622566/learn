package test.com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
	
	//通道管理器
	private Selector selector;
	private String msg = "hello client";	

	public void initService(int port) throws Exception{
		
		//获取serverSocket通道
		ServerSocketChannel serverChanel = ServerSocketChannel.open();
		serverChanel.configureBlocking(false);
		
		serverChanel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();  

        // 将通道管理器和该通道绑定，并为该通道注册selectionKey.OP_ACCEPT事件  
        //  !!!注册该事件后，当事件到达的时候，selector.select()会返回，  
        // !!!如果事件没有到达selector.select()会一直阻塞  
			
		serverChanel.register(selector, SelectionKey.OP_ACCEPT);
		
	}
	
    // 采用轮训的方式监听selector上是否有需要处理的事件，如果有，进行处理  
	public void listen() throws Exception{
		
		System.out.println("start server");
		for(;;){
			
            // 当注册事件到达时，方法返回，否则该方法会一直阻塞  
			selector.select();
			Iterator keyIterator = this.selector.selectedKeys().iterator();
			while(keyIterator.hasNext()){
				SelectionKey key = (SelectionKey) keyIterator.next();
				//  删除已选的key 以防重负处理  
			
				/*if(key.isAcceptable()){//  客户端请求连接事件  
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();  
                    // 获得和客户端连接的通道  
                    SocketChannel channel = server.accept();  
                    // 设置成非阻塞  
                    channel.configureBlocking(false);  
                    // 在这里可以发送消息给客户端  
                    channel.write(ByteBuffer.wrap(msg.getBytes()));  
                    // 在客户端 连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限  	
                    channel.register(this.selector, SelectionKey.OP_READ);  

				}else if(key.isReadable()){
                    read(key);  
				}else if(key.isWritable()){
					writeToClient(key);
				}*/
				if(key.isAcceptable()){
					 ServerSocketChannel server = (ServerSocketChannel) key.channel();  
	                    // 获得和客户端连接的通道  
	                    SocketChannel channel = server.accept();  
	                    // 设置成非阻塞  
	                    channel.configureBlocking(false);  
	                    // 在这里可以发送消息给客户端  
	                    channel.write(ByteBuffer.wrap(msg.getBytes()));  
	                    channel.register(this.selector, SelectionKey.OP_READ);  
				}else if(key.isConnectable()){
					
				}else if(key.isReadable()){
					 readFromClient(key); 
				}else if(key.isWritable()){
					writeToClient(key);//写 读
				}
				
				keyIterator.remove();
			}
			
		}
		
		
	}

	private void writeToClient(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();  
		 ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
	     channel.write(outBuffer); 
         channel.register(this.selector, SelectionKey.OP_READ);  

	}

	// 处理 读取客户端发来的信息事件  

	private void readFromClient(SelectionKey key) throws Exception {
        // 服务器可读消息，得到事件发生的socket通道  
        SocketChannel channel = (SocketChannel) key.channel();  
        ByteBuffer buffer = ByteBuffer.allocate(12);  
        channel.read(buffer);  
        byte[] data = buffer.array();  
        String msg = new String(data).trim();  
        System.out.println("server receive from client: " + msg);  
        channel.register(this.selector, SelectionKey.OP_READ);  

	}
	
	
	 public static void main(String[] args) throws Throwable {  
		 NioServer server = new NioServer();  
	        server.initService(8989);  
	        server.listen();  
	    }  
	
}
