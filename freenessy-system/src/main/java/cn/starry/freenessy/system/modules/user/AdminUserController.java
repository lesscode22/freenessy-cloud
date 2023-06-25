package cn.starry.freenessy.system.modules.user;

import cn.starry.freenessy.base.resp.Result;
import cn.starry.freenessy.system.modules.user.dtos.TempDTO;
import cn.starry.freenessy.system.modules.user.dtos.UserDTO;
import cn.starry.freenessy.system.modules.user.dtos.UserQuery;
import cn.starry.freenessy.system.modules.user.service.UserService;
import cn.starry.freenessy.system.modules.user.vos.UserInfoVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Validated
@RestController
@RequestMapping("admin-user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("list1")
    public Result<List<UserInfoVO>> listUsers(@Validated UserQuery userQuery) {
        return Result.ok(userService.listUsers(userQuery));
    }

    @GetMapping("list2")
    public Result<List<UserInfoVO>> list2(@NotEmpty(message = "phone不能为空") String phone) {
        return Result.ok();
    }

    @PostMapping("list3")
    public Result<List<UserInfoVO>> list3(@Validated @RequestBody UserDTO userQuery) {
        return Result.ok();
    }

    @GetMapping("list4")
    public Result<List<UserInfoVO>> list4(LocalDate localDate,
                                          LocalDateTime localDateTime,
                                          LocalTime localTime,
                                          Date date) {
        return Result.ok(Lists.newArrayList(new UserInfoVO()));
    }

    @GetMapping("list5")
    public Result<List<UserInfoVO>> list5(TempDTO tempDTO) {
        return Result.ok();
    }

    @PostMapping("list6")
    public Result<List<UserInfoVO>> list6(@RequestBody TempDTO tempDTO) {
        return Result.ok();
    }
}
