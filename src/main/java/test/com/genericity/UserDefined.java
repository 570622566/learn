package test.com.genericity;

public class UserDefined {
	
	public static void main(String[] args) {
		Teacher<Integer> p = new Teacher<Integer>(11, 11, "bb");  
        System.out.println(p.getT());  
      //  System.out.println(p.getA());  
        //System.out.println(p.getAa());  
	}
}


class Teacher<T>{
	
	
	private T t;
	private int age ;
	private String name;
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	 public Teacher(T t, int age, String name) {  
	        super();  
	        this.t = t;  
	        this.age = age;  
	        this.name = name;  
	    }
	 
	@Override
	public String toString() {
		return "Teacher [t=" + t + ", age=" + age + ", name=" + name + "]";
	}  
	
}