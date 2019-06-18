package com.exam.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.exam.util.AttendDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class testArray {

	public static void main(String[] args) {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date  = sdf.format(curDate);
		System.out.println(date);
		// JSONarray -> String
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("FA000001", "nor");
		obj.put("FA000002", "late");
		obj.put("FA000003", "nor");
		obj.put("FA000004", "va");
		obj.put("FA000005", "abse");
		
		obj.put("FA000006", "nor");
		obj.put("FA000007", "late");
		obj.put("FA000008", "nor");
		obj.put("FA000009", "nor");
		
		array.add(obj);
		String str = array.toJSONString();
		
		// 근무 그룹 판별-
		SimpleDateFormat sdfTime = new SimpleDateFormat ("HHmmss");
		int time = Integer.parseInt(sdfTime.format(curDate));


		
	
//		String status = jsonObject.get("status").getAsString();
//		String message = jsonObject.get("message").getAsString();
		
		
		//JSONArray nArray = new JSONArray(str);
		try {
			JSONParser parser = new JSONParser();
			Object object = parser.parse(str);
			JSONArray jsonArray = (JSONArray)object;
			List<Map<String, Object>> listMap = testArray.getListMapFromJsonArray(jsonArray);
			for(int i =0; i <listMap.size(); i++) {
				
				Map<String, Object> map = listMap.get(i);  // 하루치 근무일지 
				System.out.println(map.toString());
				String test =map.get("FA000003").toString(); // 해당 eid의 근무상태

				JSONObject objTemp = new JSONObject();
				objTemp = AttendDao.getJsonStringFromMap(map);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}//main

     // JsonObject -> Map<String, String>
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj )
    {
        Map<String, Object> map = null;
        
        try {
            
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
            
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return map;
    }
 
    //JsonArray-> List<Map<String, String>>
    public static List<Map<String, Object>> getListMapFromJsonArray( JSONArray jsonArray )
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        if( jsonArray != null )
        {
            int jsonSize = jsonArray.size();
            for( int i = 0; i < jsonSize; i++ )
            {
                Map<String, Object> map = testArray.getMapFromJsonObject( ( JSONObject ) jsonArray.get(i) );
                list.add( map );
            }
        }
        
        return list;
    }
		 

}
