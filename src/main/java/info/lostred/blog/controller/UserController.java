package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import info.lostred.blog.annotation.EnableAdminLog;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.User;
import info.lostred.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @ApiOperation("新增用户")
    @EnableAdminLog("新增用户")
    @PutMapping("/")
    public Response<User> saveUser(@Valid @RequestBody User user) {
        if (user == null) {
            return Response.paramError("请求参数错误");
        }
        user.setStatusId(1);
        if (!userService.save(user)) {
            return Response.serviceError("新增失败");
        }
        return Response.ok();
    }

    @ApiOperation("修改用户")
    @EnableAdminLog("修改用户")
    @PostMapping("/")
    public Response<User> updateUser(@Valid @RequestBody User user) {
        if (!userService.updateById(user)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @EnableAdminLog("删除用户")
    @DeleteMapping("/{id}")
    public Response<User> removeUser(@PathVariable Integer id) {
        if (!userService.removeById(id)) {
            return Response.serviceError("删除失败");
        }
        return Response.ok();
    }

    @ApiOperation("获取用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/{id}")
    public Response<User> getUser(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Response.ok(user);
    }

    @ApiOperation("根据用户名获取用户")
    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @GetMapping("/one")
    public Response<User> getUser(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);
        return Response.ok(user);
    }

    @ApiOperation("条件翻页查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "current", value = "当前页", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping("/")
    public Response<IPage<User>> listUser(String username, Long current, Long size) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            wrapper.like("username", username);
        }
        IPage<User> page = userService.page(new Page<>(current, size), wrapper);
        return Response.ok(page);
    }
}
