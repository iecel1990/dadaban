package com.dadaban.utils;


import com.dadaban.exception.ValidationException;

/**
 * Created by jrose on 14-7-18.
 */
public class Assert {
    public static void isTrue(boolean ex, String message) {
        if (!ex) {
            throw new ValidationException(message);
        }
    }

    public static void isTrue(boolean ex) {
        if (!ex) {
            throw new ValidationException();
        }
    }
    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new ValidationException(obj + "不能为空");
        }
    }
    public static void checkNotNull(Object obj, String message) {
        if (obj == null) {
            throw new ValidationException(message);
        }
    }
}
