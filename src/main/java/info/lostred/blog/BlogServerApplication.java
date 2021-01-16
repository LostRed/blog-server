package info.lostred.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("info.lostred.blog.mapper")
public class BlogServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogServerApplication.class, args);
    }
}
