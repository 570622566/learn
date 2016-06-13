package test.com.handle;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class Test6 {
	public static void main(String[] args) throws Exception {
	//初始化一个字节数组，内有5个字节的数据
		
		byte[] bytes = {1,2,3,4,5};
		
		//用一个ByteArrayInputStream来读取这个字节数组
		ByteArrayInputStream in= new ByteArrayInputStream(bytes);
		
		//包在BufferedInputStream,并初始化缓冲区大小
		BufferedInputStream bis =new BufferedInputStream(in,3);
		//读取字节1
		System.out.print(bis.read()+",");
		
		System.out.println("mark");
		bis.mark(1);
		
		/**
		 * 连续读取两个字节,readlimit的大小. mark标记依然有效
		 */
		System.out.print(bis.read()+",");
		System.out.println("reset");

		bis.reset();//See the general contract of the reset method of InputStream. 

		System.out.print(bis.read()+",");

		System.out.print(bis.read()+",");
		System.out.print(bis.read()+",");
		System.out.print(bis.read()+",");
		bis.reset();
		//如果readlimit大于BufferedInputStream类缓冲区的大小，缓冲区会被扩大，那mark后最多就可以读readlimit字节
	}
}
