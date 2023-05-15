package cn.starry.freenessy.system.modules.user.service;

import cn.starry.freenessy.system.modules.user.dtos.UserQuery;
import cn.starry.freenessy.system.modules.user.vos.UserInfoVO;

import java.util.List;

public interface UserService {

    List<UserInfoVO> listUsers(UserQuery userQuery);
}
