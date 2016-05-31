package test.com.designpattern.create.factory.staticfactory;

import test.com.designpattern.create.factory.MailSender;
import test.com.designpattern.create.factory.Sender;
import test.com.designpattern.create.factory.SmsSender;

public class SendFactory {

	
	public static Sender produceMail(){  
        return new MailSender();  
    }  
      
    public static Sender produceSms(){  
        return new SmsSender();  
    }  
}
