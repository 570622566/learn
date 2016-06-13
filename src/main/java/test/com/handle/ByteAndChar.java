package test.com.handle;

public class ByteAndChar {
	public static void main(String[] args) {
		char c = (char) -3; // char不能识别负数，必须强制转换否则报错，即使强制转换之后，也无法识别  
		System.out.println(c);
		
		
		//char 是字符数据类型 ，是无符号型的，占2字节(Unicode码 ）；
		//Char是无符号型的，可以表示一个整数，不能表示负数；而byte是有符号型的，可以表示-128—127 的数；
		byte d1 = 1;
		byte d2 = -1;  
		byte d3 = 127; // 如果是byte d3 = 128;会报错  
		byte d4 = -128; // 如果是byte d4 = -129;会报错  
		System.out.println(d1);  

		System.out.println(d2);  
		System.out.println(d3);  
		System.out.println(d4);  
		
		//2、char可以表中文字符，byte不可以，如：

		char e1 = '中', e2 = '国'; 
		byte f= (byte) 'A'; //必须强制转换否则报错  

		System.out.println(e1);  
		System.out.println(e2);  
		System.out.println(f);  

		//3、char、byte、int对于英文字符，可以相互转化，如：

		byte g = 'b';   //b对应ASCII是98  
		System.out.println(g);  
		char h = (char) g;  
		System.out.println(h);  
		char i = 85;    //U对应ASCII是85  
		System.out.println(i); 
		System.out.println((byte)'U');
		int j = 'h'; //h对应ASCII是104  
		System.out.println(j);  

	}
}
