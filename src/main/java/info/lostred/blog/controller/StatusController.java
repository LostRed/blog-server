package info.lostred.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.util.StringUtils;
import info.lostred.blog.dto.Response;
import info.lostred.blog.entity.Status;
import info.lostred.blog.service.StatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 状态 前端控制器
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Api(tags = "状态模块")
@RestController
@RequestMapping("/blog/status")
public class StatusController {
    @Resource
    private StatusService statusService;

    @ApiOperation("条件查询状态列表")
    @GetMapping("/")
    @ApiImplicitParam(name = "type", value = "状态类型", dataTypeClass = String.class)
    public Response<List<Status>> listCatalogue(String type) {
        QueryWrapper<Status> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(type)) {
            wrapper.eq("type", type);
        }
        List<Status> page = statusService.list(wrapper);
        return Response.ok(page);
    }
}
