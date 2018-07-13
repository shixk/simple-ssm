package com.imooc.vat.util;

import com.imooc.vat.service.ThrowingConsumer;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created on huangtongshuan
 * 10:18 04/11/2018.
 * 数据表扩展module, map转换类.
 */
public class ReflectFieldUtil {

    /**
     * catch field map
     */
    private final static Map<Class, Map<String, Field>> fieldsCaches = new HashMap<>();

    /**
     * 第一次反射，缓存需要转换的extend module类全部属性
     */
    private static void prepare(Class<?> cz) throws Exception {

        if (cz == null) {
            throw new IllegalArgumentException("null");
        }

        if (fieldsCaches.containsKey(cz)) {
            return;
        }

        Map<String, Field> tupleMap = new HashMap<>();

        // cz.getDeclaredField 异常处理
        Arrays.stream(cz.getDeclaredFields()).forEach((ThrowingConsumer<Field>) p -> {

            tupleMap.put(p.getName(), cz.getDeclaredField(p.getName()));

        });

        fieldsCaches.putIfAbsent(cz, tupleMap);

    }

    /**
     * 数据库key，value查询结果转换为extendModule
     */
    public static <T> void setObjectValue(Object o, Map<String, String> map) throws Exception {

        if (o == null) {
            throw new IllegalArgumentException("null");
        }

        prepare(o.getClass());

        Map<String, Field> tupleMap = fieldsCaches.get(o.getClass());

        map.forEach((key, value) -> {
            try {

                Field field = tupleMap.get(key);

                field.setAccessible(true);

                field.set(o, reflectTypeCover(value, field.getType()));

                field.setAccessible(false);

            } catch (Exception e) {
                // todo log 看业务是否需要throw
            }
        });

    }

    /**
     * extendModule对象，转换为数据库存书map
     */
    public static Map<String, String> getObjectMap(Object o) throws Exception {

        if (o == null) {
            // throw new IllegalArgumentException("null");
            return null;
        }

        Map<String, String> extendMap = new HashMap<>();

        prepare(o.getClass());

        Map<String, Field> tupleMap = fieldsCaches.get(o.getClass());

        tupleMap.forEach((key, field) -> {

            String value = null;
            try {
                field.setAccessible(true);

                value = String.valueOf(field.get(o));

                field.setAccessible(false);

            } catch (IllegalAccessException e) {
                //todo log 看业务要修是否需要throw
            }

            if (StringUtils.isNotEmpty(value)) {

                extendMap.put(key, value);
            }

        });

        return extendMap;

    }

    /**
     * 反射基本类型转换
     */
    public static Object reflectTypeCover(String value, Class<?> type) {

        if (type.equals(String.class)) {
            return value;
        }
        if (type.equals(byte.class) || type.equals(Byte.class)) {
            return Byte.valueOf(value);
        }
        if (type.equals(short.class) || type.equals(Short.class)) {
            return Short.valueOf(value);
        }
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return Integer.valueOf(value);
        }
        if (type.equals(long.class) || type.equals(Long.class)) {
            return Long.valueOf(value);
        }
        if (type.equals(float.class) || type.equals(Float.class)) {
            return Float.valueOf(value);
        }
        if (type.equals(double.class) || type.equals(Double.class)) {
            return Double.valueOf(value);
        }
        if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return Boolean.valueOf(value);
        }
        return value;
    }


}
