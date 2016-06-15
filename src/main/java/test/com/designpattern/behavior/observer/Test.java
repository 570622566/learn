package test.com.designpattern.behavior.observer;

import test.com.designpattern.behavior.observer.concrete.ConcreteWatched;
import test.com.designpattern.behavior.observer.concrete.ConcreteWatcher;
import test.com.designpattern.behavior.observer.role.Watched;
import test.com.designpattern.behavior.observer.role.Watcher;

/**
 * 
 * 　有时被称作发布/订阅模式，观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。

2. 解决的问题

　　将一个系统分割成一个一些类相互协作的类有一个不好的副作用，那就是需要维护相关对象间的一致性。我们不希望为了维持一致性而使各类紧密耦合，这样会给维护、扩展和重用都带来不便。观察者就是解决这类的耦合关系的。

3. 模式中的角色

　　3.1 抽象主题（Subject）：它把所有观察者对象的引用保存到一个聚集里，每个主题都可以有任何数量的观察者。抽象主题提供一个接口，可以增加和删除观察者对象。

　　3.2 具体主题（ConcreteSubject）：将有关状态存入具体观察者对象；在具体主题内部状态改变时，给所有登记过的观察者发出通知。

　　3.3 抽象观察者（Observer）：为所有的具体观察者定义一个接口，在得到主题通知时更新自己。

　　3.4 具体观察者（ConcreteObserver）：实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题状态协调。
 * 
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) {
		
        Watched girl = new ConcreteWatched();//新增一个被观察者,可以进来加删,并且存放观察者,也可以进行通知
        Watcher watcher1 = new ConcreteWatcher();//观察者
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();	
		
        
        girl.addWatcher(watcher1); //被观察者增加观察者
        girl.addWatcher(watcher2);
        girl.addWatcher(watcher3);
        
        girl.notifyWatchers("开心");
	}
}
