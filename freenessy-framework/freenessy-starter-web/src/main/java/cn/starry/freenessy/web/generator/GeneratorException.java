package cn.starry.freenessy.web.generator;

import cn.starry.freenessy.base.resp.ResultCode;
import cn.starry.freenessy.base.resp.ResultCodeService;

public interface GeneratorException extends ResultCodeService {

    ResultCode rootDirNotExist = ResultCode.create("0001", "项目基本路径[cn/starry/freenessy]不完整, 请创建");
}
