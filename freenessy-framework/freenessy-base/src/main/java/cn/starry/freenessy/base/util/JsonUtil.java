package cn.starry.freenessy.base.util;

import cn.starry.freenessy.base.helper.JacksonOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        JacksonOptions.init(OBJECT_MAPPER);
    }

    private JsonUtil() {}

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    public static String toJson(Object obj) {
        try {
            
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Json异常: ", e);
        }
        return "";
    }

    /**
     * 将json字符串格式化后输出
     */
    public static String toJsonFormat(Object obj) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Json异常: ", e);
        }
        return "";
    }

    public static String getNodeValue(String text, String key) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(text);
            return jsonNode.findValue(key).asText();
        } catch (JsonProcessingException e) {
            log.error("Json异常: ", e);
        }
        return null;
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (JsonProcessingException e) {
            log.error("Json异常: ", e);
        }
        return null;
    }

    /**
     * json字符串转List
     * @param text  json字符串
     * @param clazz 集中中元素类型
     */
    public static <T> List<T> fromJsonToList(String text, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(text, getListType(clazz));
        } catch (JsonProcessingException e) {
            log.error("Json异常: ", e);
        }
        return new ArrayList<>();
    }

    public static List<Map<String, Object>> fromJsonToList(String text) {
        try {
            return OBJECT_MAPPER.readValue(text, getListType(getMapType()));
        } catch (JsonProcessingException e) {
            log.error("Json异常: ", e);
        }
        return new ArrayList<>();
    }

    public static Map<String, Object> fromJsonToMap(String text) {
        try {
            return OBJECT_MAPPER.readValue(text, getMapType());
        } catch (JsonProcessingException e) {
            log.error("Json异常: ", e);
        }
        return new HashMap<>();
    }

    /**
     * map转bean
     */
    public static <T> T toBean(Map<Object, Object> map, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(map, clazz);
    }

    private static JavaType getListType(Class<?> elementClazz) {
        return OBJECT_MAPPER.getTypeFactory().constructCollectionLikeType(ArrayList.class, elementClazz);
    }

    private static JavaType getListType(JavaType javaType) {
        return OBJECT_MAPPER.getTypeFactory().constructCollectionLikeType(ArrayList.class, javaType);
    }

    private static JavaType getMapType() {
        return OBJECT_MAPPER.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Object.class);
    }
}
