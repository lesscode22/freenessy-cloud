package cn.starry.freenessy.base.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.starry.freenessy.base.resp.ResultCode;

import java.util.Collection;

public class Asserts {

    public static void isTrue(boolean expression, ResultCode resultCode, Object... params) {
        if (!expression) {
            alert(resultCode, params);
        }
    }

    public static void isFalse(boolean expression, ResultCode resultCode, Object... params) {
        if (expression) {
            alert(resultCode, params);
        }
    }

    // =========================== Object

    public static void isNull(Object obj, ResultCode resultCode, Object... params) {
        if (obj != null) {
            alert(resultCode, params);
        }
    }

    public static void notNull(Object obj, ResultCode resultCode, Object... params) {
        if (obj == null) {
            alert(resultCode, params);
        }
    }

    // =========================== String

    public static void isEmpty(String text, ResultCode resultCode, Object... params) {
        if (StrUtil.isNotBlank(text)) {
            alert(resultCode, params);
        }
    }

    public static void notEmpty(String text, ResultCode resultCode, Object... params) {
        if (StrUtil.isBlank(text)) {
            alert(resultCode, params);
        }
    }

    // ============================ Collection

    public static void notEmpty(Collection<?> collection, ResultCode resultCode, Object... params) {
        if (CollUtil.isEmpty(collection)) {
            alert(resultCode, params);
        }
    }

    public static void isEmpty(Collection<?> collection, ResultCode resultCode, Object... params) {
        if (CollUtil.isNotEmpty(collection)) {
            alert(resultCode, params);
        }
    }

    public static void noNullElements(Collection<?> collection, ResultCode resultCode, Object... params) {
        if (collection == null) {
            return;
        }
        for (Object element : collection) {
            if (element == null) alert(resultCode, params);
        }
    }

    private static void alert(ResultCode resultCode, Object... params) {
        throw new BizException(resultCode, params);
    }
}
