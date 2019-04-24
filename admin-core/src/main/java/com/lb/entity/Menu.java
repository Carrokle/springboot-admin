package com.lb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
@Accessors(chain = true)
@Data
@TableName("sys_menu")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    @TableField("title")
    private String title;

    /**
     * 组件名称
     */
    @TableField("component")
    private String component;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    @TableField(exist = false)
    private List<Menu> children;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof Menu){
            Menu menu = (Menu) obj;
            return this.getMenuId().equals(menu.getMenuId());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.getMenuId().hashCode();
    }
}
