package net.miaohy.pb.modules.request;

import lombok.Data;

/**
 * Author : MIAOHY
 * time : 2020/5/27
 */
@Data
public class    OrderListRequest extends BaseListReqeust{
    //0 未支付 1 进行中 2待评价 3 已完成
    private Integer type;
}
