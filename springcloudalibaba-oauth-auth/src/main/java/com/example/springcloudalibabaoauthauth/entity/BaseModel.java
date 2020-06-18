package com.example.springcloudalibabaoauthauth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/12 17:03
 * 4
 */
@Data
public class BaseModel {

    /**
     * 创建人id
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 上次修改人id
     */
    @TableField(value = "last_modify_by", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifyBy;

    /**
     * 上次修改时间
     */
    @TableField(value = "last_modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date lastModifyTime;

    /**
     * 逻辑删除1.正常2.已删除
     */
    @TableField(value = "logical_deleted", fill = FieldFill.INSERT)
    private Byte logicalDeleted;
}