package info.lostred.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    <E extends IPage<AdminVo>> E selectPageVo(E page, @Param(Constants.WRAPPER) Wrapper<AdminVo> queryWrapper);
}
