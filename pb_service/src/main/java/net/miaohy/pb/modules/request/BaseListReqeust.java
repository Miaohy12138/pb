package net.miaohy.pb.modules.request;

import lombok.Data;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class BaseListReqeust {
    private int pageSize;
    private int pageIdx;
}
