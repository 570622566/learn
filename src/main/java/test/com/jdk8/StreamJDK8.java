package test.com.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamJDK8 {

	
	public static void main(String[] args) {
		/**
		 * Using stream, you can process data in a declarative way similar to SQL statements
		 * 
		 * 
		 * Using collections framework in Java, a developer has to use loops and make repeated checks.
		 *  Another concern is efficiency; as multi-core processors are available at ease,
		 *   a Java developer has to write parallel并行 code processing that can be pretty error-prone已于出错.
		 * 
		 * To resolve such issues, Java 8 introduced the concept of stream that lets the developer to process data declaratively声明的 and leverage multicore architecture without the need to write any specific code for it.
		 * 利用多核体系结构,而不需要编写额外的代码
		 */
		
		
		/**
		 * Stream represents a sequence of objects from a source, which supports aggregate operations.
		 * 流表示一系列对象的源头,他支持聚合操作
		 */
		
		/**
		 * 序列化元素  :一个流提供一系列特定类型的元素序列.一个流能够得到和估算所需要的元素,兵器从不存储
		 * 
		 * 源头: 流需要集合,数组,io资源进行输入
		 * 
		 * 流支持聚合操作: 支持聚合操作,像filter过滤,map映射,limit限制,reduce缩减,find查找,match匹配,
		 * 
		 * 管道化:流操作返回流本身,结果是流水线的.这些操作被称为中间接口操作,他们的方法放入,执行,返回输出到目标,collect()方法是管道流终端的一个结果展示
		 * 
		 * 自动迭代:流操作进行源头元素进行内部迭代,区别于集合的显示必须的迭代.
		 */
		
		/**
		 * 有两种方法来生成流:串行和并行
		 * 
		 * stream() − Returns a sequential stream considering collection as its source.返回顺序流考虑集合作为来源
		 * 
		 * parallelStream() − Returns a parallel Stream considering collection as its source.返回一个并行流作为来源
		 * 
		 * 
		 * List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		   List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

		 */
		
		/**
		 * map:The ‘map’ method is used to map each element to its corresponding result. The following code segment prints unique squares of numbers using map.

			map表示每个元素映射到相应的结果.
			demo:唯一的数字方块
			
			List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
			//get list of unique squares
			List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		 * 
		 * 类似:filter soretd limit
		 */
		
		
		/**
		 * 并行管道流:Parallel Processing  是串行的一个替代
		 * List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

			int count = strings.parallelStream().filter(string -> string.isEmpty()).count();
			非常容易的 switch between sequential and parallel streams
		 * 
		 * 
		 */
		
		/**
		 * Collector:Collectors are used to combine the result of processing on the elements of a stream.
		 *  Collectors can be used to return a list or a string.
		 * 用于收集元素的执行结果;可返回作为一个list或者字符串
		 * 
		 * List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		   List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

		 * System.out.println("Filtered List: " + filtered);
			String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
			System.out.println("Merged String: " + mergedString);
		 */
		
		/**
		 * Statistics:统计数据计算器来介绍当流执行完成时候
		 * List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

			IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();
			
			System.out.println("Highest number in List : " + stats.getMax());
			System.out.println("Lowest number in List : " + stats.getMin());
			System.out.println("Sum of all numbers : " + stats.getSum());
			System.out.println("Average of all numbers : " + stats.getAverage());
		 * 
		 */
		
		 System.out.println("Using Java 7: ");
			
	      // Count empty strings
	      List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
	      System.out.println("List: " +strings);
	      long count = getCountEmptyStringUsingJava7(strings);
			
	      System.out.println("Empty Strings: " + count);
	      count = getCountLength3UsingJava7(strings);
			
	      System.out.println("Strings of length 3: " + count);
			
	      //Eliminate empty string
	      List<String> filtered = deleteEmptyStringsUsingJava7(strings);
	      System.out.println("Filtered List: " + filtered);
			
	      //Eliminate empty string and join using comma.
	      String mergedString = getMergedStringUsingJava7(strings,", ");
	      System.out.println("Merged String: " + mergedString);
	      List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
			
	      //get list of square of distinct numbers
	      List<Integer> squaresList = getSquares(numbers);
	      System.out.println("Squares List: " + squaresList);
	      List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
			
	      System.out.println("List: " +integers);
	      System.out.println("Highest number in List : " + getMax(integers));
	      System.out.println("Lowest number in List : " + getMin(integers));
	      System.out.println("Sum of all numbers : " + getSum(integers));
	      System.out.println("Average of all numbers : " + getAverage(integers));
	      System.out.println("Random Numbers: ");
			
	      //print ten random numbers
	      Random random = new Random();
			
	      for(int i=0; i < 10; i++){
	         System.out.println(random.nextInt());
	      }
			
	      System.out.println("Using Java 8: ");
	      System.out.println("List: " +strings);
			
	      count = strings.stream().filter(string->string.isEmpty()).count();
	      System.out.println("Empty Strings: " + count);
			
	      count = strings.stream().filter(string -> string.length() == 3).count();
	      System.out.println("Strings of length 3: " + count);
			
	      filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
	      System.out.println("Filtered List: " + filtered);
			
	      mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
	      System.out.println("Merged String: " + mergedString);
			
	      squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
	      System.out.println("Squares List: " + squaresList);
	      System.out.println("List: " +integers);
			
	      IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();
			
	      System.out.println("Highest number in List : " + stats.getMax());
	      System.out.println("Lowest number in List : " + stats.getMin());
	      System.out.println("Sum of all numbers : " + stats.getSum());
	      System.out.println("Average of all numbers : " + stats.getAverage());
	      System.out.println("Random Numbers: ");
			
	      random.ints().limit(10).sorted().forEach(System.out::println);
			
	      //parallel processing
	      count = strings.parallelStream().filter(string -> string.isEmpty()).count();
	      System.out.println("Empty Strings: " + count);
	   }
		
	   private static int getCountEmptyStringUsingJava7(List<String> strings){
	      int count = 0;
			
	      for(String string: strings){
			
	         if(string.isEmpty()){
	            count++;
	         }
	      }
	      return count;
	   }
		
	   private static int getCountLength3UsingJava7(List<String> strings){
	      int count = 0;
			
	      for(String string: strings){
			
	         if(string.length() == 3){
	            count++;
	         }
	      }
	      return count;
	   }
		
	   private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
	      List<String> filteredList = new ArrayList<String>();
			
	      for(String string: strings){
			
	         if(!string.isEmpty()){
	             filteredList.add(string);
	         }
	      }
	      return filteredList;
	   }
		
	   private static String getMergedStringUsingJava7(List<String> strings, String separator){
	      StringBuilder stringBuilder = new StringBuilder();
			
	      for(String string: strings){
			
	         if(!string.isEmpty()){
	            stringBuilder.append(string);
	            stringBuilder.append(separator);
	         }
	      }
	      String mergedString = stringBuilder.toString();
	      return mergedString.substring(0, mergedString.length()-2);
	   }
		
	   private static List<Integer> getSquares(List<Integer> numbers){
	      List<Integer> squaresList = new ArrayList<Integer>();
			
	      for(Integer number: numbers){
	         Integer square = new Integer(number.intValue() * number.intValue());
				
	         if(!squaresList.contains(square)){
	            squaresList.add(square);
	         }
	      }
	      return squaresList;
	   }
		
	   private static int getMax(List<Integer> numbers){
	      int max = numbers.get(0);
			
	      for(int i=1;i < numbers.size();i++){
			
	         Integer number = numbers.get(i);
				
	         if(number.intValue() > max){
	            max = number.intValue();
	         }
	      }
	      return max;
	   }
		
	   private static int getMin(List<Integer> numbers){
	      int min = numbers.get(0);
			
	      for(int i=1;i < numbers.size();i++){
	         Integer number = numbers.get(i);
			
	         if(number.intValue() < min){
	            min = number.intValue();
	         }
	      }
	      return min;
	   }
		
	   private static int getSum(List numbers){
	      int sum = (int)(numbers.get(0));
			
	      for(int i=1;i < numbers.size();i++){
	         sum += (int)numbers.get(i);
	      }
	      return sum;
	   }
		
	   private static int getAverage(List<Integer> numbers){
	      return getSum(numbers) / numbers.size();
	   }
}
