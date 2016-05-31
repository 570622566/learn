package test.com.designpattern.factory;

/**
 * @author Administrator
 *一个工厂设计模式
 */
public class FactoryTest {

	
	public static void main(String[] args) {
		
		SendFactory sender = new SendFactory();
		Sender send = sender.produce("sms");
		send.Send();
	}
}
