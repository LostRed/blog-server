package info.lostred.blog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import info.lostred.blog.entity.Admin;
import info.lostred.blog.vo.AdminVo;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
public interface AdminService extends IService<Admin> {
    IPage<AdminVo> pageVo(IPage<AdminVo> page, Wrapper<AdminVo> queryWrapper);
}
