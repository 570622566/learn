package test.com.designpattern.structure.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/**
 * Java动态代理类位于java.lang.reflect包下，一般主要涉及到以下两个类：

(1)Interface InvocationHandler：该接口中仅定义了一个方法

public object invoke(Object obj,Method method, Object[] args)

在实际使用时，第一个参数obj一般是指代理类，method是被代理的方法，如上例中的request()，args为该方法的参数数组。 这个抽象方法在代理类中动态实现。

(2)Proxy：该类即为动态代理类，作用类似于上例中的ProxySubject，其中主要包含以下内容

protected Proxy(InvocationHandler h)：构造函数，用于给内部的h赋值。

static Class getProxyClass (ClassLoader loader, Class[] interfaces)：获得一个代理类，其中loader是类装载器，interfaces是真实类所拥有的全部接口的数组。

static Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler h)：返回代理类的一个实例，返回后的代理类可以当作被代理类使用(可使用被代理类的在Subject接口中声明过的方法)

所谓Dynamic Proxy是这样一种class：它是在运行时生成的class，在生成它时你必须提供一组interface给它，然后该class就宣称它实现了这些 interface。你当然可以把该class的实例当作这些interface中的任何一个来用。当然，这个Dynamic Proxy其实就是一个Proxy，它不会替你作实质性的工作，在生成它的实例时你必须提供一个handler，由它接管实际的工作

在使用动态代理类时，我们必须实现InvocationHandler接口。
 * 
 * 
 * @author Administrator
 *
 */
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  RealSubject rs = new RealSubject();     // 在这里指定被代理类
		     //通过被代理类对象声明一个代理类对象
	        InvocationHandler dynamicSubject = new DynamicSubject(rs);
	        Class<?> cls = rs.getClass();
		        Subject subject =  (Subject) Proxy.newProxyInstance(
	        		cls.getClassLoader(),  //定义代理类的类加载器 
	        		cls.getInterfaces(),  //代理类要实现的接口列表
	        		dynamicSubject  //指派方法调用的调用处理程序:InvocationHandler对象
	        		);
	        
	      subject.request();      //调用代理处理器上的方法
	}

}
