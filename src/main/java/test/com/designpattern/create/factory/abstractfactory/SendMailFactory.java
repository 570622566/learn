package test.com.designpattern.create.factory.abstractfactory;

import test.com.designpattern.create.factory.MailSender;
import test.com.designpattern.create.factory.Sender;

public class SendMailFactory implements Provider {

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
        return new MailSender();
	}

}
