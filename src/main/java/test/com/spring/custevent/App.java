package test.com.spring.custevent;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public static void main( String[] args ){
   
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        CustomEventPublisher publisher = (CustomEventPublisher)context.getBean("customEventPublisher");
        publisher.publish();
    //    publisher.publish();
    }
}
