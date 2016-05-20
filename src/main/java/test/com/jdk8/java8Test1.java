package test.com.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 方法帮助指向他们的名字 .简化用法
 * @author Administrator
 *
 */
public class java8Test1 {
	
	public static void main(String[] args) {
		/**
		 * ::
		 * 应用的类型有:
		 * 1,静态方法
		 * 2,实例化方法
		 * 3,构造方法 Constructors using new operator (TreeSet::new)
		 */
		  List names = new ArrayList();
			
	      names.add("Mahesh");
	      names.add("Suresh");
	      names.add("Ramesh");
	      names.add("Naresh");
	      names.add("Kalpesh");
	      
	      
	      names.forEach(System.out::println);//forEach也是jdk8's 新特性
		
		/**
		 * jdk8定义了很多函数接口用lambda表达式 ;Predicate用来检测使用的是这真的还是假的
		 */
	      
	      List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	      // Predicate<Integer> predicate = n -> true
	      // n is passed as parameter to test method of Predicate interface
	      // test method will always return true no matter what value n has.
	      System.out.println("Print all numbers:");
	      //pass n as parameter
	      eval(list, n->true);
	      
	   // Predicate<Integer> predicate1 = n -> n%2 == 0
	      // n is passed as parameter to test method of Predicate interface
	      // test method will return true if n%2 comes to be zero
	      
	      System.out.println("Print even numbers:");
	      eval(list, n-> n%2 == 0 );// 相当于 Predicate<Integer> predicate 表达式:  n-> n%2 == 0 
	      	
	      
	      // Predicate<Integer> predicate2 = n -> n > 3
	      // n is passed as parameter to test method of Predicate interface
	      // test method will return true if n is greater than 3.

	      System.out.println("Print numbers greater than 3:");
	      eval(list, n-> n > 3 );  //n>3是检测的表达式
	      
	}

	private static void eval(List<Integer> list, Predicate<Integer> predicate) {
		 for(Integer n: list) {
	         if(predicate.test(n)) {
	            System.out.println(n + " ");
	         }
	      }
	}
}
