package info.lostred.blog.controller;


import info.lostred.blog.dto.Response;
import info.lostred.blog.service.ArticleService;
import info.lostred.blog.service.UserLogService;
import info.lostred.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 状态统计 前端控制器
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Api(tags = "统计模块")
@RestController
@RequestMapping("/blog")
public class StatementController {
    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;
    @Resource
    private UserLogService userLogService;

    @ApiOperation("查看用户状态")
    @GetMapping("/user")
    public Response<Map<String, Object>> user() {
        return Response.ok();
    }

    @ApiOperation("查看用户注册量")
    @ApiImplicitParam(name = "type", value = "时期", required = true)
    @GetMapping("/register")
    public Response<Map<String, Object>> register(String period) {
        return Response.ok();
    }

    @ApiOperation("查看文章状态")
    @GetMapping("/article")
    public Response<Map<String, Object>> article() {
        return Response.ok();
    }

    @ApiOperation("查看博客发布数量")
    @ApiImplicitParam(name = "type", value = "时期", required = true)
    @GetMapping("/release")
    public Response<Map<String, Object>> release(String period) {
        return Response.ok();
    }

    @ApiOperation("查看博客浏览次数")
    @ApiImplicitParam(name = "type", value = "时期", required = true)
    @GetMapping("/browse")
    public Response<Map<String, Object>> browse(String period) {
        return Response.ok();
    }
}
