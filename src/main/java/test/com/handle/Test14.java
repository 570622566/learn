package test.com.handle;

public class Test14 {
	
	public static void main(String[] args) {
		 String str=null;  
		    //$使用  
		    str=String.format("格式参数$的使用：%2$d,%1$s", 99,123);             
		    System.out.println(str);                       
		    //+使用  
		    System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);  
		    //补O使用  
		    System.out.printf("最牛的编号是：%03d%n", 7);  
		    //空格使用  
		    System.out.printf("Tab键的效果是：% 8d%n", 7);  
		    //.使用  
		    System.out.printf("整数分组的效果是：%,d%n", 9989997);  
		    //空格和小数点后面个数  
		    System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);  
	}
}
