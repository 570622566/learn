package test.com.genericity;

public class GenericTest1 {

	public static void main(String[] args) {

		Box<Number> number = new Box<Number>(99);
		Box<Integer> age = new Box<Integer>(712);
	    Box<String> name = new Box<String>("corn");
		
		 getData(name);
		 getData(age);
		 getData(number);
		 
		 getUpperNumberData(number);
		 getUpperNumberData(age);
		// getUpperNumberData(name);
	}

	private static void getData(Box<?> data) {
		System.out.println("data :" + data.getData());
	}
	
	  public static void getUpperNumberData(Box<? extends Number> data){
        System.out.println("data :" + data.getData());
      }
}
