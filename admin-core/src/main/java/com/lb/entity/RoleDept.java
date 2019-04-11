package com.lb.entity;

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
 * 角色与部门对应关系(sys_role_dept)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_dept")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDept extends Model<RoleDept> {
    @TableId(value = "id")
    private Long id;
    /**
     *角色ID
     */
    @TableId(value = "role_id")
    private Long roleId;
    /**
     *部门ID
     */
    @TableId(value = "dept_id")
    private Long deptId;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
        
}