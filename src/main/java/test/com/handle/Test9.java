package test.com.handle;

public class Test9 {
	public static void main(String[] args) {
		
		
		  System.out.println(0xffffffff);//-1   byte b=-1;
		  //0xffffffff 16进制 ------------>  11111111111111111111111111111111  2进制
		  //“2的补码”编码规则:负数的话,除最高位外全部取反,然后在加1 就等于0xfffffffff
		  //11111111111111111111111111111110  再取反10000000000000000000000001 即为-1 
		 
		 /**
		  * 
		  *  符号扩展（Sign Extension）用于在数值类型转换时扩展二进制位的长度，以保证转换后的数值和原数值的符号（正或负）和大小相同，一般用于较窄的类型（如byte）向较宽的类型（如int）转换。扩展二进制位长度指的是，在原数值的二进制位左边补齐若干个符号位（0表示正，1表示负）。

    		举例来说，如果用6个bit表示十进制数10，二进制码为"00 1010"，如果将它进行符号扩展为16bits长度，结果是"0000 0000 0000 1010"，即在左边补上10个0（因为10是正数，符号为0），符号扩展前后数值的大小和符号都保持不变；如果用10bits表示十进制数-15，使用“2的补码”编码后，二进制码为"11 1111 0001"，如果将它进行符号扩展为16bits，结果是"1111 1111 1111 0001",即在左边补上6个1（因为-15是负数，符号为1），符号扩展前后数值的大小和符号都保持不变。
		  */
		  
		  byte b=-1;
		  System.out.println((int)(char)b);//65535
		  System.out.println((int)(char)(b & 0xff));//255
		  
		    System.out.println((int)(char)(byte)-1);//65535
		     /**
			   * -1是int型的字面量，根据“2的补码”编码规则，编码结果为0xffffffff，即32位全部置1.转换成byte类型时，直接截取最后8位，所以byte结果为0xff，对应的十进制值是-1.
			   * 
			   * 2. byte(8位) -> char(16位)  
				f -> 1111
  				      由于byte是有符号类型，所以在转换成char型（16位）时需要进行符号扩展，即在0xff左边连续补上8个1（1是0xff的符号位），结果是0xffff。由于char是无符号类型，所以0xffff表示的十进制数是65535。
			   * 
			   * 3.char(16位) -> int(32位)

  				由于char是无符号类型，转换成int型时进行零扩展，即在0xffff左边连续补上16个0，结果是0x0000ffff,对应的十进制数是65535
			   * 
			   */
		    //  在将一个char型数值c转型为一个宽度更宽的类型时，并且不希望有符号扩展，可以如下编码：
		    

	}
}
