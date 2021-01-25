package info.lostred.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.vo.ArticleVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void getVoById() {
        System.out.println(articleService.getVoById(1));
    }

    @Test
    void pageVo() {
        QueryWrapper<ArticleVo> wrapper = new QueryWrapper<>();
        wrapper.like("article.title", "测")
                .like("user.username", "l");
        IPage<ArticleVo> page = articleService.pageVo(new Page<>(), wrapper);
        System.out.println("total: " + page.getTotal());
        System.out.println("list: " + page.getRecords());
    }
}