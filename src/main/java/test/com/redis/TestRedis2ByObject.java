package test.com.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedis2ByObject {
	
	public static void main(String[] args) throws Exception {
		
		Jedis redis = new Jedis("localhost");
		redis.del("mingyuan");

		Set<String> set=  redis.keys("mingyuan*");//获取全部的mingyuan开头的keys集合
		System.out.println(set.size());
		System.out.println(redis.exists("mingyuan"));
		
		for (String string : set) {
			System.out.println("key:"+string);//是键的名称
		}
		
        System.out.println("hashs中添加key001和value001键值对："+redis.hset("hashs", "key001", "value001".getBytes()+"")); 
        System.out.println("hashs中添加key002和value002键值对："+redis.hset("hashs", "key002", "value002")); 
        System.out.println("hashs中添加key003和value003键值对："+redis.hset("hashs", "key003", "value003"));
        System.out.println("获取hashs中所有的key："+redis.hkeys("hashs").size());
        System.out.println("获取hashs中所有的value："+redis.hvals("hashs"));
        System.out.println(redis.hgetAll("hashs".getBytes()).values().size());

		
/*
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
        String setObjectRet = redis.set("mingyuan1".getBytes(), byteArray);
        // Long lpush = redis.lpush("key1".getBytes(), byteArray);
        
        System.out.println(" set object return \t" + setObjectRet);
         byte[] bs = redis.get("mingyuan1".getBytes());
        ByteArrayInputStream bis =  new ByteArrayInputStream(bs);
        ObjectInputStream inputStream =  new ObjectInputStream(bis);
        Person readObject = (Person) inputStream.readObject();
        System.out.println(" read object \t" + readObject.toString());
        inputStream.close();
        bis.close();
        redis.disconnect();*/
        redis.close();
	}
}
