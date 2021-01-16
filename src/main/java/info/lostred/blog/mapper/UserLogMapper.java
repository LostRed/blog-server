package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import info.lostred.blog.entity.UserLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户操作日志 Mapper 接口
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Mapper
public interface UserLogMapper extends BaseMapper<UserLog> {

}
