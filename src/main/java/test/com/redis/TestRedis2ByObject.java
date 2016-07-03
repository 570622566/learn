package test.com.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;

public class TestRedis2ByObject {
	
	public static void main(String[] args) throws Exception {
		
		Jedis redis = new Jedis("localhost");

        String set = redis.set("mingyuan", "1");
        System.out.println(" set result \t" + set);
        
        redis.incr("mingyuan");
        String string = redis.get("mingyuan");
        System.out.println(" get result of key 'mingyuan' \t" + string);
        
        
        // 下面是对对象进行存储的测试代码
        ByteArrayOutputStream bos =  new ByteArrayOutputStream();
        ObjectOutputStream oos =  new ObjectOutputStream(bos);
        
        Person person =  new Person("liudehua" ,22);
        oos.writeObject(person);
         byte[] byteArray = bos.toByteArray();
        oos.close();
        bos.close();
        String setObjectRet = redis.set("mingyuan".getBytes(), byteArray);
        System.out.println(" set object return \t" + setObjectRet);
         byte[] bs = redis.get("mingyuan".getBytes());
        ByteArrayInputStream bis =  new ByteArrayInputStream(bs);
        ObjectInputStream inputStream =  new ObjectInputStream(bis);
        Person readObject = (Person) inputStream.readObject();
        System.out.println(" read object \t" + readObject.toString());
        inputStream.close();
        bis.close();
        redis.disconnect();
	}
}
