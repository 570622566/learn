package test.com.designpattern.structure.static1;

/**
 * 真实角色：实现了Subject的request()方法
 */
public class RealSubject extends Subject {

	 public RealSubject() {
	    }
	
	@Override
	public void request() {
		// TODO Auto-generated method stub
        System.out.println("真实角色的请求方法RealSubject.request()被调用!");

	}

}
