package test.com.designpattern.factory.staticfactory;

import test.com.designpattern.factory.MailSender;
import test.com.designpattern.factory.Sender;
import test.com.designpattern.factory.SmsSender;

public class SendFactory {

	
	public static Sender produceMail(){  
        return new MailSender();  
    }  
      
    public static Sender produceSms(){  
        return new SmsSender();  
    }  
}
