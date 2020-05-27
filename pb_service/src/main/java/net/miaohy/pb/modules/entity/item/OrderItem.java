package net.miaohy.pb.modules.entity.item;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbOrder;
import net.miaohy.pb.modules.entity.PbSource;

/**
 * Author : MIAOHY
 * time : 2020/5/27
 */
@Data
public class OrderItem extends PbOrder {
    private SourceItem sourceItem;
    private PbOrder order;
}
