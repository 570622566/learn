package test.com.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class TestRedis1 {

	public static void main(String[] args) {

		Jedis jredis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		System.out.println("Server is running: " + jredis.ping());
		
		 //设置 redis 字符串数据
		jredis.set("w3ckey", "Redis tutorial");
		
		// 获取存储的数据并输出
	     System.out.println("Stored string in redis:: "+ jredis.get("w3ckey"));
	     
	     jredis.lpush("tutorial-list", "Redis");
	     jredis.lpush("tutorial-list", "Mongodb");
	     jredis.lpush("tutorial-list", "Mysql");
	     
	   System.out.println("tutorial-list length:"+jredis.llen("tutorial-list"));  
	     // 获取存储的数据并输出
	     List<String> list = jredis.lrange("tutorial-list", 0, 2);
	     for (String s : list) {
			System.out.println(s);
		}
	     
	     jredis.close();
	     
	     
	     
	}
}
