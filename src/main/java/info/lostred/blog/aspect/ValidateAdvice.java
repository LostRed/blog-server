package info.lostred.blog.aspect;

import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.entity.User;
import info.lostred.blog.util.ValidateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>用户名和密码验证通知</p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Aspect
@Component
public class ValidateAdvice {
    @Pointcut("@annotation(info.lostred.blog.annotation.Validate)")
    public void validatePointCut() {
    }

    private Map<String, Object> getFieldsName(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new TreeMap<>();
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                paramMap.put(parameterNames[i], args[i]);
            }
        }
        return paramMap;
    }

    @Around("validatePointCut()")
    public Object validateAccount(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Map<String, Object> fieldsName = getFieldsName(joinPoint);
            for (String key : fieldsName.keySet()) {
                switch (key) {
                    case "username":
                        String username = (String) fieldsName.get(key);
                        if (ValidateUtils.illegalUsername(username)) {
                            throw new Throwable("用户名不符合规范");
                        }
                        break;
                    case "password":
                        String password = (String) fieldsName.get(key);
                        if (ValidateUtils.illegalPassword(password)) {
                            throw new Throwable("密码不符合规范");
                        }
                        break;
                    case "admin":
                        Admin admin = (Admin) fieldsName.get(key);
                        if (ValidateUtils.illegalUsername(admin.getUsername())) {
                            throw new Throwable("用户名不符合规范");
                        } else if (ValidateUtils.illegalPassword(admin.getPassword())) {
                            throw new Throwable("密码不符合规范");
                        }
                        break;
                    case "user":
                        User user = (User) fieldsName.get(key);
                        if (ValidateUtils.illegalUsername(user.getUsername())) {
                            throw new Throwable("用户名不符合规范");
                        } else if (ValidateUtils.illegalPassword(user.getPassword())) {
                            throw new Throwable("密码不符合规范");
                        } else if (ValidateUtils.illegalEmail(user.getEmail())) {
                            throw new Throwable("邮箱不符合规范");
                        }
                        break;
                }
            }
            return joinPoint.proceed(args);
        } catch (Throwable throwable) {
            return Response.paramError(throwable.getMessage());
        }
    }
}
