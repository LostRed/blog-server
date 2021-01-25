package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.entity.Article;
import info.lostred.blog.vo.ArticleVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleMapperTest {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void insert() {
        Article article = new Article();
        article.setTitle("测试文章");
        article.setStatusId(1);
        article.setUserId(1);
        article.setCatalogueId(1);
        article.setContent("测试内容");
        article.setHot(0);
        articleMapper.insert(article);
    }

    @Test
    void selectVoById() {
        System.out.println(articleMapper.selectVoById(1));
    }

    @Test
    void selectPageVo() {
        QueryWrapper<ArticleVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("article.title", "测");
        IPage<ArticleVo> page = articleMapper.selectPageVo(new Page<>(), queryWrapper);
        System.out.println("total: " + page.getTotal());
        System.out.println("list: " + page.getRecords());
    }
}
