package com.lb.shiro;

import com.lb.base.Constant;
import com.lb.entity.Menu;
import com.lb.entity.User;
import com.lb.entity.UserToRole;
import com.lb.exception.UnauthorizedException;
import com.lb.service.IMenuService;
import com.lb.service.IRoleService;
import com.lb.service.IUserService;
import com.lb.service.IUserToRoleService;
import com.lb.service.SpringContextBeanService;
import com.lb.util.ComUtil;
import com.lb.util.JWTUtil;
import com.lb.util.StringUtils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        // 查询用户角色关系
        UserToRole userToRole = userToRoleService.getByUserNo(userNo);
        // 根据角色编号查询菜单
        List<Menu> menuList = menuService.getMenuByRoleCode(userToRole.getRoleCode());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
 /*
        Role role = roleService.selectOne(new EntityWrapper<Role>().eq("role_code", userToRole.getRoleCode()));
        //添加控制角色级别的权限
        Set<String> roleNameSet = new HashSet<>();
        roleNameSet.add(role.getRoleName());
        simpleAuthorizationInfo.addRoles(roleNameSet);
        */

        // 添加菜单级别权限
        Set<String> pers = new HashSet<>();
        if(!ComUtil.isEmpty(menuList)){
            pers = menuList.stream()
                    .filter(menu -> StringUtils.isNotBlank(menu.getCode()))
                    .map(Menu::getCode).collect(Collectors.toSet());
        }
        simpleAuthorizationInfo.addStringPermissions(pers);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录验证
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        if(userService == null){
            userService = SpringContextBeanService.getBean(IUserService.class);
        }
        String token = (String) auth.getCredentials();
        if(Constant.isPass.get()){
            return new SimpleAuthenticationInfo(token,token,this.getName());
        }

        // 解密token
        String userNo = JWTUtil.getUserNo(token);
        User user = userService.getById(userNo);
        if(user == null){
            throw new UnauthenticatedException("User didn't existed!");
        }
        if(! JWTUtil.verify(token,userNo,user.getPassWord())){
            throw new UnauthorizedException("Username or password error");
        }
        return new SimpleAuthenticationInfo(token,token,this.getName());
    }
}
