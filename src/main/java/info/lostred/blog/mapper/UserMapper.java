package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import info.lostred.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
