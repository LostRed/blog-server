package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.util.StringUtils;
import info.lostred.blog.annotation.LogAdmin;
import info.lostred.blog.annotation.Validate;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Catalogue;
import info.lostred.blog.service.CatalogueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章类型 前端控制器
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Api(tags = "文章类型模块")
@RestController
@RequestMapping("/blog/catalogue")
public class CatalogueController {
    @Resource
    private CatalogueService catalogueService;

    @ApiOperation("新增文章类型")
    @Validate
    @LogAdmin("新增文章类型")
    @PutMapping("/")
    public Response<Catalogue> saveCatalogue(@RequestBody Catalogue catalogue) {
        if (!catalogueService.save(catalogue)) {
            return Response.serviceError("新增失败");
        }
        return Response.ok();
    }

    @ApiOperation("修改文章类型")
    @Validate
    @LogAdmin("修改文章类型")
    @PostMapping("/")
    public Response<Catalogue> updateCatalogue(@RequestBody Catalogue catalogue) {
        if (!catalogueService.updateById(catalogue)) {
            return Response.serviceError("修改失败");
        }
        return Response.ok();
    }

    @ApiOperation("删除文章类型")
    @ApiImplicitParam(name = "id", value = "文章类型id", required = true)
    @LogAdmin("删除文章类型")
    @DeleteMapping("/{id}")
    public Response<Catalogue> removeCatalogue(@PathVariable Integer id) {
        if (!catalogueService.removeById(id)) {
            return Response.serviceError("删除失败");
        }
        return Response.ok();
    }

    @ApiOperation("条件查询文章类型列表")
    @GetMapping("/")
    @ApiImplicitParam(name = "name", value = "名称")
    public Response<List<Catalogue>> listCatalogue(String name) {
        QueryWrapper<Catalogue> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(name)) {
            wrapper.like("name", name);
        }
        List<Catalogue> page = catalogueService.list(wrapper);
        return Response.ok(page);
    }
}
