package cn.dlut.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


public class JsonUtil{
	
	private static JsonUtil jsonUtil;
	
	public static JsonUtil getInstance() {
		if (null == jsonUtil) {
			jsonUtil = new JsonUtil();
		}
		return jsonUtil;
	}
	

    /** *//**
     * get JSON object from string
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    public static Object getObject4JsonString(String jsonString,Class<?> pojoCalss){
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject( jsonString ); 
        pojo = JSONObject.toBean(jsonObject,pojoCalss);
        return pojo;
    }
    
    
    
    /** *//**
     * get map from json HASH, nesting support
     * @param jsonString
     * @return
     */
    public static Map<String, Object> getMap4Json(String jsonString){
        JSONObject jsonObject = JSONObject.fromObject( jsonString ); 
        Iterator<?> keyIter = jsonObject.keys();
        String key;
        Object value;
        Map<String, Object> valueMap = new HashMap<String, Object>();

        while( keyIter.hasNext())
        {
            key = (String)keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }
        
        return valueMap;
    }
    
    
    /** *//**
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
        
    }
    
    
    /** *//**
     * @param jsonString
     * @param pojoClass
     * @return
     */
    public static List<Object> getList4Json(String jsonString, Class<?> pojoClass){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;
        
        List<Object> list = new ArrayList<Object>();
        for ( int i = 0 ; i<jsonArray.size(); i++){
            
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject,pojoClass);
            list.add(pojoValue);
            
        }
        return list;

    }
    
    /** *//**
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            stringArray[i] = jsonArray.getString(i);
            
        }
        
        return stringArray;
    }
    
    /** *//**
     * @param jsonString
     * @return
     */
    public static Long[] getLongArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            longArray[i] = jsonArray.getLong(i);
            
        }
        return longArray;
    }
    
    /** *//**
     * @param jsonString
     * @return
     */
    public static Integer[] getIntegerArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            integerArray[i] = jsonArray.getInt(i);
            
        }
        return integerArray;
    }
    
    /** *//**
     * @param jsonString
     * @return
     */
    public static Date[] getDateArray4Json(String jsonString,String DataFormat){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Date[] dateArray = new Date[jsonArray.size()];
        String dateString;
        Date date;
        
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            dateString = jsonArray.getString(i);
            date = DateUtil.string2Date(dateString, DataFormat);
            dateArray[i] = date;
            
        }
        return dateArray;
    }
    
    /** *//**
     * @param jsonString
     * @return
     */
    public static Double[] getDoubleArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            doubleArray[i] = jsonArray.getDouble(i);
            
        }
        return doubleArray;
    }
    
    
    /** *//**
     * get json string from object
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj){
        
        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
        
    }
   
    /** *//**
     * get json string from object, add time stamp
     * @param javaObj
     * @param dataFormat
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj , String dataFormat){
        
        JSONObject json;
        JsonConfig jsonConfig = configJson(dataFormat);
        json = JSONObject.fromObject(javaObj,jsonConfig);
        return json.toString();
        
        
    }
    
    /** *//**
     * @param args
     */
    public static void main(String[] args){
        

    }
    
    /** *//**
     * JSON Time resolution tool
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(new String[]{""});   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;   
        } 
    
    /** *//**
     * 
     * @param excludes
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String[] excludes,   
            String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(excludes);   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;   
        }

}