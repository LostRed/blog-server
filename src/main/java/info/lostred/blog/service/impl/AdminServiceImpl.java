package info.lostred.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.mapper.AdminMapper;
import info.lostred.blog.service.AdminService;
import info.lostred.blog.vo.AdminVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public IPage<AdminVo> pageVo(IPage<AdminVo> page, Wrapper<AdminVo> queryWrapper) {
        return adminMapper.selectPageVo(page, queryWrapper);
    }
}
