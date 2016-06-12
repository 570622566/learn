package test.com.mina2;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MsgRequestDecoder extends CumulativeProtocolDecoder  {
	
	private final Charset charset;

	public MsgRequestDecoder(Charset  charset) {
		// TODO Auto-generated constructor stub
		this.charset = charset;
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		CharsetDecoder cd = charset.newDecoder();
		if(in.remaining()<4){
			return false;  //是用来当拆包时候剩余长度小于4的时候的保护
		}
		if(in.remaining()>1){
            in.mark();//标记当前位置，以便reset 
            int length =in.getInt(in.position());
            
            if(length > in.remaining() -4){ //如果消息内容不够，则重置，相当于不读取size 
            		
               // System.out.println("package notenough  left="+in.remaining()+" length="+length);
                in.reset();   
                return false;//接收新数据，以拼凑成完整数据   

            }else{
               // System.out.println("package ="+in.toString()); 
                in.getInt();
                byte[] bytes = new byte[length]; 
                in.get(bytes, 0, length);   
                String str = new String(bytes,"UTF-8");    
                if(null != str && str.length() > 0){
                    String strOut = str;
                    out.write(strOut);    
                }
                
                if(in.remaining()>0){//如果读取内容后还粘了包，就让父类再给一次，进行下一次解析 
                	
                }
                return true;//这里有两种情况1：没数据了，那么就结束当前调用，有数据就再次调用
            }
            
		}
	    
	    return false;  
	}

	

}
