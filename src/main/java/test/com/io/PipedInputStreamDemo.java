package test.com.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedInputStreamDemo {

	public static void main(String[] args) {
		
	      // create a new Piped input and Output Stream
	      PipedOutputStream out = new PipedOutputStream();
	      PipedInputStream in = new PipedInputStream();

	      try {
	    	  //connect input and output
			in.connect(out);
			
	         // write something 
			out.write(70);
			out.write(71);
			
			/*   // print how many bytes are available
	         System.out.println("" + in.available());*/
	         
	         // read what we wrote

	     /*    for(int i=0; i<2 ;i++){
	        	 System.out.println("" +in.read()	);
	         }*/
	         
	       /*  // read what we wrote into an array of bytes
	         byte[] b  = new byte[2];
	         in.read(b, 0, 2);
	         
	         // print the array as a string
	         String s = new String(b);
	         System.out.println("" + s);*/
	         
			// receive a byte 
	         System.out.println("Receiving Byte...");
	        // in.receive(71);

	         
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
