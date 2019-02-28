package com.shiro.demo.service;

import com.shiro.demo.model.Permission;
import com.shiro.demo.model.Role;
import com.shiro.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Angus
 * @date 2019/2/26
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> roleNameList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            roleSet.forEach(role -> {
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) {
                    permissionSet.forEach(permission -> permissionList.add(permission.getName()));
                }
            });
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roleNameList);
        System.out.println("roleNameList = " + roleNameList);
        System.out.println("permissionList = " + permissionList);
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }

    /**
     * 认证登录
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user = userService.findByUsername(usernamePasswordToken.getUsername());
        log.debug(user.toString());
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
