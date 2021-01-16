package info.lostred.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import info.lostred.blog.entity.User;
import info.lostred.blog.mapper.UserMapper;
import info.lostred.blog.service.UserService;
import info.lostred.blog.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<UserVo> pageVo(IPage<UserVo> page, Wrapper<UserVo> queryWrapper) {
        return userMapper.selectPageVo(page, queryWrapper);
    }
}
