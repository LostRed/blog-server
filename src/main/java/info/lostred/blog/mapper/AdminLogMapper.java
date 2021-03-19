package info.lostred.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import info.lostred.blog.entity.AdminLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 管理员操作日志 Mapper 接口
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Mapper
public interface AdminLogMapper extends BaseMapper<AdminLog> {

}
