package info.lostred.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import info.lostred.blog.annotation.EnableAdminLog;
import info.lostred.blog.annotation.EnableUserLog;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.entity.User;
import info.lostred.blog.service.AdminService;
import info.lostred.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Api(tags = "身份认证模块")
@RestController
@RequestMapping("/blog/authentication")
public class AuthenticationController {
    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;

    @ApiOperation("管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true)
    })
    @EnableAdminLog("管理员登录")
    @PostMapping("/admin/login")
    public Response<Admin> adminLogin(@ApiIgnore HttpSession session, String username, String password, String captcha) {
        if (captcha == null || !captcha.equals(session.getAttribute("captcha"))) {
            return Response.verifyError("验证码错误!");
        }
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Admin admin = adminService.getOne(wrapper);
        if (admin == null || !admin.getPassword().equals(password)) {
            return Response.verifyError("账号或密码错误!");
        }
        session.setAttribute("admin", admin);
        return Response.ok(admin);
    }

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true)
    })
    @EnableUserLog("用户登录")
    @PostMapping("/user/login")
    public Response<User> userLogin(@ApiIgnore HttpSession session, String username, String password, String captcha) {
        if (captcha == null || !captcha.equals(session.getAttribute("captcha"))) {
            return Response.verifyError("验证码错误!");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);
        if (user == null || !user.getPassword().equals(password)) {
            return Response.verifyError("账号或密码错误!");
        }
        session.setAttribute("user", user);
        return Response.ok(user);
    }
}
