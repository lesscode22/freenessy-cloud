package cn.starry.freenessy.base.exception;

import cn.hutool.core.util.StrUtil;
import cn.starry.freenessy.base.resp.GlobalErrorCodeService;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final GlobalErrorCodeService errorCode;

    public BizException(GlobalErrorCodeService errorCode, Object... params) {
        this.errorCode = fillMessage(errorCode, params);
    }

    private GlobalErrorCodeService fillMessage(GlobalErrorCodeService rawError, Object... params) {
        return new GlobalErrorCodeService() {
            @Override
            public String getCode() {
                return rawError.getCode();
            }

            @Override
            public String getMessage() {
                return StrUtil.format(rawError.getMessage(), params);
            }
        };
    }
}
