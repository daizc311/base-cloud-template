package cn.dreamccc.common.model;

import lombok.Getter;

public enum DRespCodeEnum {
    UNKNOWN_ERROR(-1, "服务器异常，请联系管理员"),
    SUCCESS(0, "成功"),
    SERVER_ERROR(1, "服务器开小差了，请稍后再试...");


    @Getter
    private final int code;
    @Getter
    private final String codeMessage;

    DRespCodeEnum(int code, String codeMessage) {
        this.code = code;
        this.codeMessage = codeMessage;
    }
}
