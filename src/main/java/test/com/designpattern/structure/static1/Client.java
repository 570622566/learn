package test.com.designpattern.structure.static1;
/*●　　抽象对象角色：声明了目标对象和代理对象的共同接口，这样一来在任何可以使用目标对象的地方都可以使用代理对象。

●　　目标对象角色：定义了代理对象所代表的目标对象。

●　　代理对象角色：代理对象内部含有目标对象的引用，从而可以在任何时候操作目标对象；代理对象提供一个与目标对象相同的接口，以便可以在任何时候替代目标对象。代理对象通常在客户端调用传递给目标对象之前或之后，执行某个操作，而不是单纯地将调用传递给目标对象。*/
public class Client {
	  public static void main(String[] args) {
	        Subject sub = new ProxySubject();
	        sub.request();
	    }
	  /**
	   * 
	   * 静态代理代理和被代理对象在代理之前是确定的
	   * 
	   * 他们都实现相同的接口或者继承相同的抽象类。
	   */
}