package info.lostred.blog.mapper;

import info.lostred.blog.entity.User;
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
}