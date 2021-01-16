package info.lostred.blog;

import info.lostred.blog.util.ValidateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(ValidateUtils.illegalEmail("luwei.deng@gmail.com"));
    }
}
