package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.Hospital;

/**
 * Author : MIAOHY
 * time : 2020/4/19
 */
@Data
public class GetHospitalDetailResponse {
    private Hospital hospital;
}
