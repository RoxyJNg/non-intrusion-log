package com.null01.nonintrusivelog.resolver;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.null01.nonintrusivelog.entity.JsonConfiguration;
import org.springframework.core.io.ClassPathResource;

import java.io.*;


/**
 * json配置文件解析器
 */
public class JsonResolver {
    private static JsonConfiguration jsonConfiguration;
    private static JsonConfiguration getConfig() throws Exception {
        ClassPathResource resource = new ClassPathResource("/nil.json");

        File file = resource.getFile();
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String jsonStr = sb.toString();
        JsonConfiguration configuration = JSONObject.parseObject(jsonStr,new TypeReference<JsonConfiguration>(){});
        jsonConfiguration = configuration;
        return configuration;
    }

    public static String getBefore() throws Exception{
        getConfig();
        StringBuffer before = new StringBuffer();
        if(jsonConfiguration.getBefore()!=null && jsonConfiguration.getBefore().size()>0){
            for (String clazz:jsonConfiguration.getBefore()){
                before.append("* ");
                before.append(clazz);
                before.append("(..) || ");
            }
            return before.substring(0,before.length()-4);
        }
        return null;
    }
}
