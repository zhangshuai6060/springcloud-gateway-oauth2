package com.example.springcloudalibabaoauthauth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Data
@TableName(value = "sys_auth_user")
public class SysAuthUser extends BaseModel {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "mobile_phone")
    private String mobilePhone;

    /**
     * 盐值
     */
    @TableField(value = "salt", fill = FieldFill.INSERT_UPDATE)
    private String salt;

    /**
     * 用户状态1.启用2.禁用
     */
    @TableField(value = "state")
    private Byte state;

    /**
     * 微信openid
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 模块名称1.校盟助手2.慧管校3.系统
     */
    @TableField(value = "module", fill = FieldFill.INSERT_UPDATE)
    private Byte module;

    /**
     * 过期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;

}