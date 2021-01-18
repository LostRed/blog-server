package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import info.lostred.blog.annotation.LogAdmin;
import info.lostred.blog.annotation.LogUser;
import info.lostred.blog.annotation.Validate;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.User;
import info.lostred.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/blog/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true)
    })
    @Validate
    @LogUser("登录")
    @GetMapping("/login")
    public Response<User> login(@ApiIgnore HttpSession session, String username, String password, String captcha) {
        if (captcha == null || !captcha.equals(session.getAttribute("captcha"))) {
            return Response.verifyError("验证码错误");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userService.getBaseMapper().selectOne(wrapper);
        if (user == null || !user.getPassword().equals(password)) {
            return Response.verifyError("账号或密码错误");
        }
        session.setAttribute("user", user);
        return Response.ok(user);
    }

    @ApiOperation("新增用户")
    @Validate
    @LogAdmin("新增用户")
    @PutMapping("/")
    public Response<User> saveUser(@RequestBody User user) {
        if (!userService.save(user)) {
            return Response.serviceError("新增失败");
        }
        return Response.ok();
    }

    @ApiOperation("修改用户")
    @Validate
    @LogAdmin("修改用户")
    @PostMapping("/")
    public Response<User> updateUser(@RequestBody User user) {
        if (!userService.updateById(user)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @LogAdmin("删除用户")
    @DeleteMapping("/{id}")
    public Response<User> removeUser(@PathVariable Integer id) {
        if (!userService.removeById(id)) {
            return Response.serviceError("删除失败");
        }
        return Response.ok();
    }

    @ApiOperation("条件翻页查询用户列表")
    @GetMapping("/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示条数")
    })
    public Response<IPage<User>> listUser(String username, Long current, Long size) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            wrapper.like("username", username);
        }
        IPage<User> page = userService.page(new Page<>(current, size), wrapper);
        return Response.ok(page);
    }
}
