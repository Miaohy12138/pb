package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbDoctor;

/**
 * Author : MIAOHY
 * time : 2020/4/21
 */
@Data
public class GetDoctorDetailResponse {
    private PbDoctor doctor;
}
