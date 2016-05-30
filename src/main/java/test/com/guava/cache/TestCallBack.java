package test.com.guava.cache;

import java.util.concurrent.Callable;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 1. cacheLoader
　　2. callable callback

　　通过这两种方法创建的cache，和通常用map来缓存的做法比，不同在于，这两种方法都实现了一种逻辑——从缓存中取key X的值，如果该值已经缓存过了，则返回缓存中的值，如果没有缓存过，
可以通过某个方法来获取这个值。
但不同的在于cacheloader的定义比较宽泛，是针对整个cache定义的，可以认为是统一的根据key值load value的方法。而callable的方式较为灵活，允许你在get的时候指定。

 * @author Administrator
 *
 */
public class TestCallBack {
	
	public static void main(String[] args) throws Exception {
		
		Cache<String,String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
		
		String resultVal = cache.get("jerry", new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				String strProValue = "hello " + "jerry"+"!";
				return strProValue;
			}
		});
		
        System.out.println("jerry value : " + resultVal);
        
		
        resultVal = cache.get("peida", new Callable<String>(){

			@Override
			public String call() throws Exception {
				String strProValue="hello "+"peida"+"!";                
                return strProValue;
			}
        	
        });
        
        System.out.println("peida value : " + resultVal);  

        
	}
}
