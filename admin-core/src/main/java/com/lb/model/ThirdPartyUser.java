package com.lb.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Date: 2019-3-1
 * Description: 第三方登录用户信息实体类
 */
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyUser implements Serializable {
    /**账户*/
    private String account;
    /**昵称*/
    private String username;
    /**头像地址*/
    private String avatarUrl;
    /**性别*/
    private String gender;
    /**用户认证*/
    private String token;
    /**第三方id*/
    private String openid;
    /**用户类型*/
    private String provider;
    /**用户id*/
    private Integer userId;
    /**用户第三方id*/
    private Long userThirdPartyId;
}
