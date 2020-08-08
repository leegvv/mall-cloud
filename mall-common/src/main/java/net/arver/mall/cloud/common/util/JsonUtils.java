package net.arver.mall.cloud.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

/**
 * json工具类.
 * @author arver
 */
public class JsonUtils {

    /**
     * 日志.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * mapper.
     */
    public static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转为json.
     * @param obj 对象
     * @return json字符串
     */
    @Nullable
    public static String toJson(final Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (final JsonProcessingException e) {
            LOGGER.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    /**
     * 将json转成bean.
     * @param json json字符串
     * @param clazz 类型
     * @param <T> 泛型
     * @return bean对象
     */
    @Nullable
    public static <T> T toBean(final String json, final Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (final JsonProcessingException e) {
            LOGGER.error("json解析出错：" + json, e);
            return null;
        }
    }

    /**
     * 将json转成bean.
     * @param json json字符串
     * @param typeReference TypeReference
     * @param <T> 泛型
     * @return bean对象
     */
    @Nullable
    public static <T> T toBean(final String json, final TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (final JsonProcessingException e) {
            LOGGER.error("json解析出错：" + json, e);
            return null;
        }
    }
}
