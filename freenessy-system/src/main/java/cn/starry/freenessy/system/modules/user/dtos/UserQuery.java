package cn.starry.freenessy.system.modules.user.dtos;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class UserQuery {

    private String loginName;
    private String realName;
    @NotEmpty(message = "手机号不为空")
    private String phone;

    @Valid
    private TempDTO tempDTO;
}
