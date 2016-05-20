package test.com.iteratorfile;

import java.util.Arrays;
import java.util.List;
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
	    
	}
}
