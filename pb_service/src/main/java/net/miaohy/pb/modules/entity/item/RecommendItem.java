package net.miaohy.pb.modules.entity.item;

import lombok.Data;
import net.miaohy.pb.modules.entity.Hospital;
import net.miaohy.pb.modules.entity.PbDoctor;

/**
 * Author : MIAOHY
 * time : 2020/5/29
 */
@Data
public class RecommendItem {
    private Hospital hospital;
    private PbDoctor doctor;
    private Integer weight;
}
