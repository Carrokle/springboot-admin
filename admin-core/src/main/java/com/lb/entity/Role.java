package com.lb.entity;

import java.util.Date;
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

/**
 * 角色(sys_role)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends Model<Role> {
    @TableId(value = "role_id")
    private Long roleId;
    /**
     *角色名称
     */
    @TableId(value = "role_name")
    private String roleName;
    /**
     *备注
     */
    @TableId(value = "remark")
    private String remark;
    /**
     *部门ID
     */
    @TableId(value = "dept_id")
    private Long deptId;
    /**
     *创建时间
     */
    @TableId(value = "create_time")
    private Date createTime;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
        
}