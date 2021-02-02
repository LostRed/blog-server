package info.lostred.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import info.lostred.blog.entity.Article;
import info.lostred.blog.mapper.ArticleMapper;
import info.lostred.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import info.lostred.blog.vo.ArticleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public boolean updateHot(Integer id) {
        Article article = articleMapper.selectById(id);
        article.setHot(article.getHot() + 1);
        int result = articleMapper.updateById(article);
        return result > 0;
    }

    @Override
    public ArticleVo getVoById(Integer id) {
        return articleMapper.selectVoById(id);
    }

    @Override
    public IPage<ArticleVo> pageVo(IPage<ArticleVo> page, Wrapper<ArticleVo> queryWrapper) {
        return articleMapper.selectPageVo(new Page<>(), queryWrapper);
    }
}
