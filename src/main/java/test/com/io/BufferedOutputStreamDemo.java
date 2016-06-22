package test.com.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class BufferedOutputStreamDemo {
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream is = null;
		BufferedInputStream bis = null;
		
		ByteArrayOutputStream baos = null;
		BufferedOutputStream bos = null;
		
		
		try {
			 // open input stream test.txt for reading purpose.
			is = new FileInputStream("d:/test.txt");
			
	         // input stream is converted to buffered input stream
	         bis = new BufferedInputStream(is);
	         
	         // creates a new byte array output stream
	         baos = new ByteArrayOutputStream();
	         
	         // creates a new buffered output stream to write 'baos'
	         bos = new BufferedOutputStream(baos);
	         
	         int value;
	         
	         // the file is read to the end
	         while ((value = bis.read()) != -1) {
	            bos.write(value);
	         }
	         
	         // invokes flush to force bytes to be written out to baos
	         bos.flush();

	      // every byte read from baos
	         for (byte b: baos.toByteArray())
	         {
	            
	            // converts byte to character
	            char c = (char)b;
	            System.out.print(c);
	         }
	         
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			 // releases any system resources associated with the stream
	         if(is!=null)
	            is.close();
	         if(bis!=null)
	            bis.close();
	         if(baos!=null)
	            baos.close();
	         if(bos!=null)
	            bos.close();
			
		}
		
		
		
		
		
		
	}
}
