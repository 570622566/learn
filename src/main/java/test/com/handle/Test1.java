package test.com.handle;

import com.alibaba.fastjson.JSON;

public class Test1 {
	
	public static void main(String[] args) {
		String msg = "020306&192.168.3.66&192.168.3.128&192.168.3.166&192.168.3.222&0302";
		//msg.substring(0, 2).equals("06")&&msg.indexOf("&")!=-1
		
		  msg = msg.substring(msg.indexOf("0203")+4, msg.lastIndexOf("0302"));
		  //System.out.println(msg);

		String substring = msg.substring(0, 2);
		System.out.println(substring);
		
		int indexOf = msg.indexOf("&");
		System.out.println(indexOf);
		System.out.println(msg);
		String vid= msg.substring(3).split("&")[0];
		System.out.println("vid:"+vid);
		/*for (String s : vid) {
			System.out.println(s);
		}*/
		
        String jsonText = JSON.toJSONString(vid, true);  

		System.out.println(jsonText);
	}
}
