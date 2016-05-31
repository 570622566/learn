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
	/**
	 * 其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 单一工厂   就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
		SendFactory sender = new SendFactory();
		Sender send = sender.produce("sms");
		send.Send();

		/**
		 * 多工厂设计模式
		 * 是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象
		 */
		test.com.designpattern.create.factory.mulfactory.SendFactory mulfactory = new test.com.designpattern.create.factory.mulfactory.SendFactory();
		Sender sender1 = mulfactory.produceMail();
		sender1.Send();
		
		/**
		 * 静态工厂设计模式
		 * 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，第三种相对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模
		 */
		Sender sender2 = test.com.designpattern.create.factory.staticfactory.SendFactory.produceMail();
		sender2.Send();
		
	}
}
