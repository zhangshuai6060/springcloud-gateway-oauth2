package com.example.springcloudalibabaoauthauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu extends BaseModel {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单或者按钮id
     */
    @TableField(value = "menu_id")
    private Long menuId;

}