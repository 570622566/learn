package test.com.mina2;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MsgRequestDecoder extends CumulativeProtocolDecoder  {
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if(in.remaining()<4){ //The remaining bytes in the buffer
			return false;  //是用来当拆包时候剩余长度小于4的时候的保护
		}
		if(in.remaining()>1){
            in.mark();//标记当前位置，以便reset   　　mark就像书签一样，在这个IoBuffer里作个标记，以后再调用reset时就可以再回到这个mark过的地方。 也就是mark与reset是配对使用的！
            
            int length =in.getInt(in.position());//to get The current position in the buffer
            
            if(length > in.remaining() -4){ //如果消息内容不够，则重置，相当于不读取size  == 相当于判断是否最后一个字节段
            		
                System.out.println("package notenough  left="+in.remaining()+" length="+length);
                in.reset();  //将此缓冲区的位置重置为以前标记的位置。此缓冲区 　　调用此方法不更改也不丢弃标记的值。

                return false;//接收新数据，以拼凑成完整数据   

            }else{
                System.out.println("package ="+in.toString()); 
                in.getInt();  //The int read
                byte[] bytes = new byte[length]; 
                in.get(bytes, 0, length);   //the modified IoBuffer
                String str = new String(bytes,"GBK");    
                if(null != str && str.length() > 0){
                    String strOut = str;
                    out.write(strOut);    
                }
                
                if(in.remaining()>0){//如果读取内容后还粘了包，就让父类再给一次，进行下一次解析 
                	 return true;
                }
                return true;
            }
            
		}
	    
	    return false;  
	}

}
