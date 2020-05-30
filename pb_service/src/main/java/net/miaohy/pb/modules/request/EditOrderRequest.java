package net.miaohy.pb.modules.request;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbOrder;

/**
 * Author : MIAOHY
 * time : 2020/5/25
 */
@Data
public class EditOrderRequest extends BaseEditRequest{
    private Integer id;
    private PbOrder order;
}
