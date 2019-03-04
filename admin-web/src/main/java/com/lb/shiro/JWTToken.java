package com.lb.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Date: 2019-3-1
 * Description:
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
