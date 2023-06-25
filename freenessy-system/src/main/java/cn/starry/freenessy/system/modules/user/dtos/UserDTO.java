package cn.starry.freenessy.system.modules.user.dtos;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    @NotEmpty(message = "用户编码不能为空")
    private String code;
    @Max(value = 20, message = "年龄不能大于20")
    private Integer age;

    @Valid
    private TempDTO tempDTO;
}
