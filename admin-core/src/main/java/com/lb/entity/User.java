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
 * 系统用户(sys_user)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends Model<User> {
    @TableId(value = "user_id")
    private Long userId;
    /**
     *用户名
     */
    @TableId(value = "username")
    private String username;
    /**
     *密码
     */
    @TableId(value = "password")
    private String password;
    /**
     *盐
     */
    @TableId(value = "salt")
    private String salt;
    /**
     *邮箱
     */
    @TableId(value = "email")
    private String email;
    /**
     *手机号
     */
    @TableId(value = "mobile")
    private String mobile;
    /**
     *状态  0：禁用   1：正常
     */
    @TableId(value = "status")
    private Integer status;
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
        return this.userId;
    }
        
}