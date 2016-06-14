package test.com.handle;

import java.nio.charset.Charset;

public class Test7 {
	public static void main(String[] args) {
			
		    int number = 2;
		    System.out.println(number << 5);//2 ^(5 + 1)

		    number = 128;
		    System.out.println(number >> 2 );//128 * 2^1/2 = 2^(7-2) 
		
		    
		    String s = "020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 37020202020";
		    
			System.out.println("length:"+s.getBytes(Charset.forName("GBK")).length);//141

	}
}
