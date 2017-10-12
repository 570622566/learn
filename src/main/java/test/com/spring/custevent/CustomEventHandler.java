package test.com.spring.custevent;

import org.springframework.context.ApplicationListener;

public class CustomEventHandler implements ApplicationListener<CustomEvent>{

	@Override
	public void onApplicationEvent(CustomEvent event) {
		System.out.println("已经监听,执行了XXXX事件");
        System.out.println(event.toString());

	}

}
