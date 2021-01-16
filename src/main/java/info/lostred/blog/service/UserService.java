package info.lostred.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import info.lostred.blog.entity.User;
import info.lostred.blog.vo.UserVo;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
public interface UserService extends IService<User> {
    IPage<UserVo> pageVo(IPage<UserVo> page, Wrapper<UserVo> queryWrapper);
}
