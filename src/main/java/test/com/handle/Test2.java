package test.com.handle;

public class Test2 {

	public static void main(String[] args) {

		String msg = "020310&101D0F5CA1579A1102DEB75C491B808&张三 身份证号0302";

		msg = msg.substring(msg.indexOf("0203") + 4, msg.lastIndexOf("0302"));

		System.out.println(msg);
		//msg = msg.substring(0, 2);

		String reqs[] = msg.substring(3).split("&");
		String pkiId = reqs[0];
		String theme = reqs[1];
		System.out.println(pkiId);
		System.out.println(theme);
		System.out.println(reqs);

	}
}
