package test.com.handle;

import org.apache.mina.core.buffer.IoBuffer;

public class TestMinaIoBuffer {
	public static void main(String[] args) {
		
		
		
		IoBuffer buffer = IoBuffer.allocate(16);
		
		buffer.setAutoExpand(true);//设置为自动扩展
		
		buffer.put((byte)1);
		
        System.out.println("Initial Buffer capacity = "+buffer.capacity());  
        
        buffer.shrink();  
        
        System.out.println("Initial Buffer capacity after shrink = "+buffer.capacity());  
        
        buffer.capacity(32);  
        
        System.out.println("Buffer capacity after incrementing capacity to 32 = "+buffer.capacity());  
        
        buffer.shrink();  //虽然容量是32,但是却只使用了16
        
        System.out.println("Buffer capacity after shrink= "+buffer.capacity());  

	}
	/**
	 * 
	 * 因为我们定义了最小的长度是16，所以不满16的时候调用shrink方法不会去收缩的，当设置为大小为32的时候却只用了一半的空间来存储，调用shrink后剩下的不用的空间就会被释放，大小也会减半，这样有利于资源的充分利用。

	 * 
	 */
}
