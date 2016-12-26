package test.com.handle;

import java.util.Calendar;

public class Test15 {
	
	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -5);
		System.out.println(c.getTime());
		
	}
}
