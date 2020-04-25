package net.miaohy.pb.modules.request;

import lombok.Data;

/**
 * Author : MIAOHY
 * time : 2020/4/21
 */
@Data
public class GetDoctorListRequest extends BaseListReqeust {
    /**
     * 医院id
     */
    private Integer hospitalId;
    /**
     * 科室id；
     */
    private Integer departmentId;
}
