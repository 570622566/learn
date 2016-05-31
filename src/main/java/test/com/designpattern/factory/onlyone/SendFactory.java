package test.com.designpattern.factory.onlyone;

import test.com.designpattern.factory.MailSender;
import test.com.designpattern.factory.Sender;
import test.com.designpattern.factory.SmsSender;

public class SendFactory {
	
	 public Sender produce(String type) {  
	        if ("mail".equals(type)) {  
	            return new MailSender();  
	        } else if ("sms".equals(type)) {  
	            return new SmsSender();  
	        } else {  
	            System.out.println("请输入正确的类型!");  
	            return null;  
	        }  
	    }  
}
