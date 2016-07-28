package test.com.handle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test11 {

	public static void main(String[] args) {
		
		String s = "38&{\"IP\":\"10.49.138.176\",\"Begin\":\"2016-07-26 11:11:13\",\"End\":\"2016-07-26 11:16:14\",\"Record\":[{\"Name\":\"System\",\"Up\":1237,\"Down\":2664},{\"Name\":\"VRVEDP_M\",\"Up\":3544,\"Down\":21885},{\"Name\":\"PoliceAcessClient\",\"Up\":725,\"Down\":4647},{\"Name\":\"fsshclient\",\"Up\":1311,\"Down\":14848},{\"Name\":\"iexplore\",\"Up\":34,\"Down\":266},{\"Name\":\"WinStop\",\"Up\":120,\"Down\":2670},{\"Name\":\"GAToolBox\",\"Up\":1482,\"Down\":1642},{\"Name\":\"WinStop\",\"Up\":1268,\"Down\":7453},{\"Name\":\"vrvrf_c\",\"Up\":6966,\"Down\":3540}]}";
		System.out.println(s.substring(3));
		
		String s1 = "[{'name':'腾讯QQ'},]";
		JSONArray jsonArray = JSONArray.parseArray(s1);
		JSONObject object = (JSONObject) jsonArray.get(0);
		System.out.println(object.get("name"));
		
		net.sf.json.JSONArray jsonArray1 = net.sf.json.JSONArray.fromObject(s1);
		net.sf.json.JSONObject jsonObject = jsonArray1.getJSONObject(0);
		
		System.out.println(jsonObject.get("name"));
	}
}
