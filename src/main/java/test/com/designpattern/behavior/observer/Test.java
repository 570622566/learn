package test.com.designpattern.behavior.observer;

import test.com.designpattern.behavior.observer.concrete.ConcreteWatched;
import test.com.designpattern.behavior.observer.concrete.ConcreteWatcher;
import test.com.designpattern.behavior.observer.role.Watched;
import test.com.designpattern.behavior.observer.role.Watcher;

public class Test {
	public static void main(String[] args) {
		
        Watched girl = new ConcreteWatched();
        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();	
		
        
        girl.addWatcher(watcher1);
        girl.addWatcher(watcher2);
        girl.addWatcher(watcher3);
        
        girl.notifyWatchers("开心");
	}
}
