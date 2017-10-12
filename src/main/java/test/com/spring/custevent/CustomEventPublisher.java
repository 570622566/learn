package test.com.spring.custevent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 
 * 一旦定义事件类，你可以从任何类中发布它，假定EventClassPublisher实现了ApplicationEventPublisherAware。你还需要在XML配置文件中声明这个类作为一个bean，之所以容器可以识别bean作为事件发布者，是因为它实现了ApplicationEventPublisherAware接口。
 * @author Administrator
 *
 */
public class CustomEventPublisher implements ApplicationEventPublisherAware {
	
    private ApplicationEventPublisher publisher;

	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.publisher = applicationEventPublisher;
	}
	
	 public void publish(){
	        CustomEvent ce = new CustomEvent(this);
	        publisher.publishEvent(ce);
	 }
	
}
