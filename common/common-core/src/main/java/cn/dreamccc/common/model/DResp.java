package cn.dreamccc.common.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("返回实体")
public class DResp<T> {

    @ApiModelProperty(value = "状态码",notes = "0:成功,-1:bugs,others:错误")
    private int code;

    @ApiModelProperty(value = "数据",notes = "jsonObject")
    private T data;

    @ApiModelProperty(value = "操作信息")
    private String msg;

    @ApiModelProperty(value = "用于DEBUG的对象",hidden = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object debug;

    public static <DATA> DResp<DATA> success() {
        return new DResp<DATA>().setEnum(DRespCodeEnum.SUCCESS);
    }

    public static <DATA> DResp<DATA> success(String message) {
        return DResp.<DATA>success().setMsg(message);
    }

    public static <DATA> DResp<DATA> data(DATA data) {
        return DResp.<DATA>success().setData(data);
    }

    public static <DATA> DResp<DATA> data(DATA data, String message) {
        return DResp.<DATA>success(message).setData(data);
    }


    public static <DATA> DResp<DATA> failed() {
        return new DResp<DATA>().setEnum(DRespCodeEnum.SERVER_ERROR);
    }

    public static <DATA> DResp<DATA> failed(String message) {
        return DResp.<DATA>failed().setMsg(message);
    }

    public int getCode() {
        return code;
    }

    public DResp<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public DResp<T> setData(T data) {
        this.data = data;
        return this;

    }

    public String getMsg() {
        return msg;
    }

    public DResp<T> setMsg(String msg) {
        this.msg = msg;
        return this;

    }

    public Object getDebug() {
        return debug;
    }

    public DResp<T> setDebug(Object debug) {
        this.debug = debug;
        return this;
    }

    public DResp<T> setEnum(DRespCodeEnum codeEnum) {

        return this.setCode(codeEnum.getCode()).setMsg(codeEnum.getCodeMessage());
    }

}
