package net.miaohy.pb.modules.request;

import lombok.Data;

/**
 * Author : MIAOHY
 * time : 2020/5/24
 */
@Data
public class CommentListRequest extends BaseListReqeust{

    private Integer id;
    /**
     * 0 医院 1 医生
     */
    private Integer type;
}
