package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.annotation.LogUser;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.entity.Article;
import info.lostred.blog.service.ArticleService;
import info.lostred.blog.vo.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @LogUser("新增文章")
    @PutMapping("/")
    public Response<Article> saveAdmin(@RequestBody Article article) {
        if (!articleService.save(article)) {
            return Response.serviceError("新增失败");
        }
        return Response.ok();
    }

    @ApiOperation("修改文章")
    @LogUser("修改文章")
    @PostMapping("/")
    public Response<Admin> updateAdmin(@RequestBody Article article) {
        if (!articleService.updateById(article)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true)
    @LogUser("删除文章")
    @DeleteMapping("/{id}")
    public Response<Admin> removeAdmin(@PathVariable Integer id) {
        if (!articleService.removeById(id)) {
            return Response.serviceError("删除失败");
        }
        return Response.ok();
    }

    @ApiOperation("条件翻页查询文章列表")
    @GetMapping("/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题"),
            @ApiImplicitParam(name = "author", value = "作者")
    })
    public Response<IPage<ArticleVo>> listArticle(String title, String author) {
        QueryWrapper<ArticleVo> wrapper = new QueryWrapper<>();
        wrapper.eq("status.name", "启用");
        if (!StringUtils.isBlank(title)) {
            wrapper.like("article.title", title);
        }
        if (!StringUtils.isBlank(author)) {
            wrapper.like("user.username", author);
        }
        IPage<ArticleVo> page = articleService.pageVo(new Page<>(), wrapper);
        return Response.ok(page);
    }
}
