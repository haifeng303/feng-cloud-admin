package io.github.haifeng303.shiro;

import com.fengzai.common.exception.BusinessException;
import com.fengzai.common.res.GlobalResponseEnum;
import com.fengzai.common.res.SystemCodeEnum;
import com.fengzai.upms.dto.UserInfoDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @PACKAGE_NAME: com.fengzai.admin.payment.web.shiro
 * @author: rhf
 * @DATE: 2020/4/16
 **/
public class ShiroUtils {
    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = "SHA-256";
    /**
     * 循环次数
     */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static UserInfoDto getUserEntity() {
        return (UserInfoDto) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getCaptcha(String key) throws BusinessException {
        Object captcha = getSessionAttribute(key);
        if (captcha == null) {
            throw new BusinessException(SystemCodeEnum.PAYMENT, GlobalResponseEnum.NOT_LOGGIN, "验证码过期");
        }
        getSession().removeAttribute(key);
        return captcha.toString();
    }

}
