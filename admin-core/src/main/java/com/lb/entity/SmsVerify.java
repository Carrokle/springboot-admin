package com.lb.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

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
 * 验证码发送记录
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_sms_verify")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmsVerify extends Model<SmsVerify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "sms_verify_id", type = IdType.AUTO)
    private Integer smsVerifyId;
    /**
     * 短信编号（可以自己生成，也可以第三方复返回）
     */
    @TableField("sms_id")
    private String smsId;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 验证码
     */
    @TableField("sms_verify")
    private String smsVerify;
    /**
     * 验证码类型（1：登录验证，2：注册验证，3：忘记密码，4：修改账号）
     */
    @TableField("sms_type")
    private Integer smsType;
    /**
     * 发送时间
     */
    @TableField("create_time")
    private Long createTime;


    @Override
    protected Serializable pkVal() {
        return this.smsVerifyId;
    }

}
