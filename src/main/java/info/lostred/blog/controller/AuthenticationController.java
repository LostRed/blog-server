package info.lostred.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Api(tags = "身份认证模块")
@RestController
@RequestMapping("/blog/authentication")
public class AuthenticationController {
    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;
    @Resource
    private Producer producer;

    @ApiOperation("管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true)
    })
    @EnableAdminLog("管理员登录")
    @PostMapping("/admin/login")
    public Response<Admin> adminLogin(@ApiIgnore HttpSession session, String username, String password, String captcha) {
        if (captcha == null || !captcha.trim().equalsIgnoreCase((String) session.getAttribute("captcha"))) {
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
    @GetMapping("/user/login")
    public Response<User> userLogin(@ApiIgnore HttpSession session, String username, String password, String captcha) {
        System.err.println(username);
        System.err.println(password);
        System.err.println(session.getId() + ": " + captcha);
        if (captcha == null || !captcha.trim().equalsIgnoreCase((String) session.getAttribute("captcha"))) {
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

    @ApiOperation("获取图片验证码")
    @GetMapping("/captcha")
    public void getKaptcha(HttpServletResponse response, @ApiIgnore HttpSession session) {
        String text = producer.createText();
        System.err.println(session.getId() + ": " + text);
        BufferedImage image = producer.createImage(text);
        session.setAttribute("captcha", text);
        response.setContentType("image/png");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            System.err.println("响应验证码失败:" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
