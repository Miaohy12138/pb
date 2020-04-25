package net.miaohy.pb.modules.response;

import lombok.Data;
import net.miaohy.pb.modules.entity.PbDepartment;

import java.util.List;
import java.util.Map;

/**
 * Author : MIAOHY
 * time : 2020/4/24
 */
@Data
public class GetDepartmentListResponse {
    private Map<String, List<PbDepartment>> departments;
}
