package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbDoctor;

import java.util.List;

/**
 * Author : MIAOHY
 * time : 2020/4/21
 */
@Data
public class GetDoctorListResponse  {
    private List<PbDoctor> doctors;
    private int totalCount;
}
