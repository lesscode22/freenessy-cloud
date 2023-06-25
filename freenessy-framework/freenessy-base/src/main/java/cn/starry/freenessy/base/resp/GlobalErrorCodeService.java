package cn.starry.freenessy.base.resp;

/**
 * 错误码定义: 系统标识 + 错误类型 + 功能模块 + 错误编号(001、002、...)
 *
 * 系统标识:
 *      T - 本系统
 * 错误类型:
 *      S: 系统基础功能异常, 比如短信、oss、限流等
 *      G: 网关异常, B: 业务错误,  C: 缓存错误,  M: 中间件异常,  D: 数据库错误,  F: 文件IO异常,  N: 网络错误
 *
 * 功能模块:
 *      0xx: 系统公共定义
 *      1xx: 业务各功能模块
 */
public interface GlobalErrorCodeService {

    String getCode();

    String getMessage();

}
