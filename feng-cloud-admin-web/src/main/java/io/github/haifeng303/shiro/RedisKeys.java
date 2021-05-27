package io.github.haifeng303.shiro;

/**
 * @PACKAGE_NAME: com.fengzai.admin.payment.web.shiro
 * @author: rhf
 * @DATE: 2020/4/16
 **/
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}
