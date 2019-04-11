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
 * 用户与角色对应关系(sys_user_role)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole extends Model<UserRole> {
    @TableId(value = "id")
    private Long id;
    /**
     *用户ID
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     *角色ID
     */
    @TableId(value = "role_id")
    private Long roleId;



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