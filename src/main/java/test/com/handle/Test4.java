package test.com.handle;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;

public class Test4 {

	/**
	 * 　　position：相当于一个游标（cursor），记录我们从哪里开始写数据，从哪里开始读数据。
	 */
	public static void main(String[] args) throws Exception {

		String s = "ttt020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 370202020202020202, OU=00, OU=00, O=10, L=00, L=02, S=37, C=CN&10.49.138.1750302"
				;
		System.out.println("length:"+s.getBytes(Charset.forName("GBK")).length);//141
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);//根据limit进行分配,再重新分配的内存后limit将会比capacity小
		buf.getInt();  //类似00 00 00 00 30 32 30 33  composite四个组成的一个数
		System.out.println("buf.remaining():"+buf.remaining());//96字节数  返回limit-position,返回缓冲器中的剩余字节
		System.out.println("buf.position():"+buf.position());//4
        System.out.println("buf.capacity():"+buf.capacity());//100
        System.out.println("buf.limit():"+buf.limit()); //100
        
        
        System.out.println("insert...........................................");
        
		String strOut = s;
		buf.putInt(strOut.getBytes(Charset.forName("GBK")).length);//插入141个字节数字长度,从4开始,后续我们需要拿出来......................
		//  x >> 24|x >> 16|x >> 8|x >> 0   移动了四个字节
		buf.putString(strOut, Charset.forName("GBK").newEncoder());//写内容进buffer特定的编码,特殊的字节
		buf.getInt();//then increments the position by four. 在字节流里读取4个Byte，构成一个Int返回给你咯。从字节流里怎么知道你要的数据类型是什么，只能按照你的意愿去解释字节流，你要int，那就读4个字节解释成int给你，你要char，那就从字节流里读取2个字节解释成char返回给你，你要Byte，你就只读取一个字节返回给你
		//(((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | ((a & 0xff) << 0))
		System.out.println("buf.remaining():"+buf.remaining());//45   返回limit-position
		System.out.println("buf.limit():"+buf.limit()); //198  系统内存自我分配 (在capacity内部进行)
		System.out.println("buf.position():"+buf.position());//153  4+4+141+4
        System.out.println("buf.capacity():"+buf.capacity());//256
        System.out.println("在放入数据..大于limit的长度后..80个字节长度................");
		buf.putString("020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 37020202020           ", Charset.forName("GBK").newEncoder());//写内容进buffer特定的编码,特殊的字节
		System.out.println("buf.limit():"+buf.limit()); //256  系统内存自我分配 (在capacity内部进行)
		System.out.println("buf.position():"+buf.position());//233  4+4+141+4+80
        System.out.println("buf.capacity():"+buf.capacity());//256
		
        
		buf.flip();//读入buffer之后,开始从buffer进行写出操作    position = 0;  limit = oldPosition
		System.out.println("flip---------写进buffer之后--------------------------------------");
		buf.getInt();//跳过四个字节  4
		System.out.println("buf.remaining():"+buf.remaining());//229  包括 插入的int 和 string 223-4  limit-position
		System.out.println("buf.position():"+buf.position());//4 
        System.out.println("buf.capacity():"+buf.capacity());//256 最大存储容量 256
        System.out.println("buf.limit():"+buf.limit()); //233 读入时候的position位置; 表示可写入或可读取数据的限制.  limit表示你最多能读到多少数据
        System.out.println("buf.mark():"+buf.markValue());//初始化的时候所在的值  -1  mark值,(mark必须小于position)
        
        //	buf.mark();          Byffer中，一个不可变的定律是：mark <= position <= limit <= capacity。
        
        System.out.println("get...从buffer拿出来..............................................");
        System.out.println("Returns this buffer's position:"+buf.position());//信息量是从4开始,因为getInt,偏移4个原因

        int length =buf.getInt(buf.position());//must smaller than the buffer's limit
        System.out.println("从以前put过的索引位置为4的下标:length:"+length);//141,4取出来的数值

        buf.getInt();  //The int read 不加会截取信息出错  then increments the position by four.  指针又移动四个位置
        System.out.println("buffer's position:"+buf.position());//4+4=8,从8开始取出string
        System.out.println("buffer's position 4: " +buf.getInt(4)); //第四个索引存放的是整数
        System.out.println("buffer's position 8:  "+(char)buf.get(8));//t
        System.out.println("buffer's position 9:  "+(char)buf.get(9));//t
        System.out.println("buffer's position 10:  "+(char)buf.get(10));//t
        System.out.println("buffer's position 11: "+(char)buf.get(11));//0
        
        byte[] bytes = new byte[length]; 

        buf.get(bytes, 0, length);  //0  The position in the original buffer  length:The number of bytes to copy
        //System.out.println(ioBuffer.position());
        
        
        System.out.println("从buffer拿出来的信息:"+ new String(bytes,"GBK"));
        System.out.println(buf.toString());
        
        if(length> buf.remaining() -4){//如果消息内容不够，则重置，相当于不读取size
            System.out.println("package notEnough:  left="+buf.remaining()+" length="+length);
        }
        
        
	}
}
