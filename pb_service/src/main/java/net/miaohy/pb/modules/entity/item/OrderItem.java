package net.miaohy.pb.modules.entity.item;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbDoctor;
import net.miaohy.pb.modules.entity.PbOrder;
import net.miaohy.pb.modules.entity.PbSource;
import net.miaohy.pb.modules.entity.PbVisitPerson;

/**
 * Author : MIAOHY
 * time : 2020/5/27
 */
@Data
public class OrderItem  {
    private SourceItem sourceItem;
    private PbOrder order;
    private PbDoctor doctor;
    private PbVisitPerson visit;
}
