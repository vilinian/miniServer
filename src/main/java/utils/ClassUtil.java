package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : ddv
 * @since : 2019/5/17 下午3:29
 */

public class ClassUtil {

    /**
     * 注入对象的普通字段,具体哪些需要注入的字段与字段值,使用者自己传递,并且下标需要对应
     *
     * @param object
     * @param fieldList
     * @param values
     */
    public static void insertFields(Object object, List<Field> fieldList, List<String> values) {
        insertValue(object, fieldList, values);
    }

    private static void insertValue(Object object, List<Field> fieldList, List<String> values) {
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            String value = values.get(i);

            field.setAccessible(true);
            Class<?> type = field.getType();

            try {
                field.set(object, JodaUtil.convertFromString(type, value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入字段 按照class定义的字段顺序注入,注意值的传递顺序
     *
     * @param object
     * @param values
     */
    public static void insertDefaultFields(Object object, List<String> values) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        insertValue(object, Arrays.asList(declaredFields), values);
    }

    public static <T> T getFieldByName(Object object, String fieldName, Class<T> type) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getName().equals(fieldName)) {
                try {
                    return (T)field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Method getMethodByAnnotation(Object object, Class<? extends Annotation> annotation) {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(annotation)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    public static Field getFieldByAnnotation(Object object, Class<? extends Annotation> annotation) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(annotation)) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

    public static List<Field> getFieldsByAnnotation(Object object, Class<? extends Annotation> annotation) {
        List<Field> list = new ArrayList<>();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(annotation)) {
                field.setAccessible(true);
                list.add(field);
            }
        }
        return list;
    }

    public static <T> T createProcessor(Class<T> clazz, Map<Object, Object> params, int constructorParamCount) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == constructorParamCount) {
                try {
                    return (T)constructor.newInstance(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (constructorParamCount == 0) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
