package info.lostred.blog.mapper;

import info.lostred.blog.entity.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatusMapperTest {
    @Autowired
    StatusMapper statusMapper;

    @Test
    void insert() {
        Status status = new Status();
        status.setName("启用");
        status.setType("使用状态");
        status.setValue(1);
        statusMapper.insert(status);
        status.setName("禁用");
        status.setType("使用状态");
        status.setValue(0);
        statusMapper.insert(status);
    }

    @Test
    void select() {
        statusMapper.selectList(null).forEach(System.out::println);
    }
}