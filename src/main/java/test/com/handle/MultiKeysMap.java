package test.com.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * 一个新的多主键容器类型
 * Created by Administrator on 2017/4/19.
 */
public class MultiKeysMap {

    public static void main(String[] args) {
        System.out.println("guava 提供的多主键的工具类");

        ListMultimap<String,String> multimap = ArrayListMultimap.create();
        
        multimap.put("hehe", "test");
        multimap.put("hehe", "test1");
        
        List<String> list = multimap.get("hehe");
        
        for (String s : list) {
			System.out.println(s);
		}
        
        Map<String,String> map = new HashMap<String,String>();
        map.put("hehe", "test2");
        map.put("hehe", "test3");
        
        System.out.println("MAP------"+map.get("hehe"));
        
    }
}
