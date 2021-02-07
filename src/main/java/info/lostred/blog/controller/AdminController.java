package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import info.lostred.blog.annotation.EnableAdminLog;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @ApiOperation("新增管理员")
    @EnableAdminLog("新增管理员")
    @PutMapping("/")
    public Response<Admin> saveAdmin(@Valid @RequestBody Admin admin) {
        if (!adminService.save(admin)) {
            return Response.serviceError("新增失败");
        }
        return Response.ok();
    }

    @ApiOperation("修改管理员")
    @EnableAdminLog("修改管理员")
    @PostMapping("/")
    public Response<Admin> updateAdmin(@Valid @RequestBody Admin admin) {
        if (!adminService.updateById(admin)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("删除管理员")
    @ApiImplicitParam(name = "id", value = "管理员id", required = true)
    @EnableAdminLog("删除管理员")
    @DeleteMapping("/{id}")
    public Response<Admin> removeAdmin(@PathVariable Integer id) {
        if (!adminService.removeById(id)) {
            return Response.serviceError("删除失败");
        }
        return Response.ok();
    }

    @ApiOperation("获取管理员")
    @ApiImplicitParam(name = "id", value = "管理员id", required = true)
    @GetMapping("/{id}")
    public Response<Admin> getAdmin(@PathVariable Integer id) {
        Admin admin = adminService.getById(id);
        return Response.ok(admin);
    }

    @ApiOperation("根据用户名获取管理员")
    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @GetMapping("/one")
    public Response<Admin> getAdmin(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Admin admin = adminService.getOne(wrapper);
        return Response.ok(admin);
    }

    @ApiOperation("条件翻页查询管理员列表")
    @GetMapping("/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "current", value = "当前页", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true)
    })
    public Response<IPage<Admin>> listAdmin(String username, Long current, Long size) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            wrapper.like("username", username);
        }
        IPage<Admin> page = adminService.page(new Page<>(current, size), wrapper);
        return Response.ok(page);
    }
}
