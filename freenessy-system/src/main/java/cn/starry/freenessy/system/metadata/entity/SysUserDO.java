package cn.starry.freenessy.system.metadata.entity;

import cn.starry.freenessy.web.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Auto Generator
 * @since 2023-05-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserDO extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户编码
     */
    @TableField("user_code")
    private String userCode;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 组织id
     */
    @TableField("org_id")
    private Long orgId;

    /**
     * 性别,0:女,1:男
     */
    @TableField("sex")
    private Byte sex;

    /**
     * 联系方式
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户状态
     */
    @TableField("user_status")
    private Byte userStatus;
}
