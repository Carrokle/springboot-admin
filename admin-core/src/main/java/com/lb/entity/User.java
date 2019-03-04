package com.lb.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

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
 * 用户表
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @TableId("user_no")
    private String userNo;
    /**
     * 是电话号码，也是账号（登录用）
     */
    private String mobile;
    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;
    /**
     * 单位
     */
    private String unit;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态值（1：启用，2：禁用，3：删除）
     */
    private Integer status;
    /**
     * 职位
     */
    private String job;


    @Override
    protected Serializable pkVal() {
        return this.userNo;
    }

}
