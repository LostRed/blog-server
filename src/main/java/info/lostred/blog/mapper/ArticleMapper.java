package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import info.lostred.blog.entity.Article;
import info.lostred.blog.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    ArticleVo selectVoById(@Param("id") Integer id);

    <E extends IPage<ArticleVo>> E selectPageVo(E page, @Param(Constants.WRAPPER) Wrapper<ArticleVo> queryWrapper);
}
