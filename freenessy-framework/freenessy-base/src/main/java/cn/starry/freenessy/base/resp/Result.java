package cn.starry.freenessy.base.resp;

import cn.hutool.core.convert.Convert;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

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
        return restResult(ResultCode.SUCCESS, data, null);
    }

    /**
     * 调用失败
     */
    public static <T> Result<T> error() {
        return error(ResultCode.FAIL);
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        return restResult(resultCode, null, null);
    }

    public static <T> Result<T> error(String message) {
        ResultCode fail = ResultCode.FAIL;
        fail.setMessage(message);
        return restResult(fail, null, null);
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

    private static <T> Result<T> restResult(ResultCode resp, T data, CommonPage page) {
        Result<T> result = new Result<>();
        result.setCode(resp.getCode());
        result.setData(data);
        result.setMessage(resp.getMessage());
        result.setPage(page);
        return result;
    }
}
