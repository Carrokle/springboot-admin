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
 * 部门管理(sys_dept)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dept extends Model<Dept> {
    @TableId(value = "dept_id")
    private Long deptId;
    /**
     *上级部门ID，一级部门为0
     */
    @TableId(value = "parent_id")
    private Long parentId;
    /**
     *部门名称
     */
    @TableId(value = "name")
    private String name;
    /**
     *排序
     */
    @TableId(value = "order_num")
    private Integer orderNum;
    /**
     *是否删除  -1：已删除  0：正常
     */
    @TableId(value = "del_flag")
    private Integer delFlag;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }
        
}