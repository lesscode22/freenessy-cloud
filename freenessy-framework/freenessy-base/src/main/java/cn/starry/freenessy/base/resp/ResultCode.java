package cn.starry.freenessy.base.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultCode {

    private String code;
    private String message;

    public static final ResultCode SUCCESS = ResultCode.create("0000", "ok");
    public static final ResultCode FAIL = ResultCode.create("9999", "服务异常, 请稍后再试");

    public static ResultCode create(String code, String message) {
        return new ResultCode(code, message);
    }
}
