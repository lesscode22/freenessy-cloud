package cn.starry.freenessy.system.modules.user.service;

import cn.starry.freenessy.base.util.JsonUtil;
import cn.starry.freenessy.system.exception.SystemExceptionService;
import cn.starry.freenessy.system.metadata.entity.SysUserDO;
import cn.starry.freenessy.system.modules.user.dtos.UserQuery;
import cn.starry.freenessy.system.modules.user.mapper.UserMapper;
import cn.starry.freenessy.system.modules.user.vos.UserInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, SystemExceptionService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfoVO> listUsers(UserQuery userQuery) {
        List<UserInfoVO> data = userMapper.listUsers(userQuery);
        List<SysUserDO> list = Db.list(new LambdaQueryWrapper<>(SysUserDO.class)
                .eq(SysUserDO::getId, 223705284280321L));
        System.out.println(JsonUtil.toJsonFormat(list));
        return data;
    }
}
