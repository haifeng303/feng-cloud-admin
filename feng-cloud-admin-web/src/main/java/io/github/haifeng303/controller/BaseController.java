package io.github.haifeng303.controller;

import com.fengzai.upms.dto.UserInfoDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @PACKAGE_NAME: io.github.haifeng303.controller
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/14
 **/
public abstract class BaseController {

    /**
     * @description 获取用户信息
     * @date 2021/5/14
     * @return com.fengzai.upms.dto.UserInfoDto
     */
    protected UserInfoDto getSessionUser() {
        Subject subject = SecurityUtils.getSubject();
        UserInfoDto userDto = (UserInfoDto) subject.getPrincipal();
        return userDto;
    }
}
