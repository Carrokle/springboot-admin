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
 * 系统配置信息表(sys_config)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:41:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_config")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Config extends Model<Config> {
    @TableId(value = "id")
    private Long id;
    /**
     *key
     */
    @TableId(value = "param_key")
    private String paramKey;
    /**
     *value
     */
    @TableId(value = "param_value")
    private String paramValue;
    /**
     *状态   0：隐藏   1：显示
     */
    @TableId(value = "status")
    private Integer status;
    /**
     *备注
     */
    @TableId(value = "remark")
    private String remark;



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