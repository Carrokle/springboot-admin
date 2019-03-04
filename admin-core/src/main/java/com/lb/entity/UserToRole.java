package com.lb.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

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
 * 用户角色关系表
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user_to_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserToRole extends Model<UserToRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "user_to_role_id", type = IdType.AUTO)
    private Integer userToRoleId;
    /**
     * 用户编号
     */
    @TableField("user_no")
    private String userNo;
    /**
     * 角色代号
     */
    @TableField("role_code")
    private String roleCode;


    @Override
    protected Serializable pkVal() {
        return this.userToRoleId;
    }

}
