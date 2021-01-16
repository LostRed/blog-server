package info.lostred.blog.mapper;

import info.lostred.blog.entity.Admin;
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
}