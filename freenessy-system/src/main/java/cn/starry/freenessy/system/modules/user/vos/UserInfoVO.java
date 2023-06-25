package cn.starry.freenessy.system.modules.user.vos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserInfoVO {

    private long aa = 123;
    private Long id;
    private String loginName;
    private String realName;
    private String phone;
    private Integer gender;
    private LocalDateTime createTime;
    private Date date = new Date();

    private float f = 1252222222222225488.236544896321132312555665f;
    private Double d = 1252222222222225488.236544896321132312555665;

    private BigDecimal b = BigDecimal.valueOf(12553333333333488.236544896321132312555665);
}
