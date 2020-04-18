package net.miaohy.pb.modules.request;

import lombok.Data;

@Data
public class BaseListReqeust {
    private int pageSize;
    private int pageIdx;

}
