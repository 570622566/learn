package test.com.handle;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test5 {
	public static void main(String[] args) throws Exception {
		
		RandomAccessFile aFile = new RandomAccessFile("D:/tmp/logback/basic-2016-03-13.0.log", "rw");
		FileChannel inChannel = aFile.getChannel();
		
		//create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(2048);
		
		int bytesRead = inChannel.read(buf); //read into buffer.
		while(bytesRead!=-1){
			  buf.flip();  //make buffer ready for read
			  
			  while(buf.hasRemaining()){//Tells whether there are any elements between the current position and the limit.
			      System.out.print((char) buf.get()); // read 1 byte at a time
			  }
			  buf.clear(); //make buffer ready for writing
			  bytesRead = inChannel.read(buf);
			  
			//  inChannel.write(buf);			//从Buffer读取数据到Channel的例子：   read from buffer into channel.
			 //使用get()方法从Buffer中读取数据的例子
			  /**
			   * get方法有很多版本，允许你以不同的方式从Buffer中读取数据。例如，从指定position读取，或者从Buffer中读取数据到字节数组。更多Buffer实现的细节参考JavaDoc。
			   */
			  


		}
		aFile.close();
	
	}

}
