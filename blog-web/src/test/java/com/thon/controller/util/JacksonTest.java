/**
 * 
 */
package com.thon.controller.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @file JacksonTest.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Sep 26, 2013 4:05:53 PM
 * @description TODO
 */
@SuppressWarnings("unchecked")
public class JacksonTest {
	

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, IOException {
		
		JsonToMap();
		MapToJson();
		
		//JsonToList();
		//ListToJson();
	}
	
	
	public static void JsonToMap() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = "{\"fee1\":{\"fee\":[\"1\",\"2\"],\"level\":\"0\"},\"fee2\":{\"fee\":\"3\",\"level\":\"2\"},\"fee3\":{\"fee\":\"8\",\"level\":\"3\"}}";	
		//String json = "{\"fee\":[\"1\",\"2\"],\"level\":\"0\"}";
		Map<String, Object> map= mapper.readValue(json, Map.class);
		
		// 泛型
		// Map<String,User> result = mapper.readValue(json, new TypeReference<Map<String,User>>() { });
		
		System.out.println(map);
		System.out.println(((Map<String, Object>)map.get("fee1")).get("fee"));
		System.out.println(((List<String>)((Map<String, Object>)map.get("fee1")).get("fee")).get(0));
	}
	
	public static void MapToJson() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String,String> nameStruct = Maps.newHashMap();
        nameStruct.put("first", "Joe");
        nameStruct.put("last", "Hankcs");
        
        Map<String,Object> userData = Maps.newHashMap();
        userData.put("name", nameStruct);
        userData.put("gender", "MALE");
        userData.put("verified", Boolean.FALSE);
        userData.put("userImage", "Rm9vYmFyIQ==");
        userData.put("data", new Integer[]{1,2,3,4});
		
        String json = mapper.writeValueAsString(userData);
		System.out.println(json);
	}

	public static void JsonToList() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = "[{\"value\":\"0\",\"name\":\"无服务\",\"content\":\"无服务\",\"score\":\"0\"},{\"value\":\"1\",\"name\":\"服务\",\"content\":\"服务\",\"score\":\"100\"}]";	
		String json1 = "[{\"fee\":\"1\",\"level\":\"0\"},{\"fee\":\"3\",\"level\":\"2\"},{\"fee\":\"8\",\"level\":\"3\"}]";	
		
		StylePOJO[] styles = mapper.readValue(json, StylePOJO[].class);
		List<Speed> speeds = mapper.readValue(json1, new TypeReference<List<Speed>>(){});
		for (Speed speed : speeds) {
			System.out.println(speed.getFee() + " " +speed.getLevel());
		}
	}
	
	public static void ListToJson() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<StylePOJO> ss = new ArrayList<StylePOJO>();
		StylePOJO s1 = new StylePOJO();
		s1.setValue(0);
		
		StylePOJO s2 = new StylePOJO();
		s2.setValue(1);
		
		ss.add(s1);
		ss.add(s2);
		
		String json = mapper.writeValueAsString(ss);
		System.out.println(json);
		
		StylePOJO[] styles = mapper.readValue(json, StylePOJO[].class);
		for (StylePOJO stylePOJO : styles) {
			System.out.println(stylePOJO.getValue() + " " +stylePOJO.getName());
		}
	}
}
