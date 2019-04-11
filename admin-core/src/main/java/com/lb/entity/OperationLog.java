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
 * 操作日志表(tb_operation_log)表实体类
 *
 * @author mybatis-generator
 * @since 2019-04-11 11:42:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_operation_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationLog extends Model<OperationLog> {
    /**
     *主键
     */
    @TableId(value = "operation_log_id")
    private Integer operationLogId;
    /**
     *日志描述
     */
    @TableId(value = "log_description")
    private String logDescription;
    /**
     *方法参数
     */
    @TableId(value = "action_args")
    private String actionArgs;
    /**
     *用户主键
     */
    @TableId(value = "user_no")
    private String userNo;
    /**
     *类名称
     */
    @TableId(value = "class_name")
    private String className;
    /**
     *方法名称
     */
    @TableId(value = "method_name")
    private String methodName;
    @TableId(value = "ip")
    private String ip;
    /**
     *创建时间
     */
    @TableId(value = "create_time")
    private Long createTime;
    /**
     *模块名称
     */
    @TableId(value = "model_name")
    private String modelName;
    /**
     *操作
     */
    @TableId(value = "action")
    private String action;
    /**
     *是否成功 1:成功 2异常
     */
    @TableId(value = "succeed")
    private Integer succeed;
    /**
     *异常堆栈信息
     */
    @TableId(value = "message")
    private String message;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.operationLogId;
    }
        
}