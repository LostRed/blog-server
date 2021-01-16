package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.entity.User;
import info.lostred.blog.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void insert() {
        User user = new User();
        user.setStatusId(1);
        user.setUsername("lostred");
        user.setPassword("123456");
        user.setName("邓路炜");
        user.setSex(1);
        user.setEmail("lostred@outlook.com");
        userMapper.insert(user);
    }

    @Test
    void selectPageVo() {
        QueryWrapper<UserVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user.username", "l");
        Page<UserVo> page = userMapper.selectPageVo(new Page<>(), queryWrapper);
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());
    }
}