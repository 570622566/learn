package test.com.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

	public static void main(String[] args) {
		// 1、创建Spring的IOC的容器
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		// 2、从IOC容器中获取bean的实例
		
		  TargetClass targetClass = (TargetClass) ctx.getBean("targetClass");
		  
		  // 3、使用bean 
		  
		  String result = targetClass.joint("spring", "aop");
		 System.out.println("result:" + result);
	}

}
