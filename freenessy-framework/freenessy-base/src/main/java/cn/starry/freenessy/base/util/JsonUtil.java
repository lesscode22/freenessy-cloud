package cn.starry.freenessy.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModules(new ParameterNamesModule(), new Jdk8Module())
                // 反序列化遇到未知字段不报错
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 序列化空对象时不报错
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Date类型格式
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.setDateFormat(df);

        // LocalDate, LocalDateTime 格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        OBJECT_MAPPER.registerModule(javaTimeModule);
    }

    private JsonUtil() {}

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    public static String toJson(Object obj) {
        try {
            
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("[JsonUtil][toJson]: ", e);
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
            log.error("[JsonUtil][toJsonFormat]: ", e);
        }
        return "";
    }

    public static String getNodeValue(String text, String key) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(text);
            return jsonNode.findValue(key).asText();
        } catch (JsonProcessingException e) {
            log.error("[JsonUtil][getNodeValue]: ", e);
        }
        return null;
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (JsonProcessingException e) {
            log.error("[JsonUtil][fromJson]: ", e);
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
            log.error("[JsonUtil][fromJsonToList]: ", e);
        }
        return new ArrayList<>();
    }

    public static List<Map<String, Object>> fromJsonToList(String text) {
        try {
            return OBJECT_MAPPER.readValue(text, getListType(getMapType()));
        } catch (JsonProcessingException e) {
            log.error("[JsonUtil][fromJsonToListMap]: ", e);
        }
        return new ArrayList<>();
    }

    public static Map<String, Object> fromJsonToMap(String text) {
        try {
            return OBJECT_MAPPER.readValue(text, getMapType());
        } catch (JsonProcessingException e) {
            log.error("[JsonUtil][fromJsonToMap]: ", e);
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
