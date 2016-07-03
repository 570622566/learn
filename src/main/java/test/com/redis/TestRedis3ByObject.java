package test.com.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class TestRedis3ByObject {

	public static void main(String[] args) {

	//	testSetElements();
        testSetEnsemble();  


	}

	private static void testSetEnsemble() {
		  List<User> testData = buildTestData();  
	        Jedis jedis = buildJedisPool().getResource();  
	        String key = "testSetEnsemble" + new Random(1000).nextInt();  
	        jedis.set(key.getBytes(), ListTranscoder.serialize(testData));  
	  
	        //验证  
	        byte[] in = jedis.get(key.getBytes());  
	        List<User> list = (List<User>)ListTranscoder.deserialize(in);  
	        for(User user : list){  
	            System.out.println("testSetEnsemble user name is:" + user.getName());  
	        }  
	}

	public static JedisPool buildJedisPool() {

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(3000);
		config.setMinIdle(50);
		config.setMaxWaitMillis(5000);
		JedisPool jedisPool = new JedisPool(config, Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
		return jedisPool;
	}

	private static void testSetElements() {
		List<User> testData = buildTestData();
		Jedis jedis = buildJedisPool().getResource();
		String key = "session";
		jedis.set(key.getBytes(), ObjectsTranscoder.serialize(testData));

		// 验证
		byte[] in = jedis.get(key.getBytes());
		List<User> list =  ObjectsTranscoder.deserialize(in);
		for (User user : list) {
			System.out.println("testSetElements user name is:" + user.getName());
		}
	}

	private static List<User> buildTestData() {

		User a = new User();
		a.setName("a");
		User b = new User();
		b.setName("b");
		List<User> list = new ArrayList<User>();
		list.add(a);
		list.add(b);
		return list;
	}
	
	
	 static class ListTranscoder{  
	        public static byte[] serialize(Object value) {  
	            if (value == null) {  
	                throw new NullPointerException("Can't serialize null");  
	            }  
	            byte[] rv=null;  
	            ByteArrayOutputStream bos = null;  
	            ObjectOutputStream os = null;  
	            try {  
	                bos = new ByteArrayOutputStream();  
	                os = new ObjectOutputStream(bos);  
	                os.writeObject(value);  
	                os.close();  
	                bos.close();  
	                rv = bos.toByteArray();  
	            } catch (IOException e) {  
	                throw new IllegalArgumentException("Non-serializable object", e);  
	            } finally {  
	                close(os);  
	                close(bos);  
	            }  
	            return rv;  
	        }  
	  
	        public static Object deserialize(byte[] in) {  
	            Object rv=null;  
	            ByteArrayInputStream bis = null;  
	            ObjectInputStream is = null;  
	            try {  
	                if(in != null) {  
	                    bis=new ByteArrayInputStream(in);  
	                    is=new ObjectInputStream(bis);  
	                    rv=is.readObject();  
	                    is.close();  
	                    bis.close();  
	                }  
	            } catch (IOException e) {  
	            } catch (ClassNotFoundException e) {  
	            } finally {  
	               close(is);  
	               close(bis);  
	            }  
	            return rv;  
	        }  
	    }  

	static class ObjectsTranscoder {

		public static byte[] serialize(List<User> value) {
			if (value == null) {
				throw new NullPointerException("Can't serialize null");
			}
			byte[] rv = null;
			ByteArrayOutputStream bos = null;
			ObjectOutputStream os = null;
			try {
				bos = new ByteArrayOutputStream();
				os = new ObjectOutputStream(bos);
				for (User user : value) {
					os.writeObject(user);
				}
				os.writeObject(null);
				os.close();
				bos.close();
				rv = bos.toByteArray();
			} catch (IOException e) {
				throw new IllegalArgumentException("Non-serializable object", e);
			} finally {
				close(os);
				close(bos);
			}
			return rv;
		}
		
		
		  public static List<User> deserialize(byte[] in) {  
	            List<User> list = new ArrayList<User>();  
	            ByteArrayInputStream bis = null;  
	            ObjectInputStream is = null;  
	            try {  
	                if(in != null) {  
	                    bis=new ByteArrayInputStream(in);  
	                    is=new ObjectInputStream(bis);  
	                    while (true) {  
	                        User user = (User) is.readObject();  
	                        if(user == null){  
	                            break;  
	                        }else{  
	                            list.add(user);  
	                        }  
	                    }  
	                    is.close();  
	                    bis.close();  
	                }  
	            } catch (IOException e) {  
	            } catch (ClassNotFoundException e) {  
	            } finally {  
	                close(is);  
	               close(bis);  
	            }  
	            return list;  
	        } 
	}

	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
			}
		}
	}

	static class User implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5284508563170126834L;
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
