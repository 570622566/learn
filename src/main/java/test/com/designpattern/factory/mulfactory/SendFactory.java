package test.com.designpattern.factory.mulfactory;

import test.com.designpattern.factory.MailSender;
import test.com.designpattern.factory.Sender;
import test.com.designpattern.factory.SmsSender;

public class SendFactory {

	
	  public Sender produceMail(){  
	        return new MailSender();  
	    }  
	      
	    public Sender produceSms(){  
	        return new SmsSender();  
	    }  
}
