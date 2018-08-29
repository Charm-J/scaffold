package com.dj.scaffold.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public JsonUtil() {
    }

    public static String serialize(Object value) {
        try {
            return JSONObject.toJSONString(value);
        } catch (Exception var2) {
            logger.error("json异常 {}", var2);
            return null;
        }
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return JSONObject.parseObject(json, clazz);
        } catch (Exception var3) {
            logger.error("json异常 {} json= {}", json, var3);
            return null;
        }
    }
}