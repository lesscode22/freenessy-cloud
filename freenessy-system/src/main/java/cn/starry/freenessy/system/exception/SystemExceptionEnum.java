package cn.starry.freenessy.system.exception;

import cn.starry.freenessy.base.resp.GlobalErrorCodeService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemExceptionEnum implements GlobalErrorCodeService {

        USER_NOT_EXIST("TB101001", "该用户不存在"),

        DEPT_NOT_EXIST("TB102001", "该部门不存在");

        private final String code;
        private final String message;
}
