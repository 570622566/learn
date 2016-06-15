package test.com.designpattern.behavior.observer.concrete;

import java.util.ArrayList;
import java.util.List;

import test.com.designpattern.behavior.observer.role.Watched;
import test.com.designpattern.behavior.observer.role.Watcher;

/**
 * 
 * 在具体主题内部状态改变时，给所有登记过的观察者发出通知。具体主题角色通常用一个子类实现。
 * @author Administrator
 *
 *
 *之后是具体的主题角色：
 */
public class ConcreteWatched implements Watched {
	
	// 存放观察者   　
    private List<Watcher> list = new ArrayList<Watcher>();
	
	@Override
	public void addWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
        list.add(watcher);

	}

	@Override
	public void removeWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
        list.remove(watcher);
	}

	@Override
	public void notifyWatchers(String str) {
		// TODO Auto-generated method stub
		System.out.println("notifyWatchers..."+str);
		 // 自动调用实际上是主题进行调用的
        for (Watcher watcher : list)
        {
            watcher.update(str);
        }
	}

}
