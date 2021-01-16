package info.lostred.blog.service.impl;

import info.lostred.blog.entity.UserLog;
import info.lostred.blog.mapper.UserLogMapper;
import info.lostred.blog.service.UserLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户操作日志 服务实现类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {

}
