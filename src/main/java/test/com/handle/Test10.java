package test.com.handle;

import java.io.File;
import java.io.FileOutputStream;

public class Test10 {

	public static void main(String[] args) throws Exception {
		
		FileOutputStream  out = new FileOutputStream(new File("d:/a.txt"));
		String str = "1";
		out.write(str.getBytes(), 0, str.length());
			
		out.close();
	}
}
