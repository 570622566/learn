package test.com.enums;

public class SimpleEnumUse {
	public static void main(String[] args) {
		//当你创建enum时，它会创建toString()方法。编译器还会创建ordinal() 方法用来表示某个特定的enum常量的声明顺序，以及static values()方法
		//编译器还会创建ordinal() 方法用来表示某个特定的enum常量的声明顺序，以及static values()方法
		//用来按照enum的声明顺序，产生由这些常量构成的数组。
		// enum实际上是一种类，并且拥有自己的方法


		
		Enum1 e1 = Enum1.MEDIUM;
		System.out.println(e1);
		
		
		for (Enum1 e : Enum1.values()) {
			System.out.println(e + ".ordinal " + e.ordinal() );
			
		}
		
	}
}
