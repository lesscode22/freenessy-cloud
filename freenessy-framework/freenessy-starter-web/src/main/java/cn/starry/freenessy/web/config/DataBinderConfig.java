package cn.starry.freenessy.web.config;

import cn.hutool.core.util.StrUtil;
import cn.starry.freenessy.base.util.DateUtil;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@ControllerAdvice
public class DataBinderConfig {

    /**
     * 注意此配置会使 @DateTimeFormat 失效
     * 若参数格式非默认格式, 则只能改用String接收入参
     */
    @InitBinder
    private void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StrUtil.isBlank(text)) {
                    return;
                }
                setValue(DateUtil.convertToDate(text));
            }
        });
        dataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StrUtil.isBlank(text)) {
                    return;
                }
                setValue(DateUtil.convertToDateTime(text));
            }
        });
        dataBinder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StrUtil.isBlank(text)) {
                    return;
                }
                setValue(DateUtil.convertToTime(text));
            }
        });
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StrUtil.isBlank(text)) {
                    return;
                }
                if (text.length() == 10) {
                    text += " 00:00:00";
                }
                LocalDateTime dateTime = DateUtil.convertToDateTime(text);
                setValue(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
            }
        });
    }
}
