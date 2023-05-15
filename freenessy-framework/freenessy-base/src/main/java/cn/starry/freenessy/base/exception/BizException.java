package cn.starry.freenessy.base.exception;

import cn.hutool.core.util.StrUtil;
import cn.starry.freenessy.base.resp.ResultCode;
import lombok.Getter;

public class BizException extends RuntimeException {

    @Getter
    private final ResultCode resultCode;

    public BizException(ResultCode resultCode, Object... param) {
        resultCode.setMessage(StrUtil.format(resultCode.getMessage(), param));
        this.resultCode = resultCode;
    }
}
