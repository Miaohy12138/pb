package net.miaohy.pb.modules.request;

import lombok.Data;

/**
 * Author : MIAOHY
 * time : 2020/5/10
 */
@Data
public class EditLikeRequest extends BaseEditRequest {

    /**
     * 目标id
     */
    private Integer targetId;
    /**
     * 1、医院 2、医生
     */
    private Integer targetType;
    private Integer id;
}
