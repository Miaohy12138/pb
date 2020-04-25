package net.miaohy.pb.modules.request;

import lombok.Data;

/**
 * Author : MIAOHY
 * time : 2020/4/23
 */
@Data
public class GetDepartmentListRequest  {
    /**
     * 科室ids
     */
    private String ids;

    private String hospitalId;

}
