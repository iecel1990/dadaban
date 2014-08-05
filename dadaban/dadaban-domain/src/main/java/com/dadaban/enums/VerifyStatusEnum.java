package com.dadaban.enums;

/**
 * Created by jrose on 8/2/14.
 */
public enum VerifyStatusEnum {
    NO_VERIFY(0, "未验证"),
    VERIFYED(1, "已验证");

    private Integer code;

    private String desc;

    VerifyStatusEnum(Integer code, String desc) {
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
