package info.lostred.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import info.lostred.blog.entity.Article;
import info.lostred.blog.vo.ArticleVo;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
public interface ArticleService extends IService<Article> {
    ArticleVo getVoById(Integer id);

    IPage<ArticleVo> pageVo(IPage<ArticleVo> page, Wrapper<ArticleVo> queryWrapper);
}
