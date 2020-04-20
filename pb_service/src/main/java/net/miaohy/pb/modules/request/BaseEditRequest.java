package net.miaohy.pb.modules.request;

import lombok.Data;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class BaseEditRequest {
    /**
     * 1、添加 2、删除  3、修改
     */
    private Integer actionType;
}
