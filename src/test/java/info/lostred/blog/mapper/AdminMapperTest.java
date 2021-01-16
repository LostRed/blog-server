package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.vo.AdminVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminMapperTest {
    @Autowired
    AdminMapper adminMapper;

    @Test
    void insert() {
        Admin admin = new Admin();
        admin.setStatusId(1);
        admin.setUsername("lostred");
        admin.setPassword("123456");
        adminMapper.insert(admin);
    }

    @Test
    void selectPageVo() {
        QueryWrapper<AdminVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("admin.username", "l");
        Page<AdminVo> page = adminMapper.selectPageVo(new Page<>(), queryWrapper);
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());
    }
}