package com.mmall.common;

public enum PayPlatformEnum {

    ALIPAY(1, "支付宝"),
    WEIXINPAY(2, "微信支付");

    PayPlatformEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    private String value;
    private int code;

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static PayPlatformEnum codeOf(int code) {
        for (PayPlatformEnum payPlatformEnum : values()) {
            if (payPlatformEnum.getCode() == code) {
                return payPlatformEnum;
            }
        }
        throw new RuntimeException("么有找到对应的枚举");
    }
}