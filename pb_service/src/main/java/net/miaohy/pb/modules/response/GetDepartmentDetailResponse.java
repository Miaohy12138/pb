package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbDepartment;

/**
 * Author : MIAOHY
 * time : 2020/4/25
 */
@Data
public class GetDepartmentDetailResponse {
    private PbDepartment department;
}
