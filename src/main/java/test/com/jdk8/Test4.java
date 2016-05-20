package test.com.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

public class Test4 {
	
	public static void main(String[] args) throws Exception {
	    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	    Stream<Integer> stream = numbers.stream();
	    
	    stream.filter((x) -> {
	        return x % 2 == 0;
	    }).map((x) -> {
	        return x * x;
	    }).forEach(System.out::println);
		
	    //Creates a mutable ArrayList instance containing the given elements. 
	    /**
	     * 上面这段代码是获取一个List中，元素不为null的个数。这段代码虽然很简短，但是却是一个很好的入门级别的例子来体现如何使用Stream，正所谓“麻雀虽小五脏俱全”。我们现在开始深入解刨这个例子，完成以后你可能可以基本掌握Stream的用法！
	     */
	    List<Integer> nums = Lists.newArrayList(1,null,3,4,null,6);
	    nums.stream().filter(num -> num != null).count();
	    System.out.println(nums.size());	
	    
	    
	    //一个接受变长参数T...，一个接口单一值
	    Stream<Integer> integerStream = Stream.of(1,2,3,5);
	    Stream<String> stringStream = Stream.of("taobao");
	    Stream<Double> generate = Stream.generate(new Supplier<Double>(){

			@Override
			public Double get() {
				// TODO Auto-generated method stub     这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象
				return Math.random();  
			}
	    	
	    });
	    Stream<Double> generate2 = Stream.generate(() -> Math.random());  //三条语句的作用都是一样的，只是使用了lambda表达式和方法引用的语法来简化代码
	    Stream<Double> generate3 = Stream.generate(Math::random);  //都是生成一个无限长度的Stream，其中值是随机的
	    System.out.println("1:"+generate);
	    System.out.println("2:"+generate2);
	    System.out.println("3:"+generate3);
	    
	    Stream.iterate(1, item -> item +1).limit(10).forEach(System.out::println); //这段代码就是先获取一个无限长度的正整数集合的Stream，然后取出前10个打印。千万记住使用limit方法，不然会无限打印下去。


	    
	    
	}
}
