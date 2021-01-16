package info.lostred.blog.service.impl;

import info.lostred.blog.entity.AdminLog;
import info.lostred.blog.mapper.AdminLogMapper;
import info.lostred.blog.service.AdminLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员操作日志 服务实现类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

}
