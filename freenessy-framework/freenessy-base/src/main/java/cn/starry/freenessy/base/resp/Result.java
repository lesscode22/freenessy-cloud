package cn.starry.freenessy.base.resp;

import cn.hutool.core.convert.Convert;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public final class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务状态码
     */
    private String code;
    /**
     * 返回提示信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;
    /**
     * 分页信息
     */
    private CommonPage page;

    private Result() {}

    /**
     * 调用成功
     */
    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(DefaultErrorEnum.SUCCESS, data);
    }

    /**
     * 调用失败
     */
    public static <T> Result<T> error() {
        return error(DefaultErrorEnum.UNKNOWN_FAIL);
    }

    public static <T> Result<T> error(GlobalErrorCodeService errorCode) {
        return restResult(errorCode, null);
    }

    public static <T> Result<T> judge(boolean status) {
        if (status) {
            return ok();
        } else {
            return error();
        }
    }

    public <O> O convertDataTo(Class<O> clazz) {
        return Convert.convert(clazz, this.getData());
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
        map.put("page", page);
        return map;
    }

    private static <T> Result<T> restResult(GlobalErrorCodeService resp, T data) {
        Result<T> result = new Result<>();
        result.setCode(resp.getCode());
        result.setData(data);
        result.setMessage(resp.getMessage());
        return result;
    }
}
