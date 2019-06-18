package com.exam.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.exam.domain.EmployeeVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AttendDao {
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
                Map<String, Object> map = AttendDao.getMapFromJsonObject( ( JSONObject ) jsonArray.get(i) );
                list.add( map );
            }
        }
        
        return list;
    }
    
    // Map ->  jsonobject
    public static JSONObject getJsonStringFromMap( Map<String, Object> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }
        
        return jsonObject;
    }
    // Mapss ->  jsonobject
    public static JSONObject getJsonStringFromMapss( Map<String, String> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Entry<String, String> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }
        
        return jsonObject;
    }
    
    // List<Map>-> jsonArray
    public static JSONArray getJsonArrayFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.add( getJsonStringFromMap( map ) );
        }
        
        return jsonArray;
    }
    
    // 한달 일수 생성 메소드 
    public static List<String> planning(int year, int month) {
		// 해당달에 대한 날짜 생성
		List<String> monthList = new ArrayList<String>();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			for (int date = 1; date < 32; date++) {
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(year));
				sb.append("-");
				// 달
				if (month < 10) {
					sb.append("0");
					sb.append(String.valueOf(month));
					sb.append("-");
				} else {
					sb.append(String.valueOf(month));
					sb.append("-");
				}
				if (date < 10) {
					sb.append("0");
					sb.append(String.valueOf(date));
				} else {
					sb.append(String.valueOf(date));
				}
				monthList.add(sb.toString());
			} // for
		} else if (month == 2) {
			for (int date = 1; date < 28; date++) {
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(year));
				sb.append("-");
				// 달
				if (month < 10) {
					sb.append("0");
					sb.append(String.valueOf(month));
					sb.append("-");
				} else {
					sb.append(String.valueOf(month));
					sb.append("-");
				}
				if (date < 10) {
					sb.append("0");
					sb.append(String.valueOf(date));
				} else {
					sb.append(String.valueOf(date));
				}
				monthList.add(sb.toString());
			} // for
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			for (int date = 1; date < 31; date++) {
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(year));
				sb.append("-");
				// 달
				if (month < 10) {
					sb.append("0");
					sb.append(String.valueOf(month));
					sb.append("-");
				} else {
					sb.append(String.valueOf(month));
					sb.append("-");
				}
				if (date < 10) {
					sb.append("0");
					sb.append(String.valueOf(date));
				} else {
					sb.append(String.valueOf(date));
				}
				monthList.add(sb.toString());
			} // for
		}
		return monthList;
	}// planning
    
    public static String attendByDaily(List<EmployeeVo> eidList) {
    	// 오늘날짜로 직원 출결 임의 업데이트 
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(curDate);

		// 난수 생성
		Set<Integer> set = new HashSet<>();

		int ran = 0; // 랜던값변수
		Random r = new Random();
		while (true) {
			if (set.size() >= 2968) {
				break;
			}
			ran = r.nextInt(2968) + 1;
			set.add(ran);
		}
		Map<Integer, Boolean> map = new HashMap<>();
		Iterator<Integer> iter = set.iterator();
		while (iter.hasNext()) {
			map.put(iter.next(), false);
		}

		Set<Integer> keySet = map.keySet();
		Iterator<Integer> keyIter = keySet.iterator();
		List<Integer> numList = new ArrayList<>();
		boolean isUsed = false;
		int num = 0;

		outerloop: while (keyIter.hasNext()) {
			while (true) {
				num = r.nextInt(2968) + 1;
				isUsed = map.get(num);
				if (isUsed == true) {
					continue;
				}
				map.put(num, true); // true 사용함
				numList.add(num);
				if (numList.size() == 2968) {
					break outerloop;
				}
			}
		}
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		String eid = "";
		String eidAndWorkNote = "";
		// 직원 상태 임의 편성
		for (int i = 0; i < numList.size(); i++) {
			if (i < 100) {
				eidAndWorkNote = "abse";
			} else if (i < 330) {
				eidAndWorkNote = "late";
			} else if (i < 553) {
				eidAndWorkNote = "va";
			} else {
				eidAndWorkNote = "nor";
			}
			
		// 실제 사용될 시  메인 페이지와 함께 전직원 데이터 non으로 업데이트
			
//			for (int i = 0; i < numList.size(); i++) {
//				eidAndWorkNote = "non";
//				}
				
			EmployeeVo empVo = eidList.get(numList.get(i) - 1);
			eid = empVo.getEid();
			obj.put(eid, eidAndWorkNote);
		}
		array.add(obj);
//		obj.put("FA000001", "nor");
//		array.add(obj);
//		for(Object o: array){ //출력
//			if ( o instanceof JSONObject ) {
//				System.out.println(o);
//		    }
//		    
//		}//for  

		String str = array.toJSONString();
		return str;
    }//attendByDaily
}
