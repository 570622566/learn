package test.com.designpattern.behavior.observer.role;

import test.com.designpattern.behavior.observer.Watcher;

/**
 * @author Administrator
 *把所有对观察者对象的引用保存在一个集合中，每个抽象主题角色都可以有任意数量的观察者。抽象主题提供一个接口，可以增加和删除观察者角色。一般用一个抽象类和接口来实现。
 */
public interface Watched { 
	// 抽象主题角色，watched：被观察
	
	public void addWatcher(Watcher watcher);

	public void removeWatcher(Watcher watcher);

	public void notifyWatchers(String str);

}
