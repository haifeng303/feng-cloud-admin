package io.github.haifeng303.shiro;


import com.fengzai.common.res.ResultResponse;
import com.fengzai.upms.dto.UserInfoDto;
import com.fengzai.common.res.SystemCodeEnum;
import com.fengzai.upms.feign.MenuFeign;
import com.fengzai.upms.feign.UserFeign;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @PACKAGE_NAME: com.fengzai.admin.payment.web.shiro
 * @author: rhf
 * @DATE: 2020/4/16
 **/
@Component
public class UserRealm extends AuthorizingRealm {

    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;
    /**
     * 数据权限过滤
     */
    public static final String SQL_FILTER = "sql_filter";

    @Resource
    private UserFeign userFeign;

    @Resource
    private MenuFeign menuFeign;


    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfoDto user = (UserInfoDto) principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        ResultResponse<List<String>> edpResponse = menuFeign.listPermsByUserId(SystemCodeEnum.PAYMENT, userId);
        List<String> permsList = edpResponse.getBody();

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        //查询用户信息
        ResultResponse<UserInfoDto> edpResponse = userFeign.getUserByUserName(token.getUsername());


        if (!edpResponse.isSuccess()) {
            throw new UnknownAccountException("帐号不存在");
        }

        UserInfoDto user = new UserInfoDto();

        if (edpResponse.getBody().getUserId() == null) {
            throw new UnknownAccountException("帐号不存在");
        }

        //账号锁定
        if (null != edpResponse.getBody().getUserId() && edpResponse.getBody().getStatus() == 0) {
            throw new LockedAccountException("帐号已被锁定,请联系管理员");
        }

        if (null != edpResponse.getBody().getUserId()) {
            user = edpResponse.getBody();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
