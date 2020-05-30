package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.item.DoctorItem;

import java.util.List;

/**
 * Author : MIAOHY
 * time : 2020/5/28
 */
@Data
public class GetDocrorListByNameResponse {
    private List<DoctorItem> list;
    private Integer totalCount;
}
