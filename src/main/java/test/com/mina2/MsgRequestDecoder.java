package test.com.mina2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MsgRequestDecoder extends CumulativeProtocolDecoder  {
	
	/* 
	 *  1、当内容刚好时，返回false，告知父类接收下一批内容
	 *  2、内容不够时(缓存里的)需要下一批发过来的内容，此时返回false，这样父类 CumulativeProtocolDecoder 会将内容放进IoSession中，等下次来数据后就自动拼装再交给本类的doDecode
	 *  3. 当内容多时，返回true，因为需要再将本批数据进行读取，父类会将剩余的数据再次推送本
	 * 
	 * 
	 * 
	 */
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if(in.remaining()<4){ //The remaining bytes in the buffer
			return false;  //是用来当拆包时候剩余长度小于4的时候的保护
		}
		if(in.remaining()>1){
            in.mark();//标记当前位置，以便reset   　　mark就像书签一样，在这个IoBuffer里作个标记，以后再调用reset时就可以再回到这个mark过的地方。 也就是mark与reset是配对使用的！
            
            int length =in.getInt(in.position());//获取此条信息的长度             数据包的定义有很多种方式，这里说下我所用过的两种方式：
          //  1.固定消息长度，消息头+消息体+校验码。此方式相对简单，表示的内容也比较少  2 不定消息长度，消息头+消息长度+消息体。此方式可以无限消息长度，比较灵活
            
            if(length > in.remaining() -4){ //长度大于buffer剩余空间   如果消息内容不够，则重置，相当于不读取size  == 相当于判断是否(断包)
            		
                System.out.println("package notEnough,消息长度大于buffer剩余的空间  remaing="+in.remaining()+" length="+length);
                in.reset();  //将此缓冲区的位置重置为以前标记的位置。此缓冲区 　　调用此方法不更改也不丢弃标记的值。
                // int position = mark;
                System.out.println("将之前的position复位至mark表示是的值,mark值:"+in.markValue()+",复位后:"+in.position());
                System.out.println("接收新数据，以拼凑成完整数据   ");
                return false;//接收新数据，以拼凑成完整数据   

            }else{
                System.out.println("package ="+in.toString()); 
                in.getInt();  //The int read 4个
                byte[] bytes = new byte[length]; 
                in.get(bytes, 0, length);   //the modified IoBuffer
                String str = new String(bytes,"GBK");    
                if(null != str && str.length() > 0){
                    String strOut = str;
                    out.write(strOut);    //消息写到队列里
                }
                
                if(in.remaining()>0){//如果读取内容后还粘了包，就让父类再给一次，进行下一次解析 
                	 return true; //要再将本批数据进行读取，父类会将剩余的数据再次推送本类的doDecode
                }
              
            }
            
		}
	    
	    return false;  ////处理成功，让父类进行接收下个包 
	}

}
