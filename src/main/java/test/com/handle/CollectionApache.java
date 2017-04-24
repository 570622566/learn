package test.com.handle;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.LinkedMap;

/**
 * Created by Administrator on 2017/4/20.
 */
public class CollectionApache {

    public static void main(String[] args) {
        OrderedMap om = new LinkedMap();

        om.put("one", 1);
        om.put("two", "2");
        om.put("three", "three");
        om.put("four", 4);
        om.put("five", "5");
        om.put("six", "6");

        System.out.println(om.firstKey());
        System.out.println(om.lastKey());
        System.out.println(om.lastKey());
        System.out.println(om.nextKey("four"));
        System.out.println(om.previousKey("four"));
        System.out.println(om.get("four"));


        System.out.println("==============================");

        BidiMap bm = new TreeBidiMap();
        bm.put("three", "3");
        bm.put("five", "isfive");
        bm.put("six", "isfive");
        System.out.println(bm.getKey("isfive").toString()); //诺优多个value则只会显示最新的那个
        System.out.println(bm.get("three"));
    }
}
