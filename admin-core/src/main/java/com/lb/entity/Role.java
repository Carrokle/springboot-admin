package com.lb.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色代号主键
     */
    @TableId("role_code")
    private String roleCode;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;


    @Override
    protected Serializable pkVal() {
        return this.roleCode;
    }

}
