package cn.starry.freenessy.base.resp;

import cn.starry.freenessy.base.exception.BizException;

public interface ResultCodeService {

    ResultCode success = ResultCode.SUCCESS;
    ResultCode fail = ResultCode.FAIL;

    ResultCode paramInvalid = ResultCode.create("1000", "参数异常");

    default void throwException(ResultCode resultCode) {
        throw new BizException(resultCode);
    }

    default void throwParamException() {
        throw new BizException(paramInvalid);
    }
}
