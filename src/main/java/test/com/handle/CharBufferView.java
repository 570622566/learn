package test.com.handle;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class CharBufferView {

	public static void print(Buffer buffer){  
        System.out.println("pos="+buffer.position()+", limit="+   
                buffer.limit() + ", capacity="+buffer.capacity()  
                +":'" + buffer.toString()+"'");  
    }  
    public static void main(String[] args)  
        throws Exception{  
        ByteBuffer byteBuffer = ByteBuffer.allocate(10).order(ByteOrder.BIG_ENDIAN);  
        CharBuffer charBuffer = byteBuffer.asCharBuffer();  
          
        byteBuffer.put(0,(byte)0);  
        byteBuffer.put(1,(byte)'H');  
        byteBuffer.put(2,(byte)0);  
        byteBuffer.put(3,(byte)'e');  
        byteBuffer.put(4,(byte)0);  
        byteBuffer.put(5,(byte)'l');  
        byteBuffer.put(6,(byte)0);  
                  byteBuffer.put(7,(byte)'l');  
                  byteBuffer.put(8,(byte)0);  
                  byteBuffer.put(9,(byte)'o');  
          
        CharBufferView.print(byteBuffer);  
        CharBufferView.print(charBuffer);  
          
    }  

}
