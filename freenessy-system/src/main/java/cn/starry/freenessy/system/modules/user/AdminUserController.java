package cn.starry.freenessy.system.modules.user;

import cn.starry.freenessy.base.resp.Result;
import cn.starry.freenessy.system.modules.user.dtos.UserDTO;
import cn.starry.freenessy.system.modules.user.dtos.UserQuery;
import cn.starry.freenessy.system.modules.user.service.UserService;
import cn.starry.freenessy.system.modules.user.vos.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("admin-user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("get-user-detail")
    public Result<List<UserInfoVO>> getUserDetail(@NotNull(message = "id不能为空") Long id,
                                                  @NotEmpty(message = "名字也不能为空") String name) {
        return Result.ok();
    }

    @GetMapping("list-users")
    public Result<List<UserInfoVO>> listUsers(@Valid UserQuery userQuery) {
        return Result.ok(userService.listUsers(userQuery));
    }

    @PostMapping("save-user")
    public Result<Boolean> saveUser() {
        return Result.ok(null);
    }

    @PostMapping("update-user")
    public Result<Boolean> updateUser(@RequestBody @Valid UserDTO dto) {
        return Result.ok();
    }

    @PostMapping("delete-user")
    public Result<Boolean> deleteUser() {
        return Result.ok(null);
    }


}
