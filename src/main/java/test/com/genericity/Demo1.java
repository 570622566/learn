package test.com.genericity;

//自定义类上的泛型  
public class Demo1<T> {

	
	public void testfuntion() {  
        funtion("aaa");  
    }  
	
	
	
	//自定义带泛型的方法  
    public <T>T funtion(T t) {  
          
        return null;  
    }  
    
    
    public <T,E,K,M,C>void b(T t,E e,K k,M m,C c) {  
        
    }  
    
    //静态方法泛型定义在static后   
    public static<T> void c(T t) {  
          
    }  
    
    
}
