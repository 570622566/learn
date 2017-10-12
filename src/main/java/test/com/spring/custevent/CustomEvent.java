package test.com.spring.custevent;

import org.springframework.context.ApplicationEvent;


/**
 * 通过扩展ApplicationEvent,创建一个事件类CustomEvent。这个类必须定义一个默认的构造函数，它应该从ApplicationEvent类中继承的构造函数。
 * @author Administrator
 *
 */
public class CustomEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3227581660522113699L;

	public CustomEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
        return "My Custom Event";
    }

}
