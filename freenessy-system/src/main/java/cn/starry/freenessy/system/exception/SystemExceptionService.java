package cn.starry.freenessy.system.exception;

import cn.starry.freenessy.base.resp.ResultCode;
import cn.starry.freenessy.base.resp.ResultCodeService;

public interface SystemExceptionService extends ResultCodeService {

        ResultCode userNotExist = ResultCode.create("0002", "该用户不存在");
}
