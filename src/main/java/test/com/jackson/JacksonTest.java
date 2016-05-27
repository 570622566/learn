package test.com.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class JacksonTest {
	
	
    private JsonGenerator jsonGenerator = null;
    private ObjectMapper objectMapper = null;
    private AccountBean bean = null;
    	
    @Before
    public void init(){
        bean = new AccountBean();
        bean.setAddress("china-Guangzhou");
        bean.setEmail("hoojo_@126.com");
        bean.setId(1);
        bean.setName("hoojo");
        
        
        objectMapper = new ObjectMapper();
        
        try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out,JsonEncoding.UTF8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @After
    public void destory() {
        try {
            if (jsonGenerator != null) {
                jsonGenerator.flush();
            }
            if (!jsonGenerator.isClosed()) {
                jsonGenerator.close();
            }
            jsonGenerator = null;
            objectMapper = null;
            bean = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void writeEntityJSON() {
        
        try {
            System.out.println("jsonGenerator");
            //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
            jsonGenerator.writeObject(bean);   // JsonGenerator的创建依赖于ObjectMapper对象。也就是说如果你要使用JsonGenerator来转换JSON，那么你必须创建一个ObjectMapper。
            System.out.println();
            
            System.out.println("ObjectMapper");
            //writeValue具有和writeObject相同的功能
            objectMapper.writeValue(System.out, bean); //你用ObjectMapper来转换JSON，则不需要JSONGenerator。 
            
            /**
             * objectMapper的writeValue方法可以将一个Java对象转换成JSON
             * 这个方法的参数一，需要提供一个输出流，转换后可以通过这个流来输出转换后的内容。或是提供一个File，将转换后的内容写入到File中
             * 当然，这个参数也可以接收一个JSONGenerator，然后通过JSONGenerator来输出转换后的信息。
             * 第二个参数是将要被转换的Java对象。如果用三个参数的方法，那么是一个Config,这个config可以提供一些转换时的规则，过指定的Java对象的某些属性进行过滤或转换等。
             * 如:SerializationConfig
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void  writeJSON() throws Exception{
    	
    	Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", bean.getName());
        map.put("account", bean);
        bean = new AccountBean();
        bean.setAddress("china-Beijin");
        bean.setEmail("hoojo@qq.com");
        map.put("account2", bean);
        System.out.println("jsonGenerator");
        jsonGenerator.writeObject(map);
        System.out.println("");
        System.out.println("objectMapper");
        objectMapper.writeValue(System.out, map);

    	
    }
    
    @Test
    public void writeListToJsonByGson(){
    	   List<AccountBean> list = new ArrayList<AccountBean>();
           list.add(bean);
           
           bean = new AccountBean();
           bean.setId(2);
           bean.setAddress("address2");
           bean.setEmail("email2");
           bean.setName("haha2");
           list.add(bean);
           
           
           JsonElement jsonTree = new Gson().toJsonTree(list,new TypeToken<List<AccountBean>>() {}.getType());
           System.out.println(jsonTree.getAsJsonArray());
    }
    
    @Test
    public void writeListJSON() {
        try {
            List<AccountBean> list = new ArrayList<AccountBean>();
            list.add(bean);
            
            bean = new AccountBean();
            bean.setId(2);
            bean.setAddress("address2");
            bean.setEmail("email2");
            bean.setName("haha2");
            list.add(bean);
            
            System.out.println("jsonGenerator");
            //list转换成JSON字符串
            jsonGenerator.writeObject(list);
            System.out.println();
            System.out.println("ObjectMapper");
            //用objectMapper直接返回list转换成的JSON字符串
            System.out.println("1###" + objectMapper.writeValueAsString(list));
            System.out.print("2###");
            //objectMapper list转换成JSON字符串
            objectMapper.writeValue(System.out, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void readJson2Entity() {
        String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}";
        try {
            AccountBean acc = objectMapper.readValue(json, AccountBean.class);
            System.out.println(acc.getName());
            System.out.println(acc);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void readJson2List() {
        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
                    "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
        try {
            List<LinkedHashMap<String, Object>> list = objectMapper.readValue(json, List.class);
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Set<String> set = map.keySet();
                for (Iterator<String> it = set.iterator();it.hasNext();) {
                    String key = it.next();
                    System.out.println(key + ":" + map.get(key));
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void readJson2Map() {
        String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
                    "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
        try {
            Map<String, Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
            System.out.println(maps.size());
            Set<String> key = maps.keySet();
            Iterator<String> iter = key.iterator();
            while (iter.hasNext()) {
                String field = iter.next();
                System.out.println(field + ":" + maps.get(field));
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
