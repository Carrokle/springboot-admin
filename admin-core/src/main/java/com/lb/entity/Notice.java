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
 * 消息通知表
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_notice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;
    /**
     * 标题
     */
    private String title;
    /**
     * 类型 1:消息类型11;2:消息类型22;3:消息类型33;4:消息类型44;5:消息类型55
     */
    private Integer type;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;
    /**
     * 消息所有者
     */
    private String mobile;
    /**
     * 关联的主题no
     */
    @TableField("theme_no")
    private String themeNo;
    /**
     * 是否已读 0 未读; 1 已读
     */
    @TableField("is_read")
    private Integer isRead;


    @Override
    protected Serializable pkVal() {
        return this.noticeId;
    }

}
