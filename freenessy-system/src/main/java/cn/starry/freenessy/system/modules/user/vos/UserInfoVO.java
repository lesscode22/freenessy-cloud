package cn.starry.freenessy.system.modules.user.vos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoVO {

    private Long id;
    private String loginName;
    private String realName;
    private String phone;
    private Integer gender;
    private LocalDateTime createTime;
}
