package cn.starry.freenessy.web.exception;

import cn.starry.freenessy.base.exception.BizException;
import cn.starry.freenessy.base.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

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
        log.error("[GlobalExceptionHandler]请求体缺失: ", e);
        return Result.error("请求参数缺失");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> error(HttpRequestMethodNotSupportedException e) {
        log.error("[GlobalExceptionHandler]请求方式有误: ", e);
        return Result.error("请求方式有误");
    }

    @ExceptionHandler(BizException.class)
    public Result<?> error(BizException e) {
        log.error("[GlobalExceptionHandler][自定义异常]: ", e);
        return Result.error(e.getResultCode());
    }

    /**
     * 表单请求参数校验
     */
    @ExceptionHandler(BindException.class)
    public Result<?> error(BindException e) {
        log.error("[GlobalExceptionHandler][BindException]参数校验异常: ", e);
        return Result.error(e.getFieldErrors().get(0).getDefaultMessage());
    }

    /**
     * json请求体校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> error(MethodArgumentNotValidException e) {
        log.error("[GlobalExceptionHandler][MethodArgumentNotValidException]参数校验异常: ", e);
        return Result.error(e.getFieldErrors().get(0).getDefaultMessage());
    }

    /**
     *  路径参数校验, 比如 @PathVariable、@RequestParam 修饰单个参数的场景;
     *  需要在类级别开启校验 @Validated
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> error(ConstraintViolationException e) {
        log.error("[GlobalExceptionHandler][ConstraintViolationException]参数校验异常: ", e);
        return Result.error(e.getConstraintViolations().iterator().next().getMessage());
    }
}
