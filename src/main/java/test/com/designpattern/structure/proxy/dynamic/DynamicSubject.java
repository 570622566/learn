package test.com.designpattern.structure.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {
	
    private Object sub;       //被代理对象
    public DynamicSubject() {
    }
    
    /**
     * 构造方法,并初始化被代理对象
     * @param obj 是被代理的对象
     */
    public DynamicSubject(Object obj) {
        sub = obj;
    }
	
    /**
    *
    * @param proxy  指代理类
    * @param method 被代理的方法
    * @param args   被代理的方法所需要的参数数组
    * @return  在代理实例上处理方法调用并返回结果。
    * @throws Throwable
    */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		  System.out.println("代理前执行的操作!");
	        method.invoke(sub, args);
	        System.out.println("代理后执行的操作!");
	        return null;
	}

}
