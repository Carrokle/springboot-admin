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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_menu")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;
    /**
     * 父菜单主键
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 菜单代号,规范权限标识
     */
    @TableField("menu_code")
    private String menuCode;
    /**
     * 代码控制权限标识符
     */
    private String code;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单类型，1：菜单  2：业务操作
     */
    @TableField("menu_type")
    private Integer menuType;
    /**
     * 菜单的序号
     */
    private Integer num;
    /**
     * 菜单地址
     */
    private String url;
    private String icon;

    @TableField(exist = false)
    private List<Menu> childMenu;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
