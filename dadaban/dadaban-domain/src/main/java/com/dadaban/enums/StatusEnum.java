package com.dadaban.enums;

/**
 * Created by jrose on 8/2/14.
 */
public enum StatusEnum {
    valid(1, "有效"),
    invalid(0, "无效");

    private Integer code;

    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
