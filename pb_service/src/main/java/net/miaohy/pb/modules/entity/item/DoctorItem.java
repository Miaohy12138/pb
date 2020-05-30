package net.miaohy.pb.modules.entity.item;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbDoctor;

/**
 * Author : MIAOHY
 * time : 2020/5/28
 */
@Data
public class DoctorItem extends PbDoctor {
    private String hospitalLevel;
}
