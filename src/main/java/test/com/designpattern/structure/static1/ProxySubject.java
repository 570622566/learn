package test.com.designpattern.structure.static1;

public class ProxySubject extends Subject {
	
    private RealSubject realSubject; // 以真实角色作为代理角色的属性
    public ProxySubject() {
    }
	
	@Override
	public void request() {
		// TODO Auto-generated method stub
        preRequest();               //代理前执行的操作
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();     // 此处执行真实对象的request方法
        postRequest();              //代理后执行的操作

	}
	  private void preRequest() {
		     // something you want to do before requesting
		        System.out.println("代理前执行的操作ProxySubject.preRequest()!");
		    }
	  
	  private void postRequest() {
		     // something you want to do after requesting
		        System.out.println("代理后执行的操作ProxySubject.postRequest()!");
		    }
}
