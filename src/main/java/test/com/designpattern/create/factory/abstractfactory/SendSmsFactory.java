package test.com.designpattern.create.factory.abstractfactory;

import test.com.designpattern.create.factory.Sender;
import test.com.designpattern.create.factory.SmsSender;

public class SendSmsFactory implements Provider {

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		   return new SmsSender();
	}

}
