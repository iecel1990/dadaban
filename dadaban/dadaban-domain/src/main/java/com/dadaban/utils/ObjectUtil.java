package com.dadaban.utils;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by jrose on 14-6-9.
 */
public class ObjectUtil {
    public  static boolean isBlank(Object obj) {
        if (isEmpty(obj)) {
            return true;
        }
        return org.apache.commons.lang3.StringUtils.isBlank(obj.toString());
    }

    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean hasEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equals(Integer num1, Integer num2) {
        if (isEmpty(num1) || isEmpty(num2)) {
            return false;
        }
        return num1.equals(num2);
    }

    public static String defaultData (Object data, Object defaultData) {
        if (isNotBlank(data)) {
            return data.toString();
        }
        if (isNotBlank(defaultData)) {
            return defaultData.toString();
        }
        throw new RuntimeException("系统异常");
    }

    public static boolean notHasEmpty(Object... objects) {
        return !hasEmpty(objects);
    }

    public static Integer getValue(Integer val, Integer def) {
        return val == null ? def : val;
    }

    public static String getValue(String val, String def) {
        return StringUtils.isBlank(val) ? def : val;
    }

    public static BigDecimal getValue(BigDecimal val, BigDecimal def) {
        return val == null ? def : val;
    }
}
