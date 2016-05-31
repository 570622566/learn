package test.com.designpattern.create.factory.mulfactory;

import test.com.designpattern.create.factory.MailSender;
import test.com.designpattern.create.factory.Sender;
import test.com.designpattern.create.factory.SmsSender;

public class SendFactory {

	
	  public Sender produceMail(){  
	        return new MailSender();  
	    }  
	      
	    public Sender produceSms(){  
	        return new SmsSender();  
	    }  
}
