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
 * 菜单管理(sys_menu)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu extends Model<Menu> {
    @TableId(value = "menu_id")
    private Long menuId;
    /**
     *父菜单ID，一级菜单为0
     */
    @TableId(value = "parent_id")
    private Long parentId;
    /**
     *菜单名称
     */
    @TableId(value = "name")
    private String name;
    /**
     *菜单URL
     */
    @TableId(value = "url")
    private String url;
    /**
     *授权(多个用逗号分隔，如：user:list,user:create)
     */
    @TableId(value = "perms")
    private String perms;
    /**
     *类型   0：目录   1：菜单   2：按钮
     */
    @TableId(value = "type")
    private Integer type;
    /**
     *菜单图标
     */
    @TableId(value = "icon")
    private String icon;
    /**
     *排序
     */
    @TableId(value = "order_num")
    private Integer orderNum;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }
        
}