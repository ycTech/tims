package com.tims.facade.enums;

/**
 * 状态
 * @author: yecb
 * @create: 2017/12/8  18:29
 */
public enum StatusEnum {

    VALID("有效"),

    INVALID("无效");

    private String value;

    private StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
