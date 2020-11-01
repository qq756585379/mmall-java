package com.mmall.util;

import avro.shaded.com.google.common.collect.Lists;
import com.mmall.pojo.TestPojo;
import com.mmall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JsonUtil {

    private final static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    //ObjectMapper是jackson提供的
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    //对象转 jsonString
    public static <T> String obj2String(T obj) {
        if (obj == null) return null;
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    //对象转Pretty jsonString
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to String error", e);
            return null;
        }
    }

    //字符串转对象
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }

    public static void main(String[] args) {
        TestPojo testPojo = new TestPojo();
        testPojo.setName("yj");
        testPojo.setId(666);
        String testPojoJson = JsonUtil.obj2String(testPojo);
        log.info("testPojoJson:{}", testPojoJson);

        //{"name":"yj","id":666}
        String json = "{\"name\":\"yj\",\"color\":\"blue\",\"id\":666}";
        TestPojo testPojoObject = JsonUtil.string2Obj(json, TestPojo.class);

        User user = new User();
        user.setId(2);
        user.setEmail("756585379@qq.com");
        user.setCreateTime(new Date());
        String userJson = JsonUtil.obj2String(user);
        String userJsonPretty = JsonUtil.obj2StringPretty(user);
        log.info("userJson:{}", userJson);
        log.info("userJsonPretty:{}", userJsonPretty);

        User user2 = JsonUtil.string2Obj(userJson, User.class);

        List<User> userList = Lists.newArrayList();
        userList.add(user);
        userList.add(user2);
        String userListStr = JsonUtil.obj2StringPretty(userList);
        log.info("==================");
        log.info(userListStr);

        List<User> userListObj1 = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {
        });
        List<User> userListObj2 = JsonUtil.string2Obj(userListStr, List.class, User.class);

        log.info("end");
        System.out.println("end");
    }
}
