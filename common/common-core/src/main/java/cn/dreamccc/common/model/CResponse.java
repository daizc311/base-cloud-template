package cn.dreamccc.common.model;


public class CResponse<T> {

    private int code;

    private T data;

    private String msg;

    private Object debug;

    public static <DATA> CResponse<DATA> success() {
        return new CResponse<DATA>().setEnum(CResponseCodeEnum.SUCCESS);
    }

    public static <DATA> CResponse<DATA> success(String message) {
        return CResponse.<DATA>success().setMsg(message);
    }

    public static <DATA> CResponse<DATA> data(DATA data) {
        return CResponse.<DATA>success().setData(data);
    }

    public static <DATA> CResponse<DATA> data(DATA data, String message) {
        return CResponse.<DATA>success(message).setData(data);
    }


    public static <DATA> CResponse<DATA> failed() {
        return new CResponse<DATA>().setEnum(CResponseCodeEnum.SERVER_ERROR);
    }

    public static <DATA> CResponse<DATA> failed(String message) {
        return CResponse.<DATA>failed().setMsg(message);
    }

    public int getCode() {
        return code;
    }

    public CResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public CResponse<T> setData(T data) {
        this.data = data;
        return this;

    }

    public String getMsg() {
        return msg;
    }

    public CResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;

    }

    public Object getDebug() {
        return debug;
    }

    public CResponse<T> setDebug(Object debug) {
        this.debug = debug;
        return this;
    }

    public CResponse<T> setEnum(CResponseCodeEnum codeEnum) {

        return this.setCode(codeEnum.getCode()).setMsg(codeEnum.getCodeMessage());
    }

}
