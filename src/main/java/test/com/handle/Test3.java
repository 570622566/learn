package test.com.handle;

public class Test3 {

	public static String getMessageBody(String msg) {
		return msg = msg.substring(msg.indexOf("0203") + 4, msg.lastIndexOf("0302"));
	}

	public static void main(String[] args) {

		System.out.println(getMessageBody("020320&USBWhiteList0302"));

	}
}
