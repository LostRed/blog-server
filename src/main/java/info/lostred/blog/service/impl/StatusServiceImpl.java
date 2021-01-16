package info.lostred.blog.service.impl;

import info.lostred.blog.entity.Status;
import info.lostred.blog.mapper.StatusMapper;
import info.lostred.blog.service.StatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 状态 服务实现类
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService {

}
