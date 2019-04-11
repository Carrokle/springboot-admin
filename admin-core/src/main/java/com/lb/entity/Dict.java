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
 * 数据字典表(sys_dict)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dict extends Model<Dict> {
    @TableId(value = "id")
    private Long id;
    /**
     *字典名称
     */
    @TableId(value = "name")
    private String name;
    /**
     *字典类型
     */
    @TableId(value = "type")
    private String type;
    /**
     *字典码
     */
    @TableId(value = "code")
    private String code;
    /**
     *字典值
     */
    @TableId(value = "value")
    private String value;
    /**
     *排序
     */
    @TableId(value = "order_num")
    private Integer orderNum;
    /**
     *备注
     */
    @TableId(value = "remark")
    private String remark;
    /**
     *删除标记  -1：已删除  0：正常
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
        return this.id;
    }
        
}