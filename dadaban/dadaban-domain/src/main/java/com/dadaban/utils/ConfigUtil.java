package com.dadaban.utils;


/**
 * Created by jrose on 8/13/14.
 */
public class ConfigUtil {

    private static ConfigInfo configInfo;
    static {
        ConfigInfo bean = Beans.getBean(ConfigInfo.class);
        configInfo = bean;
    }

    public static String getFileDir () {
        return configInfo.fileDir;
    }
}
