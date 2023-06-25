package cn.starry.freenessy.system.modules.user.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2023-06-22
 **/
@Data
public class TempDTO {

    @NotEmpty(message = "nickName不能为空")
    private String nickName;

    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private LocalTime localTime;
    private Date date;
}
