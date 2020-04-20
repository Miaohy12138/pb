package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.Hospital;

import java.util.List;
/**
 * @author miaohy
 * @since 2020-04-19
 */
@Data
public class GetHospitalListResponse {
    private int totalCount;
    private List<Hospital> hospitals;
}
