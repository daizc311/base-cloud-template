package cn.dreamccc.common.model;

import lombok.Getter;

public enum CResponseCodeEnum {
    UNKNOWN_ERROR(-1, "服务器开小差了，请稍后再试..."),
    SUCCESS(0, "成功"),
    SERVER_ERROR(1, "请求失败，请联系管理员");


    @Getter
    private int code;
    @Getter
    private String codeMessage;

    CResponseCodeEnum(int code, String codeMessage) {
        this.code = code;
        this.codeMessage = codeMessage;
    }
}
