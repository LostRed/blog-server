package info.lostred.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void pageVo() {
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.like("user.username", "l");
        IPage<UserVo> page = userService.pageVo(new Page<>(), wrapper);
        System.out.println("total: " + page.getTotal());
        System.out.println("list: " + page.getRecords());
    }
}