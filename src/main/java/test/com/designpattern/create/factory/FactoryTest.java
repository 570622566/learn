package test.com.designpattern.create.factory;

import test.com.designpattern.create.factory.onlyone.SendFactory;

/**
 * @author Administrator 一个工厂设计模式
 * 
 * 
 * 
 * 总体来说设计模式分为三大类：
创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
 * 
 * 
 * 
 * 
 */
public class FactoryTest {

	public static void main(String[] args) {

		// 单一工厂
		SendFactory sender = new SendFactory();
		Sender send = sender.produce("sms");
		send.Send();

		/**
		 * 多工厂设计模式
		 * 
		 */
		test.com.designpattern.create.factory.mulfactory.SendFactory mulfactory = new test.com.designpattern.create.factory.mulfactory.SendFactory();
		Sender sender1 = mulfactory.produceMail();
		sender1.Send();
		
		/**
		 * 静态工厂设计模式
		 */
		Sender sender2 = test.com.designpattern.create.factory.staticfactory.SendFactory.produceMail();
		sender2.Send();

	}
}
