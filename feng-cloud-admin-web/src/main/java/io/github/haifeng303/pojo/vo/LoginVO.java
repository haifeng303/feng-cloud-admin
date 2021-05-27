package io.github.haifeng303.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @PACKAGE_NAME: io.github.haifeng303.pojo.vo
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/14
 **/
@Data
public class LoginVO {
    @NotNull(message = "请输入用户名")
    private String userName;
    @NotNull(message = "请输入密码")
    private String password;
    @NotNull(message = "请输入验证码")
    private String verify;
}
