package cn.starry.freenessy.base.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 默认异常码
 * 
 * @author: TJ
 * @date:  2022-06-19
 **/
@Getter
@AllArgsConstructor
public enum DefaultErrorEnum implements GlobalErrorCodeService {

    SUCCESS("0", "ok"),
    UNKNOWN_FAIL("-1", "服务异常, 请稍后再试"),

    MESSAGE_NOT_READABLE("TB001001", "消息体读取异常"),
    REQUEST_TYPE_NOT_SUPPORT("TB001002", "请求方式有误: GET/POST"),
    INVALID_URL("TB001003", "请求地址不存在"),
    REQUEST_PARAM_COVERT_FAIL("TB001004", "参数格式绑定异常"),
    // 通用校验异常码, 若未单独定义其他异常码, 则参数校验都返回此异常码, 但提示信息由业务自定义
    INVALID_PARAM("TB001999", "参数值校验异常"),


    BASE_PACKET_NOT_EXIST("TB002001", "项目基本包结构不完整: [cn/starry/freenessy]"),
    FILE_NOT_EXIST("TB002002", "文件不存在"),
    ;

    private final String code;
    private final String message;
}
