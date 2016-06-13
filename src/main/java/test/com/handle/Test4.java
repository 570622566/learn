package test.com.handle;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;

public class Test4 {

	public static void main(String[] args) throws Exception {

		String s = "ttt020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 370202020202020202, OU=00, OU=00, O=10, L=00, L=02, S=37, C=CN&10.49.138.1750302"
				;
		System.out.println("length:"+s.getBytes(Charset.forName("GBK")).length);//141
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		
		System.out.println("buf.getInt():"+buf.getInt());//0  
		System.out.println("buf.remaining():"+buf.remaining());//96字节数  返回limit-position,返回缓冲器中的剩余字节
		System.out.println("buf.position():"+buf.position());//4
        System.out.println("buf.capacity():"+buf.capacity());//100
        System.out.println("buf.limit():"+buf.limit()); //100
        
        System.out.println("insert...........................................");
		String strOut = s;
		buf.putInt(strOut.getBytes(Charset.forName("GBK")).length);//插入141  表头代表4个字节

		buf.putString(strOut, Charset.forName("GBK").newEncoder());
		
		System.out.println("buf.getInt():"+buf.getInt());//0
		System.out.println("buf.remaining():"+buf.remaining());//45   返回limit-position
		System.out.println("buf.limit():"+buf.limit()); //198
		System.out.println("buf.position():"+buf.position());//153
        System.out.println("buf.capacity():"+buf.capacity());//256
		
		buf.flip();
		System.out.println("flip------------------------------------------------");
		System.out.println("buf.getInt():"+buf.getInt());//138
		System.out.println("buf.remaining():"+buf.remaining());//138
		System.out.println("buf.position():"+buf.position());//4
        System.out.println("buf.capacity():"+buf.capacity());// 最大存储容量 138
        System.out.println("buf.limit()"+buf.limit()); //142  表示可写入或可读取数据的限制.  limit表示你最多能读到多少数据
        System.out.println("buf.mark():"+buf.markValue());//初始化的时候所在的值  -1 
        //	buf.mark();          Byffer中，一个不可变的定律是：mark <= position <= limit <= capacity。
        
        
        int length =buf.getInt(buf.position());//to get The current position in the buffer
        System.out.println("length:"+length);
        System.out.println(buf.toString());
        
        if(length> buf.remaining() -4){//如果消息内容不够，则重置，相当于不读取size
            System.out.println("package notEnough:  left="+buf.remaining()+" length="+length);
            
        }
        
        
	}
}
