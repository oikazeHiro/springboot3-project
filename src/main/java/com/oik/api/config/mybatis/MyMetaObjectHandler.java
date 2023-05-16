package com.oik.api.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.oik.api.entity.User;
import com.oik.api.utils.dto.UserDTO;
import com.oik.api.utils.redis.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author oik
 *  mybatis-plus 自动插入值
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        // 或者
        //this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
        String id = "";
        try {
            User user = UserHolder.getUser();
            id = user.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        this.strictInsertFill(metaObject, "createdBy", String.class, id);
        this.strictInsertFill(metaObject, "updatedBy", String.class, id);

        //this.setFieldValByName("createUser",user.getUsername(),metaObject); //


    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
//        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
        String id = "";
        try {
            User user = UserHolder.getUser();
            id = user.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        this.setFieldValByName("updatedBy", id, metaObject);
    }
}
