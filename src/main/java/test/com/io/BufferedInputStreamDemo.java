package test.com.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author 
 */
public class BufferedInputStreamDemo {

	public static void main(String[] args) throws Exception {
		InputStream inStream = null;
		BufferedInputStream bis = null;
	    boolean bool = false;

		try {
			// open input stream test.txt for reading purpose.
			inStream = new FileInputStream("d:/test.txt");

			// input stream is converted to buffered input stream
			bis = new BufferedInputStream(inStream);

			// read until a single byte is available
	/*		
	 * Administrator The following example shows the usage of
 *         java.io.BufferedInputStream.available() method.
	 * while (bis.available() > 0) {
				// get the number of bytes available
				Integer nBytes = bis.available();
				System.out.println("Avaiable bytes = " + nBytes);

				// read next available character
				char ch = (char) bis.read();

				// print the read character.

				System.out.println("The character read = " + ch);

			}*/
			/*
			 * bis.close(); // releases any system resources associated with the stream
			 * 
			 * 
			 * int byteNum = bis.available(); // throws io exception on  available() invocation
			 *
			 * System.out.println(byteNum);
			 */
			
			
		/*	
		 * mark
		 * // read and print characters one by one
	         System.out.println("Char : "+(char)bis.read());
	         System.out.println("Char : "+(char)bis.read());
	         System.out.println("Char : "+(char)bis.read());
	         // mark is set on the input stream
	         bis.mark(3);
	         System.out.println("Char : "+(char)bis.read());
	         System.out.println("reset() invoked");
	         
	         // reset is called
	         bis.reset();
	         
	         // read and print characters
	         System.out.println("char : "+(char)bis.read());
	         System.out.println("char : "+(char)bis.read());*/
	         
			
	      /* 
	       * markSupportted 用法  
	       * bool = bis.markSupported();
	         System.out.println("Support for mark() and reset() : "+bool);  */
			
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// releases any system resources associated with the stream
			if (null != inStream) {
				inStream.close();
			}
			if (bis != null) {
				bis.close();
			}

		}

	}
}
