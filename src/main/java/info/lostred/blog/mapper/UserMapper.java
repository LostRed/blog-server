package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import info.lostred.blog.entity.User;
import info.lostred.blog.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    <E extends IPage<UserVo>> E selectPageVo(E page, @Param(Constants.WRAPPER) Wrapper<UserVo> queryWrapper);
}
