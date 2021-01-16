package info.lostred.blog.service.impl;

import info.lostred.blog.entity.Catalogue;
import info.lostred.blog.mapper.CatalogueMapper;
import info.lostred.blog.service.CatalogueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章类型 服务实现类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Service
public class CatalogueServiceImpl extends ServiceImpl<CatalogueMapper, Catalogue> implements CatalogueService {

}
