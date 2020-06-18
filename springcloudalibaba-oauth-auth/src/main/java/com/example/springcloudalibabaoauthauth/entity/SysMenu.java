package com.example.springcloudalibabaoauthauth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Data
@TableName(value = "sys_menu")
public class SysMenu extends BaseModel {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单或者按钮名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 类型1.菜单2.按钮
     */
    @TableField(value = "type")
    private Byte type;

    /**
     * 按钮显示code
     */
    @TableField(value = "code")
    private String code;

    /**
     * 父级菜单id
     */
    @TableField(value = "parent_id")
    private Long parentId;



    /**
     * 权限码
     */
    @TableField(value = "resource")
    private String resource;

    /**
     * 排序编号
     */
    @TableField(value = "sort")
    private Long sort;

}