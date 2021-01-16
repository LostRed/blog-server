package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import info.lostred.blog.entity.Status;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 状态 Mapper 接口
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Mapper
public interface StatusMapper extends BaseMapper<Status> {

}
