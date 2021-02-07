package info.lostred.blog.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>MyBatis-Plus自动填充处理器</p>
 * <p>在数据库插入或更新时，MyBatis-Plus会自动对实体中的某些字段设置默认值</p>
 *
 * @author lostred
 * @since 2020-12-23
 */
@Slf4j
@Component
public class AutoFillHandler implements MetaObjectHandler {
    /**
     * 执行insert语句自动填充
     *
     * @param metaObject 实体类字段属性
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
        this.strictInsertFill(metaObject, "hot", Integer.class, 0);
        //在这里添加需要自动填充的字段
    }

    /**
     * 执行update语句自动填充
     *
     * @param metaObject 实体类字段属性
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
        //在这里添加需要自动填充的字段
    }
}
