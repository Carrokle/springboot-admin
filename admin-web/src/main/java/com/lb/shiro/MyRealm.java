package com.lb.shiro;

import com.lb.entity.User;
import com.lb.entity.UserToRole;
import com.lb.service.IMenuService;
import com.lb.service.IRoleService;
import com.lb.service.IUserService;
import com.lb.service.IUserToRoleService;
import com.lb.service.SpringContextBeanService;
import com.lb.util.JWTUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Date: 2019-3-1
 * Description:
 */
public class MyRealm extends AuthorizingRealm {

    private IUserService userService;
    private IUserToRoleService userToRoleService;
    private IMenuService menuService;
    private IRoleService roleService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 权限验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        if(userService == null){
            userService = SpringContextBeanService.getBean(IUserService.class);
        }
        if(userToRoleService == null){
            userToRoleService = SpringContextBeanService.getBean(IUserToRoleService.class);
        }
        if(menuService == null){
            menuService = SpringContextBeanService.getBean(IMenuService.class);
        }

        if(roleService == null){
            roleService = SpringContextBeanService.getBean(IRoleService.class);
        }
        String userNo = JWTUtil.getUserNo(principals.toString());
        User user = userService.selectById(userNo);
        UserToRole userToRole = userToRoleService.selectByUserNo(userNo);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        return null;
    }

    /**
     * 登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
