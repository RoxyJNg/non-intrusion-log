package com.null01.nonintrusivelog.resolver;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.null01.nonintrusivelog.entity.JsonConfiguration;
import com.null01.nonintrusivelog.entity.Signature;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;


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

    public static List<Signature> getBefore() throws Exception{
        return getConfig().getBefore();
    }
}
