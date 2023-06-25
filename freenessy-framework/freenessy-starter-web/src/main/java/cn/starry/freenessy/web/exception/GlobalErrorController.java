package cn.starry.freenessy.web.exception;

import cn.starry.freenessy.base.resp.DefaultErrorEnum;
import cn.starry.freenessy.base.resp.Result;
import cn.starry.freenessy.base.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description: 处理控制器层以外抛出的错误
 * 
 * @author: TJ
 * @date:  2022-06-25
 **/
@Slf4j
@RestController
public class GlobalErrorController extends BasicErrorController {

    public GlobalErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    public GlobalErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    public GlobalErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        // 获取错误信息
        ErrorAttributeOptions attributeOptions = ErrorAttributeOptions.defaults();
        attributeOptions = attributeOptions.including(ErrorAttributeOptions.Include.MESSAGE);
        Map<String, Object> body = getErrorAttributes(request, attributeOptions);
        log.error("[GlobalErrorController]错误拦截: {}", JsonUtil.toJsonFormat(body));

        DefaultErrorEnum errorEnum = DefaultErrorEnum.UNKNOWN_FAIL;
        Integer status = (Integer) body.get("status");
        if (status == 404) {
            errorEnum = DefaultErrorEnum.INVALID_URL;
        }
        return new ResponseEntity<>(Result.error(errorEnum).toMap(), getStatus(request));
    }
}
