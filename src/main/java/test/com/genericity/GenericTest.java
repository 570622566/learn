package test.com.genericity;

/**
 * 
 * 在于Java中的泛型这一概念提出的目的，导致其只是作用于代码编译阶段，在编译过程中，对于正确检验泛型结果后，
 * 会将泛型的相关信息擦出，也就是说，成功编译过后的class文件中是不包含任何泛型信息的。泛型信息不会进入到运行时阶段。
 * 泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型。
 * @author Administrator
 *
 */
public class GenericTest {
	
	
	public static void main(String[] args) {
		
		 Box<String> name = new Box<String>("corn");
		 System.out.println("name:" + name.getData());
	     Box<Integer> age = new Box<Integer>(712);
	     
	     System.out.println("age class:" + age.getClass()); 
	     System.out.println("name class:" + name.getClass());
	     System.out.println(name.getClass() == age.getClass());   
	     
	     
	}
}

class Box<T>{
	
	private T data;
	
	public Box(){
		
	}
	
	public Box(T data){
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
}