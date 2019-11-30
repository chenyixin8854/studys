package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: parent
 * @description: 对象属性复制工具
 * @author: chenyixin
 * @create: 2019-06-12 10:30
 **/
@Slf4j
public class BeanUtil {

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

}
