package info.lostred.blog.aspect;

import info.lostred.blog.annotation.EnableAdminLog;
import info.lostred.blog.annotation.EnableUserLog;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.entity.AdminLog;
import info.lostred.blog.entity.User;
import info.lostred.blog.entity.UserLog;
import info.lostred.blog.mapper.AdminLogMapper;
import info.lostred.blog.mapper.UserLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>启用日志通知</p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Aspect
@Component
public class EnableLogAdvice {
    @Resource
    private AdminLogMapper adminLogMapper;
    @Resource
    private UserLogMapper userLogMapper;

    @Pointcut("@annotation(info.lostred.blog.annotation.EnableAdminLog)")
    public void logAdminPointCut() {
    }

    @Pointcut("@annotation(info.lostred.blog.annotation.EnableUserLog)")
    public void logUserPointCut() {
    }

    @After("logAdminPointCut()")
    public void logAdmin(JoinPoint joinPoint) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //获取请求
        HttpServletRequest request = attr.getRequest();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        String event = method.getAnnotation(EnableAdminLog.class).value();
        //创建日志
        AdminLog adminLog = new AdminLog();
        adminLog.setEvent(event);
        adminLog.setRemark("IP地址：" + request.getRemoteAddr());
        //从session中获取登录的管理员对象
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            adminLog.setAdminId(admin.getId());
        }
        adminLogMapper.insert(adminLog);
    }

    @After("logUserPointCut()")
    public void logUser(JoinPoint joinPoint) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //获取请求
        HttpServletRequest request = attr.getRequest();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        String event = method.getAnnotation(EnableUserLog.class).value();
        //创建日志
        UserLog userLog = new UserLog();
        userLog.setEvent(event);
        userLog.setRemark("IP地址：" + request.getRemoteAddr());
        //从session中获取登录的管理员对象
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            userLog.setUserId(user.getId());
        }
        userLogMapper.insert(userLog);
    }
}
