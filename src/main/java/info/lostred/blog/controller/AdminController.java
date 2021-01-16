package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import info.lostred.blog.annotation.LogAdmin;
import info.lostred.blog.annotation.Validate;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.service.AdminService;
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
 * 管理员 前端控制器
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/blog/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ApiOperation("管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true)
    })
    @Validate
    @LogAdmin("登录")
    @GetMapping("/login")
    public Response<Admin> login(@ApiIgnore HttpSession session, String username, String password, String captcha) {
        if (captcha == null || !captcha.equals(session.getAttribute("captcha"))) {
            return Response.verifyError("验证码错误");
        }
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Admin admin = adminService.getBaseMapper().selectOne(wrapper);
        if (admin == null || !admin.getPassword().equals(password)) {
            return Response.verifyError("账号或密码错误");
        }
        session.setAttribute("admin", admin);
        return Response.ok(admin);
    }

    @ApiOperation("新增管理员")
    @Validate
    @LogAdmin("新增管理员")
    @PutMapping("/")
    public Response<Admin> saveAdmin(@RequestBody Admin admin) {
        if (!adminService.save(admin)) {
            return Response.serviceError("新增失败");
        }
        return Response.ok();
    }

    @ApiOperation("修改管理员")
    @Validate
    @LogAdmin("修改管理员")
    @PostMapping("/")
    public Response<Admin> updateAdmin(@RequestBody Admin admin) {
        if (!adminService.updateById(admin)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("删除管理员")
    @ApiImplicitParam(name = "id", value = "管理员id", required = true)
    @LogAdmin("删除管理员")
    @DeleteMapping("/{id}")
    public Response<Admin> removeAdmin(@PathVariable Integer id) {
        if (!adminService.removeById(id)) {
            return Response.serviceError("删除失败");
        }
        return Response.ok();
    }

    @ApiOperation("条件翻页查询管理员列表")
    @GetMapping("/")
    @ApiImplicitParam(name = "username", value = "用户名")
    public Response<IPage<Admin>> listAdmin(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            wrapper.like("username", username);
        }
        IPage<Admin> page = adminService.page(new Page<>(), wrapper);
        return Response.ok(page);
    }
}
