package test.com.designpattern.behavior.observer;

/**
 * @author Administrator
 *  抽象观察者角色

 */
public interface Watcher {

	/**
		为所有具体的观察者定义一个接口，在得到主题的通知时更新自己。
	 */
	public void update(String str);
}
