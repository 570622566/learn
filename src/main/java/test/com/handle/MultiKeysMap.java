package test.com.handle;

import java.util.*;

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

        ListMultimap<String,List<String>> multimap2 = ArrayListMultimap.create();
        multimap2.put("1", Arrays.asList("1,2,3,4".split(",")));
        multimap2.put("2", Arrays.asList("5,6,7,8,91,32".split(",")));

        Set<String> keys = multimap2.keySet();
        for (String s : keys) {
            System.out.println("key:"+s);
            System.out.println(multimap2.get(s));
        }

        Map<String,String> map = new HashMap<String,String>();
        map.put("hehe", "test2");
        map.put("hehe", "test3");
        
        System.out.println("MAP------"+map.get("hehe"));
        
    }
}
