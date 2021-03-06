package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import info.lostred.blog.annotation.EnableUserLog;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Article;
import info.lostred.blog.entity.User;
import info.lostred.blog.service.ArticleService;
import info.lostred.blog.vo.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Api(tags = "文章模块")
@RestController
@RequestMapping("/blog/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @ApiOperation("新增文章")
    @EnableUserLog("新增文章")
    @PutMapping("/")
    public Response<Article> saveArticle(@Valid @RequestBody Article article, @ApiIgnore HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Response.paramError("未登录");
        }
        if (article == null) {
            return Response.paramError("请求参数错误");
        }
        article.setUserId(user.getId());
        if (!articleService.saveOrUpdate(article)) {
            return Response.serviceError("新增失败");
        }
        return Response.ok(article);
    }

    @ApiOperation("修改文章")
    @EnableUserLog("修改文章")
    @PostMapping("/")
    public Response<Article> updateArticle(@Valid @RequestBody Article article) {
        if (!articleService.updateById(article)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("浏览文章")
    @ApiImplicitParam(name = "id", value = "文章id", dataTypeClass = Integer.class, required = true)
    @PostMapping("/{id}")
    public Response<Article> browseArticle(@PathVariable Integer id) {
        if (!articleService.updateHot(id)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "id", value = "文章id", dataTypeClass = Integer.class, required = true)
    @EnableUserLog("删除文章")
    @DeleteMapping("/{id}")
    public Response<Article> removeArticle(@PathVariable Integer id) {
        if (!articleService.removeById(id)) {
            return Response.serviceError("删除失败");
        }
        return Response.ok();
    }

    @ApiOperation("获取文章")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "id", value = "文章id", dataTypeClass = Integer.class, required = true)
    public Response<ArticleVo> getArticle(@PathVariable Integer id) {
        ArticleVo articleVo = articleService.getVoById(id);
        if (articleVo == null) {
            return Response.serviceError("未查询到结果");
        }
        return Response.ok(articleVo);
    }

    @ApiOperation("条件翻页查询文章列表")
    @GetMapping("/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", dataTypeClass = String.class),
            @ApiImplicitParam(name = "author", value = "作者", dataTypeClass = String.class),
            @ApiImplicitParam(name = "column", value = "排序字段", dataTypeClass = String.class),
            @ApiImplicitParam(name = "current", value = "当前页", dataTypeClass = Long.class, required = true),
            @ApiImplicitParam(name = "size", value = "每页显示条数", dataTypeClass = Long.class, required = true)
    })
    public Response<IPage<ArticleVo>> listArticle(String title, String author, String column, Long current, Long size) {
        QueryWrapper<ArticleVo> wrapper = new QueryWrapper<>();
        wrapper.eq("status.name", "启用");
        if (!StringUtils.isNullOrEmpty(title)) {
            wrapper.like("article.title", title);
        }
        if (!StringUtils.isNullOrEmpty(author)) {
            wrapper.like("user.username", author);
        }
        if (!StringUtils.isNullOrEmpty(column)) {
            wrapper.orderByDesc(column);
        } else {
            wrapper.orderByDesc("hot");// 默认按照热度排序
        }
        IPage<ArticleVo> page = articleService.pageVo(new Page<>(current, size), wrapper);
        return Response.ok(page);
    }
}
