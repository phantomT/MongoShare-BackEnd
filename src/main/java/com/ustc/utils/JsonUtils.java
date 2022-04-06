package com.ustc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Json转换工具
 *
 * @author 田宝宁
 */
@Component
public class JsonUtils {
    private static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
    }

    /**
     * 将对象转换为Json字符串
     *
     * @param data 数据
     * @return 对应的Json字符串
     */
    public static String objectToJson(Object data) throws JsonProcessingException {
        return MAPPER.writeValueAsString(data);
    }

    /**
     * 将json结果转换为对象
     *
     * @param jsonData json数据
     * @param beanType 对象类型
     * @return 返回对象
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) throws JsonProcessingException {
        return MAPPER.readValue(jsonData, beanType);
    }

    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) throws JsonProcessingException {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        return MAPPER.readValue(jsonData, javaType);
    }
}
