package com.cherbini.faceattendance.commonUtils;

import com.cherbini.faceattendance.model.Dept;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.Date;

public class JsonUtils {
    public static  <T> String beanToJSONStr(T t) {
        String json = "";
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,
                new JsonDateValueProcessor());
        if(t!=null){
            json = JSONObject.fromObject(t, jsonConfig).toString();
        }else{
            json="[]";
        }
        return json;
    }
}
