package cn.starry.freenessy.system.modules.user.mapper;

import cn.starry.freenessy.system.modules.user.dtos.UserQuery;
import cn.starry.freenessy.system.modules.user.vos.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<UserInfoVO> listUsers(@Param("userQuery") UserQuery userQuery);
}
