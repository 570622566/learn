package test.com.handle;

public class HashCode {
public static void main(String[] args) {
	
	
	//下面程序中s1和s2是两个不同对象

	String s1 = new String("Hello");

	String s2 =new String("Hello");

	//String类重写了Object类的hashCode方法——改为根据字符序列计算hashCode值，

	//因为s1和s2的字符序列相同，所以它们的hashCode方法返回值相同

	System.out.println(s1.hashCode() +"----" + s2.hashCode());


	//s1和s2是不同的字符串对象，所以它们的identityHashCode值不同，

	//identityHashCode是根据对象的地址计算得到的，所以任何两个不同的对象的

	//identityHashCode值总是不相等

	System.out.println(System.identityHashCode(s1)+ "----"+System.identityHashCode(s2));


	//s3和s4是相同的字符串对象，所以它们的identityHashCode值相同

	String s3 ="Java";

	String s4 ="Java";

	System.out.println(System.identityHashCode(s3)+"----"+System.identityHashCode(s4));
	
	}
	
}
