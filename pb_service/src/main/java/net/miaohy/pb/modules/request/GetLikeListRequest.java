package net.miaohy.pb.modules.request;

import lombok.Data;

/**
 * Author : MIAOHY
 * time : 2020/5/10
 */
@Data
public class GetLikeListRequest extends BaseListReqeust {
    //1、医院 2、医生
    private Integer targetType;
}
