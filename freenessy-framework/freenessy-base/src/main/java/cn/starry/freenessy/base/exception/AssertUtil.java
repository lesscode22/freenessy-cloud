package cn.starry.freenessy.base.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.starry.freenessy.base.resp.GlobalErrorCodeService;

import java.util.Collection;

public class AssertUtil {

    public static void isTrue(boolean expression, GlobalErrorCodeService errorCode, Object... params) {
        if (!expression) {
            alert(errorCode, params);
        }
    }

    public static void isFalse(boolean expression, GlobalErrorCodeService errorCode, Object... params) {
        if (expression) {
            alert(errorCode, params);
        }
    }

    // =========================== Object

    public static void isNull(Object obj, GlobalErrorCodeService errorCode, Object... params) {
        if (obj != null) {
            alert(errorCode, params);
        }
    }

    public static void notNull(Object obj, GlobalErrorCodeService errorCode, Object... params) {
        if (obj == null) {
            alert(errorCode, params);
        }
    }

    // =========================== String

    public static void isBlank(String text, GlobalErrorCodeService errorCode, Object... params) {
        if (StrUtil.isNotBlank(text)) {
            alert(errorCode, params);
        }
    }

    public static void notBlank(String text, GlobalErrorCodeService errorCode, Object... params) {
        if (StrUtil.isBlank(text)) {
            alert(errorCode, params);
        }
    }

    // ============================ Collection

    public static void notEmpty(Collection<?> collection, GlobalErrorCodeService errorCode, Object... params) {
        if (CollUtil.isEmpty(collection)) {
            alert(errorCode, params);
        }
    }

    public static void isEmpty(Collection<?> collection, GlobalErrorCodeService errorCode, Object... params) {
        if (CollUtil.isNotEmpty(collection)) {
            alert(errorCode, params);
        }
    }

    public static void noNullElements(Collection<?> collection, GlobalErrorCodeService errorCode, Object... params) {
        if (collection == null) {
            return;
        }
        for (Object element : collection) {
            if (element == null) alert(errorCode, params);
        }
    }

    private static void alert(GlobalErrorCodeService errorCode, Object... params) {
        throw new BizException(errorCode, params);
    }
}
