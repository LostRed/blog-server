package info.lostred.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.vo.AdminVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceTest {
    @Autowired
    AdminService adminService;

    @Test
    void pageVo() {
        QueryWrapper<AdminVo> wrapper = new QueryWrapper<>();
        wrapper.like("admin.username", "l");
        IPage<AdminVo> page = adminService.pageVo(new Page<>(), wrapper);
        System.out.println("total: " + page.getTotal());
        System.out.println("list: " + page.getRecords());
    }
}