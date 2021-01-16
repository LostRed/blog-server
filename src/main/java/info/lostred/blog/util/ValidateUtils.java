package info.lostred.blog.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * <p>控制器校验工具</p>
 *
 * @author lostred
 * @since 2021-01-16
 */
public class ValidateUtils {
    /**
     * 用户名校验
     *
     * @param username 用户名
     * @return 不符合规则返回true，否则返回false
     */
    public static boolean illegalUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return true;
        }
        return username.length() < 6 || username.length() > 8;
    }

    /**
     * 密码校验
     *
     * @param password 密码
     * @return 不符合规则返回true，否则返回false
     */
    public static boolean illegalPassword(String password) {
        if (StringUtils.isBlank(password)) {
            return true;
        }
        return password.length() < 6 || password.length() > 8;
    }

    /**
     * 邮箱校验
     *
     * @param email 邮箱
     * @return 不符合规则返回true，否则返回false
     */
    public static boolean illegalEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return true;
        }
        return !email.matches("^([A-Za-z0-9_\\-\\.])+@([A-Za-z0-9_\\-])+\\.([A-Za-z]{2,4})$");
    }
}
