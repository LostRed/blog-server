package info.lostred.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import info.lostred.blog.annotation.EnableAdminLog;
import info.lostred.blog.annotation.EnableUserLog;
import info.lostred.blog.dto.LoginDto;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.entity.User;
import info.lostred.blog.service.AdminService;
import info.lostred.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation("获取登录管理员")
    @GetMapping("/admin/")
    public Response<Admin> getLoginAdmin(@ApiIgnore HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Response.verifyError("未登录!");
        }
        return Response.ok(admin);
    }

    @ApiOperation("管理员登录")
    @EnableAdminLog("管理员登录")
    @PostMapping("/admin/login")
    public Response<Admin> adminLogin(@ApiIgnore HttpSession session, @RequestBody LoginDto loginDto) {
        if (loginDto.getCaptcha() == null || !loginDto.getCaptcha().trim().equalsIgnoreCase((String) session.getAttribute("captcha"))) {
            return Response.verifyError("验证码错误!");
        }
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", loginDto.getUsername());
        Admin admin = adminService.getOne(wrapper);
        if (admin == null || !admin.getPassword().equals(loginDto.getPassword())) {
            return Response.verifyError("账号或密码错误!");
        }
        session.setAttribute("admin", admin);
        return Response.ok(admin);
    }

    @ApiOperation("管理员退出")
    @PostMapping("/admin/exit")
    public Response<Admin> adminExit(@ApiIgnore HttpSession session) {
        Admin admin = (Admin) session.getAttribute("user");
        if (admin == null) {
            session.setAttribute("admin", null);
        }
        session.invalidate();
        return Response.ok(admin);
    }

    @ApiOperation("获取登录用户")
    @GetMapping("/user/")
    public Response<User> getLoginUser(@ApiIgnore HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Response.verifyError("未登录!");
        }
        return Response.ok(user);
    }

    @ApiOperation("用户登录")
    @EnableUserLog("用户登录")
    @PostMapping("/user/login")
    public Response<User> userLogin(@ApiIgnore HttpSession session, @RequestBody LoginDto loginDto) {
        if (loginDto.getCaptcha() == null || !loginDto.getCaptcha().trim().equalsIgnoreCase((String) session.getAttribute("captcha"))) {
            return Response.verifyError("验证码错误!");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", loginDto.getUsername());
        User user = userService.getOne(wrapper);
        if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
            return Response.verifyError("账号或密码错误!");
        }
        session.setAttribute("user", user);
        return Response.ok(user);
    }

    @ApiOperation("用户退出")
    @PostMapping("/user/exit")
    public Response<User> userExit(@ApiIgnore HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("user", null);
        }
        session.invalidate();
        return Response.ok(user);
    }

    @ApiOperation("获取图片验证码")
    @GetMapping("/captcha")
    public void getKaptcha(HttpServletResponse response, @ApiIgnore HttpSession session) {
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        session.setAttribute("captcha", text);
        response.setContentType("image/png");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
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
