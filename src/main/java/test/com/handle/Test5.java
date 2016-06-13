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
		
		int bytesRead = inChannel.read(buf); //write into buffer.
		while(bytesRead!=-1){
			  buf.flip();  //make buffer ready for read
			  
			  while(buf.hasRemaining()){//Tells whether there are any elements between the current position and the limit.
			      System.out.print((char) buf.get()); // read 1 byte at a time
			  }
			  buf.clear(); //make buffer ready for writing
			  bytesRead = inChannel.read(buf);

		}
		aFile.close();
	
	}

}
