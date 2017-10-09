package test.com.handle;

public class Test18 {

	public static void main(String[] args) {
		
		String a = "chenssy";
		String b = "chenssy";
		String c = new String("chenssy");
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(a==b);
		System.out.println("a=c?"+a==c);
		System.out.println(a.equals(b));

	}
	
	
}
