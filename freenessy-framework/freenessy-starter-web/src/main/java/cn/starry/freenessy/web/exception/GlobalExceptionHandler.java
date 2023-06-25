package cn.starry.freenessy.web.exception;

import cn.hutool.core.util.ReflectUtil;
import cn.starry.freenessy.base.exception.BizException;
import cn.starry.freenessy.base.resp.DefaultErrorEnum;
import cn.starry.freenessy.base.resp.GlobalErrorCodeService;
import cn.starry.freenessy.base.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<?> error(Exception e) {
        log.error("[GlobalExceptionHandler]服务异常: ", e);
        return Result.error();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> error(HttpMessageNotReadableException e) {
        log.error("[GlobalExceptionHandler]消息体读取异常: ", e);
        return Result.error(DefaultErrorEnum.MESSAGE_NOT_READABLE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> error(HttpRequestMethodNotSupportedException e) {
        log.error("[GlobalExceptionHandler]请求方式异常: ", e);
        return Result.error(DefaultErrorEnum.REQUEST_TYPE_NOT_SUPPORT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> error(MethodArgumentTypeMismatchException e) {
        log.error("[GlobalExceptionHandler]参数格式绑定异常: ", e);
        return Result.error(DefaultErrorEnum.REQUEST_PARAM_COVERT_FAIL);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public Result<?> error(DateTimeParseException e) {
        log.error("[GlobalExceptionHandler]参数格式绑定异常: ", e);
        return Result.error(DefaultErrorEnum.REQUEST_PARAM_COVERT_FAIL);
    }

    @ExceptionHandler(BizException.class)
    public Result<?> error(BizException e) {
        log.error("[GlobalExceptionHandler][自定义异常]: ", e);
        return Result.error(e.getErrorCode());
    }

    /*
     * 参数校验分类:
     *   1.单个参数校验, 需要类上增加@Validated, 抛出 ConstraintViolationException
     *   1.表单参数校验, 需要方法入参上增加@Validated, 抛出 BindException
     *   3.json请求体校验, 需要方法入参上增加@Validated, 抛出 MethodArgumentNotValidException
     */

    @ExceptionHandler(BindException.class)
    public Result<?> error(BindException e) {
        log.error("[GlobalExceptionHandler][BindException]参数绑定异常: ", e);
        // 抛出 BindException 的场景根据source区分
        Object source = ReflectUtil.getFieldValue(e.getFieldError(), "source");
        if (source instanceof HibernateConstraintViolation) {
            // 参数值校验问题
            FieldError fieldError = e.getFieldError();
            return Result.error(withTip(fieldError != null ? fieldError.getDefaultMessage() : null));
        }
        return Result.error(DefaultErrorEnum.REQUEST_PARAM_COVERT_FAIL);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> error(MethodArgumentNotValidException e) {
        log.error("[GlobalExceptionHandler][MethodArgumentNotValidException]参数绑定异常: ", e);
        FieldError fieldError = e.getFieldError();
        return Result.error(withTip(fieldError != null ? fieldError.getDefaultMessage() : null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> error(ConstraintViolationException e) {
        log.error("[GlobalExceptionHandler][ConstraintViolationException]参数绑定异常: ", e);
        return Result.error(withTip(e.getConstraintViolations().iterator().next().getMessage()));
    }

    private GlobalErrorCodeService withTip(String tip) {
        return new GlobalErrorCodeService() {
            @Override
            public String getCode() {
                return DefaultErrorEnum.INVALID_PARAM.getCode();
            }

            @Override
            public String getMessage() {
                return tip == null ? DefaultErrorEnum.INVALID_PARAM.getMessage() : null;
            }
        };
    }
}
