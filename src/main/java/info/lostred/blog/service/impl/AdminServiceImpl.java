package info.lostred.blog.service.impl;

import info.lostred.blog.entity.Admin;
import info.lostred.blog.mapper.AdminMapper;
import info.lostred.blog.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
