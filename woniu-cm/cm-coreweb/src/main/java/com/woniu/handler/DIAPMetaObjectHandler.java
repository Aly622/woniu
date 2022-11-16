package com.woniu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.esmartwave.niumeng.diap.contants.CommonConstants;
import com.esmartwave.niumeng.diap.extend.TokenUser;
import com.woniu.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName GlobalMetaObjectHandler
 * @Desc 通用元数据 共同字段统一处理类
 * @Author Oliver.Liu
 * @Date 2019/6/9 18:17
 * @Version 1.0
 **/
@Component
public class DIAPMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        TokenUser user = SecurityUtils.getUser();
        if(metaObject.hasGetter("createdAt")) {
            setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
        }
        if(metaObject.hasGetter("updatedAt")) {
            setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        }
        if(metaObject.hasGetter("isDeleted")) {
            setFieldValByName("isDeleted", CommonConstants.IS_DELETED, metaObject);
        }
        if (user != null) {
            if(metaObject.hasGetter("createdBy")) {
                setFieldValByName("createdBy", user.getId(), metaObject);
            }
            if(metaObject.hasGetter("createdByName")) {
                setFieldValByName("createdByName", user.getUsername(), metaObject);
            }
            if(metaObject.hasGetter("updatedBy")) {
                setFieldValByName("updatedBy", user.getId(), metaObject);
            }
            if(metaObject.hasGetter("updatedByName")) {
                setFieldValByName("updatedByName", user.getUsername(), metaObject);
            }
            if(metaObject.hasGetter("tenantId")) {
                if(metaObject.getValue("tenantId") == null) {
                    setFieldValByName("tenantId", user.getTenantId(), metaObject);
                }
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        TokenUser user = SecurityUtils.getUser();
        if(metaObject.hasGetter("updatedAt")) {
            setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        }
        if (user != null) {
            if(metaObject.hasGetter("updatedBy")) {
                setFieldValByName("updatedBy", user.getId(), metaObject);
            }
            if(metaObject.hasGetter("updatedByName")) {
                setFieldValByName("updatedByName", user.getUsername(), metaObject);
            }
        }
    }
}
