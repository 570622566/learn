package test.com.jdk8;

/**
 * @author Administrator
 *
 */
public class DefaultMethodJDK8Test {
	
	public static void main(String[] args) {
		
		/**
		 * Java 8 introduces default method so that List/Collection interface can
		 *  have a default implementation of forEach method, 
		 * and the class implementing these interfaces need not implement the same
		 * 
		 * 
		 */
		
	      Vehicle vehicle = new Car();
	      vehicle.print();
	}
}

interface Vehicle {
	   default void print(){
	      System.out.println("I am a vehicle!");
	   }
		
	   static void blowHorn(){
	      System.out.println("Blowing horn!!!");
	   }
	}

interface FourWheeler {
	   default void print(){
	      System.out.println("I am a four wheeler!");
	   }
	}

class Car implements Vehicle, FourWheeler {
	   public void print(){
	      Vehicle.super.print();
	      FourWheeler.super.print();
	      Vehicle.blowHorn();
	      System.out.println("I am a car!");
	   }
	}