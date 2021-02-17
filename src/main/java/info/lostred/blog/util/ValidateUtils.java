package info.lostred.blog.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * <p>控制器校验工具</p>
 *
 * @author lostred
 * @since 2021-01-16
 */
public class ValidateUtils {
    private static final String USERNAME_REGEX = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    private static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+([_.][A-Za-z0-9]+)*@([A-Za-z0-9\\-]+\\.)+[A-Za-z]{2,6}$";

    /**
     * 用户名校验
     *
     * @param username 用户名
     * @return 不符合规则返回true，否则返回false
     */
    public static boolean illegalUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return true;
        } else if (username.matches(USERNAME_REGEX)) {
            return true;
        }
        return username.length() < 6 || username.length() > 16;
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
        } else return !password.matches(PASSWORD_REGEX);
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
        return !email.matches(EMAIL_REGEX);
    }
}
