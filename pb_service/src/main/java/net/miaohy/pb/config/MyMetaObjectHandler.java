package net.miaohy.pb.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 在插入的时候填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 判断数据库中是否有这个列
        boolean hasSetter = metaObject.hasSetter("createTime");
        // 如果有就向这个列插入数据
        // 如果没有就不插
        if (hasSetter) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
    }

    /**
     * 在更新的时候填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 判断是否已经设置了值
        Object val = getFieldValByName("updateTime", metaObject);
        // 如果设置了就不进行自动填充
        if (val == null) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
